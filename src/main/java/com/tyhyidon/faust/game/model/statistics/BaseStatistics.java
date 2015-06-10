package com.tyhyidon.faust.game.model.statistics;

import java.util.ArrayList;
import java.util.List;

public class BaseStatistics {

    private List<Long> games = new ArrayList<>();
    private List<Long> winningGames = new ArrayList<>();

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
}
