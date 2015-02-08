package com.tyhyidon.faust.game.services;

import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.Result;
import com.tyhyidon.faust.game.manager.GameManager;
import com.tyhyidon.faust.game.manager.MemberManager;
import com.tyhyidon.faust.game.manager.PlayerManager;
import com.tyhyidon.faust.game.model.RatingSnapshot;
import com.tyhyidon.faust.game.rating.RatingCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.stream.Collectors.*;

/**
 */
@Component
public class GameServiceImpl {

    private static Logger LOG = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private MemberManager memberManager;

    @Autowired
    private GameManager gameManager;

    @Autowired
    private PlayerManager playerManager;

    @Autowired
    private RatingCalculator ratingCalculator;

    public List<Double> calculateRating(List<Player> players, Result result) {
        return players.stream().map(p -> {p.setResult(result);return ratingCalculator.calculateRating(p);}).
                collect(toList());
    }

    public List<String> getMembers() {
        return memberManager.getMembers().stream().map(player -> player.getNickname()).collect(toList());
    }

    public List<RatingSnapshot> showRating(Integer season) {
        return ratingCalculator.calculateSeasonRating(playerManager.getPlayers(season).stream().
                collect(groupingBy(p -> p.getMember())).values());
    }

    public List<String> addMember(String nickname) {
        memberManager.addMember(nickname);
        return getMembers();
    }

    @Transactional
    public boolean addGame(Game game) {
        gameManager.addGame(game);
        playerManager.addPlayers(game.getPlayers().stream().map(p -> {p.setGameId(game.getId());return p;}).
                collect(toList()));
        return true;
    }
}
