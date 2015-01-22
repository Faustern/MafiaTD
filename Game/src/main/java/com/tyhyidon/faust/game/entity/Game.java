package com.tyhyidon.faust.game.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Василий on 12.01.14.
 */
@Entity
@Table(name = "Game")
public class Game {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private Date date;

    @Column
    private int result;

    @Column
    private int season;

    @ManyToOne (targetEntity = Member.class)
    @JoinColumn(name = "master")
    private Member master;

    @JsonIgnore
    @OneToMany(mappedBy = "game", targetEntity = Player.class)
    private List<Player> playerSnapshots;

    public Game(int result, int season, Date date, Member master) {
        this.result = result;
        this.season = season;
        this.date = date;
        this.master = master;
    }

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Member getMaster() {
        return master;
    }

    public void setMaster(Member master) {
        this.master = master;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public List<Player> getPlayerSnapshots() {
        return playerSnapshots;
    }

    public void setSeasonGameStatistics(List<Player> playerSnapshots) {
        this.playerSnapshots = playerSnapshots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (id != game.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", date=" + date +
                ", result=" + result +
                ", master=" + master +
                '}';
    }
}
