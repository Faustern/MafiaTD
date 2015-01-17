package com.tyhyidon.faust.game.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by vasylsavytskyi on 17.01.15.
 */
public class GameSnapshot {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date date;

    private int result;

    private int season;

    private String master;

    private List<PlayerSnapshot> players;

    public Date getDate() {
        return date;
    }

    public int getResult() {
        return result;
    }

    public int getSeason() {
        return season;
    }

    public String getMaster() {
        return master;
    }

    public List<PlayerSnapshot> getPlayers() {
        return players;
    }
}
