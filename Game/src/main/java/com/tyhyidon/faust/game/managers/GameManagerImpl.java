package com.tyhyidon.faust.game.managers;

import com.tyhyidon.faust.game.entity.Member;
import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.mapping.MemberEntityConverter;
import com.tyhyidon.faust.game.entity.mapping.PlayerEntityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class GameManagerImpl {

    private static final Logger LOG = LoggerFactory.getLogger(GameManagerImpl.class);

    @Autowired
    private JdbcTemplate template;

    public List<Member> getMembers() {
        return template.query("SELECT * FROM players", new MemberEntityConverter());
    }


    public List<Player> getPlayers(int season) {
        return template.query("SELECT  p.*, g.result, m.vkontakte FROM statistics p " +
                "inner join games g on p.game_id = g.id  " +
                "inner join players m on p.nickname = m.nickname " +
                "where g.season = ?", new PlayerEntityConverter(), season);
    }
}
