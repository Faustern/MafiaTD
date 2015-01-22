package com.tyhyidon.faust.game.entity;

import com.tyhyidon.faust.game.legacy.Constants;
import com.tyhyidon.faust.game.rating.RatingCalculator;

import javax.persistence.*;

/**
 * Created by Василий on 12.01.14.
 */
@Entity
@Table(name = "Player")
public class Player {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne (targetEntity = Member.class)
    @JoinColumn(name = "nickname")
    private Member member;

    @ManyToOne (targetEntity = Game.class)
    @JoinColumn(name = "game_id")
    private Game game;

    @Column
    private int number;

    @Column
    private int role;

    @Column
    private int life;

    @Column(name = "best_voices")
    private int bestVoices;

    @Column(name = "final_decision")
    private int finalDecision;

    @Column
    private int fouls;

    public Player() {

    }

    public Player(Member member, Game game, int number, int role, int life, int bestVoices, int finalDecision, int fouls) {
        this.member = member;
        this.game = game;
        this.role = role;
        this.life = life;
        this.number = number;
        this.bestVoices = bestVoices;
        if (finalDecision > 0) {
            this.finalDecision = 1 / finalDecision;
        }
        this.fouls = fouls;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getBestVoices() {
        return bestVoices;
    }

    public void setBestVoices(int bestVoices) {
        this.bestVoices = bestVoices;
    }

    public int getFinalDecision() {
        return finalDecision;
    }

    public void setFinalDecision(int finalDecision) {
        this.finalDecision = finalDecision;
    }

    public int getFouls() {
        return fouls;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }

    public boolean isWin() {
        switch (role) {
            case Constants.ROLE_DON:
                if ((game.getResult() == Constants.RESULT_MAFIA_CLEAR_WIN) || (game.getResult() == Constants.RESULT_MAFIA_WIN)) {
                    return true;
                } else {
                    return false;
                }
            case Constants.ROLE_MAFIA:
                if ((game.getResult() == Constants.RESULT_MAFIA_CLEAR_WIN) || (game.getResult() == Constants.RESULT_MAFIA_WIN)) {
                    return true;
                } else {
                    return false;
                }
            case Constants.ROLE_SHERIFF:
                if ((game.getResult() == Constants.RESULT_CITY_CLEAR_WIN) || (game.getResult() == Constants.RESULT_CITY_WIN)) {
                    return true;
                } else {
                    return false;
                }
            case Constants.ROLE_CITIZEN:
                if ((game.getResult() == Constants.RESULT_CITY_CLEAR_WIN) || (game.getResult() == Constants.RESULT_CITY_WIN)) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", member=" + member +
                ", game=" + game +
                ", number=" + number +
                ", role=" + role +
                ", life=" + life +
                ", bestVoices=" + bestVoices +
                ", finalDecision=" + finalDecision +
                ", fouls=" + fouls +
                '}';
    }
}
