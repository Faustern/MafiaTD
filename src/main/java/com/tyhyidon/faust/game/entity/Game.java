package com.tyhyidon.faust.game.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tyhyidon.faust.game.entity.enums.Result;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Василий on 12.01.14.
 */
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date date;

    private Result result;

    private int season;

    private String master;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "game")
    private List<Player> players;

    public Game() {
    }

    public Game(Result result) {
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
