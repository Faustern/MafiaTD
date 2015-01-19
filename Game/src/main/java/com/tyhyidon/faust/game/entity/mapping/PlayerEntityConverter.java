package com.tyhyidon.faust.game.entity.mapping;

/**
 * Created by vasylsavytskyi on 19.01.15.
 */

import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.entity.Member;
import com.tyhyidon.faust.game.entity.Player;
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
        Member member = new Member();
        member.setNickname(rowSet.getString("nickname"));
        member.setVkontakte(rowSet.getString("vkontakte"));
        player.setMember(member);
        Game game = new Game();
        game.setResult(rowSet.getInt("result"));
        player.setGame(game);
        player.setNumber(rowSet.getInt("number"));
        player.setRole(rowSet.getInt("role"));
        player.setLife(rowSet.getInt("life"));
        player.setBestVoices(rowSet.getInt("best_voices"));
        player.setFinalDecision(rowSet.getInt("final_decision"));
        player.setFouls(rowSet.getInt("fouls"));
        player.setResultRating(rowSet.getDouble("result_rating"));
        player.setLifeRating(rowSet.getDouble("life_rating"));
        player.setBestVoicesRating(rowSet.getDouble("best_voices_rating"));
        player.setFinalDecisionRating(rowSet.getDouble("final_decision_rating"));
        player.setFoulsRating(rowSet.getDouble("fouls_rating"));
        player.setTotalRating(rowSet.getDouble("total_rating"));
        return player;
    }
}
