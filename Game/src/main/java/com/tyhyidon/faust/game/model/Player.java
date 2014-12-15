package com.tyhyidon.faust.game.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Василий on 12.01.14.
 */
@Entity
@Table(name = "players")
public class Player {

    @Id
    @Column(name = "nickname")
    private String nickname;

    @Column(name = "vkontakte")
    private String vkontakte;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "mail")
    private String mail;

    @Column(name = "telephone")
    private String telephone;

    @JsonIgnore
    @OneToMany(mappedBy = "player", targetEntity = Statistics.class)
    private Set<Statistics> statistics;

    @JsonIgnore
    @OneToMany(mappedBy = "master", targetEntity = Game.class)
    private Set<Statistics> masters;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Statistics> getMasters() {
        return masters;
    }

    public void setMasters(Set<Statistics> masters) {
        this.masters = masters;
    }

    public Set<Statistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(Set<Statistics> statistics) {
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return "Player{" +
                "nickname='" + nickname + '\'' +
                ", vkontakte='" + vkontakte + '\'' +
                ", birthday=" + birthday +
                ", mail='" + mail + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
