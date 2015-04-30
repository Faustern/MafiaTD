package com.tyhyidon.faust.game.model;

import com.tyhyidon.faust.game.entity.Member;
import com.tyhyidon.faust.game.entity.Player;

import java.util.List;

/**
 * Created by vasylsavytskyi on 20.01.15.
 */
public class RatingSnapshot {

    private String nickname;

    private String vkontakte;

    private double rating;

    private int games;

    public RatingSnapshot(String nickname, String vkontakte, double rating, int games) {
        this.nickname = nickname;
        this.vkontakte = vkontakte;
        this.rating = rating;
        this.games = games;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getVkontakte() {
        return vkontakte;
    }

    public void setVkontakte(String vkontakte) {
        this.vkontakte = vkontakte;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }
}
