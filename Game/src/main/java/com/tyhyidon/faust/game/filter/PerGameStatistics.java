package com.tyhyidon.faust.game.filter;

/**
 */
public class PerGameStatistics {

    private String nickname;

    private double foulsPerGame;

    private double bestVoicesPerGame;

    public PerGameStatistics(String nickname, double foulsPerGame, double bestVoicesPerGame) {
        this.nickname = nickname;
        this.foulsPerGame = foulsPerGame;
        this.bestVoicesPerGame = bestVoicesPerGame;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getFoulsPerGame() {
        return foulsPerGame;
    }

    public void setFoulsPerGame(double foulsPerGame) {
        this.foulsPerGame = foulsPerGame;
    }

    public double getBestVoicesPerGame() {
        return bestVoicesPerGame;
    }

    public void setBestVoicesPerGame(double bestVoicesPerGame) {
        this.bestVoicesPerGame = bestVoicesPerGame;
    }
}
