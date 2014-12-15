package com.tyhyidon.faust.game.filter;

import com.tyhyidon.faust.game.model.Statistics;
import com.tyhyidon.faust.game.model.Player;

import java.util.List;

/**
 * Created by Василий on 02.02.14.
 */
public class Result {

    private Player player;

    private int games;

    private int gamesWin;

    private List<Statistics> gamesPlayed;

    private double rating;

    public Result(Player player, int games, int gamesWin) {
        this.player = player;
        this.games = games;
        this.gamesWin = gamesWin;
    }

    public Result(Player player, int games, int gamesWin,
                  List<Statistics> gamesPlayed, double rating) {
        this.player = player;
        this.games = games;
        this.gamesWin = gamesWin;
        this.gamesPlayed = gamesPlayed;
        this.rating = rating;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getGamesWin() {
        return gamesWin;
    }

    public void setGamesWin(int gamesWin) {
        this.gamesWin = gamesWin;
    }

    public double getWinProcent() {
        return (int)(100.0*gamesWin/games*100)/100.0;
    }

    public List<Statistics> getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(List<Statistics> gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Result{" +
                "player='" + player + '\'' +
                ", games=" + games +
                ", gamesWin=" + gamesWin +
                ", winProcent=" + (int)(100.0*gamesWin/games*100)/100.0 +
                ", rating=" + (int)(100.0*rating)/100.0 +
                '}';
    }
}
