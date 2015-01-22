package com.tyhyidon.faust.game.services;

import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.manager.GameManager;
import com.tyhyidon.faust.game.manager.MemberManager;
import com.tyhyidon.faust.game.manager.PlayerManager;
import com.tyhyidon.faust.game.mapper.EntityToSnapshot;
import com.tyhyidon.faust.game.mapper.SnapshotToEntity;
import com.tyhyidon.faust.game.model.GameSnapshot;
import com.tyhyidon.faust.game.model.PlayerSnapshot;
import com.tyhyidon.faust.game.legacy.GameDAO;
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
    private GameDAO gameDAO;

    @Autowired
    private MemberManager memberManager;

  //  @Autowired
  //   private GameManager gameManager;

    @Autowired
    private PlayerManager playerManager;

    @Autowired
    private RatingCalculator ratingCalculator;

    public List<Double> calculateRating(List<PlayerSnapshot> playerSnapshots, int result) {
        return playerSnapshots.stream().map(p -> {p.setResult(result);return ratingCalculator.calculateRating(p);}).
                collect(toList());
    }

    public List<String> getMembers() {
        return memberManager.getMembers().stream().map(player -> player.getNickname()).collect(toList());
    }

    public List<RatingSnapshot> showRating(Integer season) {
        Collection<List<PlayerSnapshot>> players = playerManager.getPlayers(season).stream().map(ps ->
                EntityToSnapshot.map(ps)).collect(groupingBy(p -> p.getNickname())).values();
        return ratingCalculator.calculateSeasonRating(players);
    }

    public List<String> addMember(String nickname) {
        memberManager.addMember(nickname);
        return getMembers();
    }

    @Transactional
    public boolean addGame(GameSnapshot gameSnapshot) {
        Game game = gameDAO.addGame(SnapshotToEntity.map(gameSnapshot));
            gameSnapshot.getPlayers().stream().map(p -> SnapshotToEntity.map(p)).forEach(p ->
            {p.setGame(game);gameDAO.addPlayer(p);});
        return true;
    }
}
