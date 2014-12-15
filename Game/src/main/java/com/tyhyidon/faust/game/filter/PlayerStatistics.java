package com.tyhyidon.faust.game.filter;

/**
 */
public class PlayerStatistics {

    private String nickname;

    private int gamesTotal;

    private double winTotal;

    private int gamesDon;

    private double winDon;

    private int gamesMafia;

    private double winMafia;

    private int gamesSheriff;

    private double winSheriff;

    private int gamesCitizen;

    private double winCitizen;

    private double foulsPerGame;

    private double bestVoicesPerGame;

    private int finalDecisions;

    private int killedOneNight;

    private int killed;

    private int redAway;

    private int redAwayOneDay;

    public PlayerStatistics(String nickname, int gamesTotal, double winTotal, int gamesDon,
                            double winDon, int gamesMafia, double winMafia, int gamesSheriff,
                            double winSheriff, int gamesCitizen, double winCitizen, double foulsPerGame,
                            double bestVoicesPerGame, int finalDecisions, int killedOneNight,
                            int killed, int citizenAway, int getCitizenAwayOneDay) {
        this.nickname = nickname;
        this.gamesTotal = gamesTotal;
        this.winTotal = winTotal;
        this.gamesDon = gamesDon;
        this.winDon = winDon;
        this.gamesMafia = gamesMafia;
        this.winMafia = winMafia;
        this.gamesSheriff = gamesSheriff;
        this.winSheriff = winSheriff;
        this.gamesCitizen = gamesCitizen;
        this.winCitizen = winCitizen;
        this.foulsPerGame = foulsPerGame;
        this.bestVoicesPerGame = bestVoicesPerGame;
        this.finalDecisions = finalDecisions;
        this.killedOneNight = killedOneNight;
        this.killed = killed;
        this.redAway = citizenAway;
        this.redAwayOneDay = getCitizenAwayOneDay;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getGamesTotal() {
        return gamesTotal;
    }

    public void setGamesTotal(int gamesTotal) {
        this.gamesTotal = gamesTotal;
    }

    public double getWinTotal() {
        return winTotal;
    }

    public void setWinTotal(double winTotal) {
        this.winTotal = winTotal;
    }

    public int getGamesDon() {
        return gamesDon;
    }

    public void setGamesDon(int gamesDon) {
        this.gamesDon = gamesDon;
    }

    public double getWinDon() {
        return winDon;
    }

    public void setWinDon(double winDon) {
        this.winDon = winDon;
    }

    public int getGamesMafia() {
        return gamesMafia;
    }

    public void setGamesMafia(int gamesMafia) {
        this.gamesMafia = gamesMafia;
    }

    public double getWinMafia() {
        return winMafia;
    }

    public void setWinMafia(double winMafia) {
        this.winMafia = winMafia;
    }

    public int getGamesSheriff() {
        return gamesSheriff;
    }

    public void setGamesSheriff(int gamesSheriff) {
        this.gamesSheriff = gamesSheriff;
    }

    public double getWinSheriff() {
        return winSheriff;
    }

    public void setWinSheriff(double winSheriff) {
        this.winSheriff = winSheriff;
    }

    public int getGamesCitizen() {
        return gamesCitizen;
    }

    public void setGamesCitizen(int gamesCitizen) {
        this.gamesCitizen = gamesCitizen;
    }

    public double getWinCitizen() {
        return winCitizen;
    }

    public void setWinCitizen(double winCitizen) {
        this.winCitizen = winCitizen;
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

    public int getFinalDecisions() {
        return finalDecisions;
    }

    public void setFinalDecisions(int finalDecisions) {
        this.finalDecisions = finalDecisions;
    }

    public int getKilledOneNight() {
        return killedOneNight;
    }

    public void setKilledOneNight(int killedOneNight) {
        this.killedOneNight = killedOneNight;
    }

    public int getKilled() {
        return killed;
    }

    public void setKilled(int killed) {
        this.killed = killed;
    }

    public int getRedAway() {
        return redAway;
    }

    public void setRedAway(int redAway) {
        this.redAway = redAway;
    }

    public int getRedAwayOneDay() {
        return redAwayOneDay;
    }

    public void setGetRedAwayOneDay(int redAwayOneDay) {
        this.redAwayOneDay = redAwayOneDay;
    }
}
