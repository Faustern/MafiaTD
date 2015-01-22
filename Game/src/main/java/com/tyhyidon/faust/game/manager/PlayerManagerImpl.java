package com.tyhyidon.faust.game.manager;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.mapping.PlayerEntityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vasylsavytskyi on 22.01.15.
 */
@Repository
public class PlayerManagerImpl implements PlayerManager {

    private static final Logger LOG = LoggerFactory.getLogger(PlayerManagerImpl.class);

    @Autowired
    private JdbcTemplate template;

    public List<Player> getPlayers(int season) {
        return template.query("SELECT  p.*, g.result, m.vkontakte FROM Player p " +
                "inner join Game g on p.game_id = g.id  " +
                "inner join Member m on p.nickname = m.nickname " +
                "where g.season = ?", new PlayerEntityConverter(), season);
    }

    public void addPlayers(List<Player> players) {
        template.update("INSERT INTO Player (nickname, game_id, number, role, life, best_voices, final_decision, fouls)" +
                "VALUES " + players.stream().map(p -> "(" + p.getMember().getNickname() + "," + p.getGame().getId() + "," +
                p.getNumber() + "," + p.getRole() + "," + p.getLife() + "," + p.getBestVoices() + "," +
                p.getFinalDecision() + "," + p.getFouls() + ")").reduce((x, y) -> x + "," + y).orElse(""));
    }

}
