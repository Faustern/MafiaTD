package com.tyhyidon.faust.game.legacy;

import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;

/**
 * Created by Василий on 18.01.14.
 */
//@Repository
//@Transactional
public class GameDAO {

  /*  @PersistenceContext
    private EntityManager entityManager;

    public List<Member> getMembers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
        Root<Member> member = criteriaQuery.from(Member.class);
        criteriaQuery.select(member);
        TypedQuery<Member> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public void addMember(String nickname) {
        Member player = new Member();
        player.setNickname(nickname);
        entityManager.merge(player);
    }

    public Member getMember(String nickname) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
        Root<Member> member = criteriaQuery.from(Member.class);
        criteriaQuery.select(member).where(criteriaBuilder.equal(member.get("nickname"), nickname));
        TypedQuery<Member> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    public List<Game> getGames(int season) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> criteriaQuery = criteriaBuilder.createQuery(Game.class);
        Root<Game> game = criteriaQuery.from(Game.class);
        if (season>0) criteriaQuery.select(game).where(criteriaBuilder.equal(game.get("season"), season));
        else criteriaQuery.select(game);
        TypedQuery<Game> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public Game addGame(Game game) {
        return entityManager.merge(game);
    }

    public void addPlayer(Player player) {;
        entityManager.merge(player);
    }

    public List<Player> getPlayerGamesByDefinedData(String nickname, Integer season,
                                         List <Integer> numbers, List <Integer> roles,
                                         List <Integer> lives, List <Integer> bestVoices,
                                         List <Integer> finalDecisions, List <Integer> fouls) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> criteriaQuery = criteriaBuilder.createQuery(Player.class);
        Root playerGame = criteriaQuery.from(Player.class);

        int i = 0;

        Predicate predicatePlayer = criteriaBuilder.and();
        Predicate predicateSeason = criteriaBuilder.and();
        Predicate  [] pNumbers = {criteriaBuilder.and()};
        Predicate  [] pRoles = {criteriaBuilder.and()};
        Predicate  [] pLives = {criteriaBuilder.and()};
        Predicate  [] pBestVoices = {criteriaBuilder.and()};
        Predicate  [] pFinalDecisions = {criteriaBuilder.and()};
        Predicate  [] pFouls = {criteriaBuilder.and()};

        if (nickname!=null)
            predicatePlayer = criteriaBuilder.equal(playerGame.join("player").get("nickname"), nickname);
        if (season!=0)
            predicateSeason = criteriaBuilder.equal(playerGame.join("game").get("season"), season);
        if (!numbers.isEmpty())
            pNumbers = new Predicate[numbers.size()];
        if (!roles.isEmpty())
            pRoles = new Predicate[roles.size()];
        if (!lives.isEmpty())
            pLives = new Predicate[lives.size()];
        if (!bestVoices.isEmpty())
            pBestVoices = new Predicate[bestVoices.size()];
        if (!finalDecisions.isEmpty())
            pFinalDecisions = new Predicate[finalDecisions.size()];
        if (!fouls.isEmpty())
            pFouls = new Predicate[fouls.size()];

        i = 0;
        for (Integer number : numbers) {
            pNumbers[i] = criteriaBuilder.equal(playerGame.get("number"), number); i++;
        }
        i = 0;
        for (Integer role : roles) {
            pRoles[i] = criteriaBuilder.equal(playerGame.get("role"), role); i++;
        }
        i = 0;
        for (Integer life : lives) {
            pLives[i] = criteriaBuilder.equal(playerGame.get("life"), life); i++;
        }
        i = 0;
        for (Integer bestVoice : bestVoices) {
            pBestVoices[i] = criteriaBuilder.equal(playerGame.get("bestVoices"), bestVoice); i++;
        }
        i = 0;
        for (Integer finalDecision : finalDecisions) {
            pFinalDecisions[i] = criteriaBuilder.equal(playerGame.get("finalDecision"), finalDecision); i++;
        }
        i = 0;
        for (Integer foul : fouls) {
            pFouls[i] = criteriaBuilder.equal(playerGame.get("fouls"), foul); i++;
        }

        Predicate predicatesNumbers = criteriaBuilder.or(pNumbers);
        Predicate predicatesRoles = criteriaBuilder.or(pRoles);
        Predicate predicatesLives = criteriaBuilder.or(pLives);
        Predicate predicatesBestVoices = criteriaBuilder.or(pBestVoices);
        Predicate predicatesFinalDecisions = criteriaBuilder.or(pFinalDecisions);
        Predicate predicatesFouls = criteriaBuilder.or(pFouls);

        Predicate summary  =  criteriaBuilder.and(
                predicatePlayer, predicateSeason, predicatesNumbers, predicatesRoles, predicatesLives,
                predicatesBestVoices, predicatesFinalDecisions, predicatesFouls);

        criteriaQuery.select(playerGame).where(summary);

        TypedQuery<Player> query = entityManager.createQuery(criteriaQuery);
        List<Player> playerGameList = query.getResultList();

        return playerGameList;
    } */
}
