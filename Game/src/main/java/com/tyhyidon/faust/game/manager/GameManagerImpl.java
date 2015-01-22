package com.tyhyidon.faust.game.manager;

import com.tyhyidon.faust.game.entity.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class GameManagerImpl {

    private static final Logger LOG = LoggerFactory.getLogger(GameManagerImpl.class);

    @Autowired
    private JdbcTemplate template;

    public void addGame(Game game) {
        template.update("INSERT INTO Game (date, result, season, master)\n" + "VALUES (" + game.getDate() + "," +
                game.getResult() + "," + game.getSeason() + "," + game.getMaster().getNickname() + ")");
    }

}
