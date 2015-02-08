package com.tyhyidon.faust.game.manager;

import com.tyhyidon.faust.game.entity.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class GameManagerImpl implements GameManager{

    private static final Logger LOG = LoggerFactory.getLogger(GameManagerImpl.class);

    @Autowired
    private JdbcTemplate template;

    @Override
    public void addGame(Game game) {
        template.update("INSERT INTO Game (date, result, season, master)\n" + "VALUES (" + "'2012-01-02'" + "," +
                (game.getResult().ordinal()+1) + "," + game.getSeason() + ",'" + game.getMaster() + "')");
    }

}
