package com.tyhyidon.faust.game.manager;

import com.tyhyidon.faust.game.entity.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@Repository
public class GameManagerImpl implements GameManager{

    private static final Logger LOG = LoggerFactory.getLogger(GameManagerImpl.class);

    @Autowired
    private JdbcTemplate template;

    @PostConstruct
    private void inti() {
        int count = template.queryForInt("SELECT count(*) FROM User");
        count++;
    }

    @Override
    public long addGame(Game game) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rows = template.update(connection -> connection.prepareStatement("INSERT INTO Game (date, result, season, " +
                "master) " + "VALUES (" + "'2012-01-02'" + "," + game.getResult().ordinal() + "," + game.getSeason() +
                ",'" + game.getMaster() + "')", new String[] {"id"}), keyHolder);
        if (rows > 0) {
            return keyHolder.getKey().longValue();
        }
        return 0;
    }

}
