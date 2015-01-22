package com.tyhyidon.faust.game.manager;

import com.tyhyidon.faust.game.entity.Player;

import java.util.List;

/**
 * Created by vasylsavytskyi on 22.01.15.
 */
public interface PlayerManager {

    public List<Player> getPlayers(int season);

    public void addPlayers(List<Player> players);

}
