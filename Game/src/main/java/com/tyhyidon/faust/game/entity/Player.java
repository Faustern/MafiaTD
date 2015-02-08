package com.tyhyidon.faust.game.entity;

/**
 * Created by Василий on 12.01.14.
 */
public class Player {

    private long id;

    private long gameId;

    private String member;

    private Result result;

    private int number;

    private Role role;

    private Life life;

    private int bestVoices;

    private int finalDecision;

    private int fouls;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Life getLife() {
        return life;
    }

    public void setLife(Life life) {
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

}
