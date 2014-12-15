package com.tyhyidon.faust.game.player;

import com.tyhyidon.faust.game.model.Game;
import com.tyhyidon.faust.game.model.Statistics;
import com.tyhyidon.faust.game.model.Player;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Василий on 18.01.14.
 */
@Repository
@Transactional
public class GameDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Player> getAllPlayers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> criteriaQuery = criteriaBuilder.createQuery(Player.class);
        Root<Player> player = criteriaQuery.from(Player.class);
        criteriaQuery.select(player);
        TypedQuery<Player> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public void addPlayer(String nickname, String vkontakte) {
        Player player = new Player();
        player.setNickname(nickname);
        player.setVkontakte(vkontakte);
        entityManager.merge(player);
    }

    public Player getPlayerByNickname(String nickname) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> criteriaQuery = criteriaBuilder.createQuery(Player.class);
        Root<Player> player = criteriaQuery.from(Player.class);
        criteriaQuery.select(player).where(criteriaBuilder.equal(player.get("nickname"), nickname));
        TypedQuery<Player> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    public List<Game> getAllGames(Integer season) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> criteriaQuery = criteriaBuilder.createQuery(Game.class);
        Root<Game> game = criteriaQuery.from(Game.class);
        if (season>0) criteriaQuery.select(game).where(criteriaBuilder.equal(game.get("season"), season));
        else criteriaQuery.select(game);
        TypedQuery<Game> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public Game addGame(int season, int result, Player master, Date date) {
        Game game = new Game();
        game.setSeason(season);
        game.setDate(date);
        game.setResult(result);
        game.setMaster(master);
        return entityManager.merge(game);
    }

    public void addStatistics(Player player, Game game, int number, int role, int life, int bestVoices,
                                        int finalDecision, int fouls, double resultRating, double lifeRating,
                                        double bestVoicesRating, double finalDecisionRating, double foulsRating,
                                        double totalRating
    ) {
        Statistics gameStatistics = new Statistics();
        gameStatistics.setPlayer(player);
        gameStatistics.setGame(game);
        gameStatistics.setNumber(number);
        gameStatistics.setRole(role);
        gameStatistics.setLife(life);
        gameStatistics.setBestVoices(bestVoices);
        gameStatistics.setFinalDecision(finalDecision);
        gameStatistics.setFouls(fouls);
        gameStatistics.setResultRating(resultRating);
        gameStatistics.setLifeRating(lifeRating);
        gameStatistics.setBestVoicesRating(bestVoicesRating);
        gameStatistics.setFinalDecisionRating(finalDecisionRating);
        gameStatistics.setFoulsRating(foulsRating);
        gameStatistics.setTotalRating(totalRating);
        entityManager.merge(gameStatistics);
    }

    public void addStatistics(Statistics statistic) {;
        entityManager.merge(statistic);
    }

    public List<Statistics> getPlayerGamesByDefinedData(String nickname, Integer season,
                                         List <Integer> numbers, List <Integer> roles,
                                         List <Integer> lives, List <Integer> bestVoices,
                                         List <Integer> finalDecisions, List <Integer> fouls) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Statistics> criteriaQuery = criteriaBuilder.createQuery(Statistics.class);
        Root playerGame = criteriaQuery.from(Statistics.class);

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

        TypedQuery<Statistics> query = entityManager.createQuery(criteriaQuery);
        List<Statistics> playerGameList = query.getResultList();

        return playerGameList;
    }
}
