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

    private int result;

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

    public void setNumber(int number) {
        this.number = number;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setBestVoices(int bestVoices) {
        this.bestVoices = bestVoices;
    }

    public void setFinalDecision(int finalDecision) {
        this.finalDecision = finalDecision;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
