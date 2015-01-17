package com.tyhyidon.faust.game.model;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.Member;

import java.util.List;

/**
 * Created by Василий on 02.02.14.
 */
public class Result {

    private Member player;

    private int games;

    private int gamesWin;

    private List<Player> gamesPlayed;

    private double rating;

    public Result(Member player, int games, int gamesWin) {
        this.player = player;
        this.games = games;
        this.gamesWin = gamesWin;
    }

    public Result(Member player, int games, int gamesWin,
                  List<Player> gamesPlayed, double rating) {
        this.player = player;
        this.games = games;
        this.gamesWin = gamesWin;
        this.gamesPlayed = gamesPlayed;
        this.rating = rating;
    }

    public Member getPlayer() {
        return player;
    }

    public void setPlayer(Member player) {
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

    public List<Player> getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(List<Player> gamesPlayed) {
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
