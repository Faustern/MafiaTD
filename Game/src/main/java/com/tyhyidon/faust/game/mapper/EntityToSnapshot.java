package com.tyhyidon.faust.game.mapper;

import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.entity.Member;
import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.model.GameSnapshot;
import com.tyhyidon.faust.game.model.PlayerSnapshot;

/**
 * Created by vasylsavytskyi on 17.01.15.
 */
public class EntityToSnapshot {

    public static PlayerSnapshot map(Player player) {
        PlayerSnapshot playerSnapshot = new PlayerSnapshot();
        playerSnapshot.setNickname(player.getMember().getNickname());
        playerSnapshot.setResult(player.getGame().getResult());
        playerSnapshot.setNumber(player.getNumber());
        playerSnapshot.setRole(player.getRole());
        playerSnapshot.setLife(player.getLife());
        playerSnapshot.setBestVoices(player.getBestVoices());
        playerSnapshot.setFinalDecision(player.getFinalDecision());
        playerSnapshot.setFouls(player.getFouls());
        return playerSnapshot;
    };

}
