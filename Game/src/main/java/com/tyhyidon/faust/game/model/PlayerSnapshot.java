package com.tyhyidon.faust.game.model;

import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.entity.Member;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by vasylsavytskyi on 17.01.15.
 */
public class PlayerSnapshot {

    private int number;

    private String nickname;

    private int role;

    private int life;

    private int bestVoices;

    private int finalDecision;

    private int fouls;

    public int getNumber() {
        return number;
    }

    public String getNickname() {
        return nickname;
    }

    public int getRole() {
        return role;
    }

    public int getLife() {
        return life;
    }

    public int getBestVoices() {
        return bestVoices;
    }

    public int getFinalDecision() {
        return finalDecision;
    }

    public int getFouls() {
        return fouls;
    }
}
