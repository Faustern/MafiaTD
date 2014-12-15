package com.tyhyidon.faust.game.model;


import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Василий on 12.01.14.
 */
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "result")
    private int result;

    @Column(name = "season")
    private int season;

    @ManyToOne (targetEntity = Player.class)
    @JoinColumn(name = "master")
    private Player master;

    @JsonIgnore
    @OneToMany(mappedBy = "game", targetEntity = Statistics.class)
    private Set<Statistics> seasonGameStatistics;

    public Game(int result, int season, Date date, Player master) {
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

    public Player getMaster() {
        return master;
    }

    public void setMaster(Player master) {
        this.master = master;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public Set<Statistics> getSeasonGameStatistics() {
        return seasonGameStatistics;
    }

    public void setSeasonGameStatistics(Set<Statistics> seasonGameStatistics) {
        this.seasonGameStatistics = seasonGameStatistics;
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
