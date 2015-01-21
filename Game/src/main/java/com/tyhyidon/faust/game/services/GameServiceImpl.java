package com.tyhyidon.faust.game.services;

import com.tyhyidon.faust.game.legacy.filter.*;
import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.managers.GameManagerImpl;
import com.tyhyidon.faust.game.mapper.EntityToSnapshot;
import com.tyhyidon.faust.game.mapper.SnapshotToEntity;
import com.tyhyidon.faust.game.model.GameSnapshot;
import com.tyhyidon.faust.game.model.PlayerSnapshot;
import com.tyhyidon.faust.game.legacy.Result;
import com.tyhyidon.faust.game.legacy.Constants;
import com.tyhyidon.faust.game.legacy.GameDAO;
import com.tyhyidon.faust.game.model.RatingSnapshot;
import com.tyhyidon.faust.game.rating.RatingCalculator;
import com.tyhyidon.faust.game.rating.RatingCalculatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

import static java.util.stream.Collectors.*;

/**
 */
@Component
public class GameServiceImpl {

    private static Logger LOG = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private GameManagerImpl gameManager;

    @Resource
    private Properties ratingProperties;

    public List<Double> calculateRating(List<PlayerSnapshot> playerSnapshots, int result) {
        RatingCalculator ratingCalculator = new RatingCalculatorImpl(ratingProperties);
        return playerSnapshots.stream().map(p -> {p.setResult(result);return ratingCalculator.calculateRating(p);}).
                collect(toList());
    }

    public List<String> getMembers() {
        return gameManager.getMembers().stream().map(player -> player.getNickname()).collect(toList());
    }

    public List<RatingSnapshot> showRating(Integer season) {

        RatingCalculator ratingCalculator = new RatingCalculatorImpl(ratingProperties);

        Collection<List<PlayerSnapshot>> players = gameManager.getPlayers(season).stream().map(ps -> EntityToSnapshot.map(ps)).
                collect(groupingBy(p -> p.getNickname())).values();

        return ratingCalculator.calculateSeasonRating(players);
    }

    public List<String> addMember(String nickname) {
        gameDAO.addMember(nickname);
        return getMembers();
    }

    @Transactional
    public boolean addGame(GameSnapshot gameSnapshot) {
        Game game = gameDAO.addGame(SnapshotToEntity.map(gameSnapshot));
        RatingCalculator ratingCalculator = new RatingCalculatorImpl(ratingProperties);
            gameSnapshot.getPlayers().stream().map(p -> {
                p.setResult(gameSnapshot.getResult());return SnapshotToEntity.map(p);
            }).forEach(p -> {
                p.setGame(game);
                ratingCalculator.calculateRating(EntityToSnapshot.map(p));
                gameDAO.addPlayer(p);
        });
        return true;
    }
}
