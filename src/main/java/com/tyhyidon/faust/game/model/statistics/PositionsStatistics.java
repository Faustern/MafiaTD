package com.tyhyidon.faust.game.model.statistics;

import com.tyhyidon.faust.game.entity.enums.Role;

import java.util.*;


/**
 * Created by vasylsavytskyi on 07.06.15.
 */
public class PositionsStatistics {

    private List<Long> games;
    private List<Long> winningGames;
    private Map<Role, List<Long>> roleGames;
    private Map<Role, List<Long>> roleWinningGames;


    public List<Long> getGames() {
        return games;
    }

    public void setGames(List<Long> games) {
        this.games = games;
    }

    public List<Long> getWinningGames() {
        return winningGames;
    }

    public void setWinningGames(List<Long> winningGames) {
        this.winningGames = winningGames;
    }

    public Map<Role, List<Long>> getRoleGames() {
        return roleGames;
    }

    public void setRoleGames(Map<Role, List<Long>> roleGames) {
        this.roleGames = roleGames;
    }

    public Map<Role, List<Long>> getRoleWinningGames() {
        return roleWinningGames;
    }

    public void setRoleWinningGames(Map<Role, List<Long>> roleWinningGames) {
        this.roleWinningGames = roleWinningGames;
    }
}
