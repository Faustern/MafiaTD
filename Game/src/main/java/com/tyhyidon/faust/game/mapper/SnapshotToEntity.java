package com.tyhyidon.faust.game.mapper;

import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.entity.Member;
import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.model.GameSnapshot;
import com.tyhyidon.faust.game.model.PlayerSnapshot;

/**
 * Created by vasylsavytskyi on 17.01.15.
 */
public class SnapshotToEntity{

    public static Game map(GameSnapshot gameSnapshot) {
        Game game = new Game();
        Member member = new Member();
        member.setNickname(gameSnapshot.getMaster());
        game.setMaster(member);
        game.setResult(gameSnapshot.getResult());
        game.setSeason(gameSnapshot.getSeason());
        game.setDate(gameSnapshot.getDate());
        return game;
    };

    public static Player map(PlayerSnapshot playerSnapshot) {
        Player player = new Player();
        Member member = new Member();
        member.setNickname(playerSnapshot.getNickname());
        player.setMember(member);
        player.setNumber(playerSnapshot.getNumber());
        player.setRole(playerSnapshot.getRole());
        player.setLife(playerSnapshot.getLife());
        player.setBestVoices(playerSnapshot.getBestVoices());
        player.setFinalDecision(playerSnapshot.getFinalDecision());
        player.setFouls(playerSnapshot.getFouls());
        return player;
    };
}
