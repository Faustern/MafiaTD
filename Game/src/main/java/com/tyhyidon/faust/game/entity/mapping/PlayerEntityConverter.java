package com.tyhyidon.faust.game.entity.mapping;

/**
 * Created by vasylsavytskyi on 19.01.15.
 */
import com.tyhyidon.faust.game.entity.Life;
import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.Result;
import com.tyhyidon.faust.game.entity.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 */
public class PlayerEntityConverter implements RowMapper<Player> {

    @Override
    public Player mapRow(ResultSet rowSet, int rowNum) throws SQLException {
        Player player = new Player();
        player.setId(rowSet.getInt("id"));
        player.setMember(rowSet.getString("nickname"));
        player.setResult(Result.values()[rowSet.getInt("result")]);
        player.setNumber(rowSet.getInt("number"));
        player.setRole(Role.values()[rowSet.getInt("role")]);
        player.setLife(Life.values()[rowSet.getInt("life")]);
        player.setBestVoices(rowSet.getInt("best_voices"));
        player.setFinalDecision(rowSet.getInt("final_decision"));
        player.setFouls(rowSet.getInt("fouls"));
        return player;
    }
}