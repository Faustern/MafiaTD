package com.tyhyidon.faust.game.legacy;

import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.entity.Member;
import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.legacy.filter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vasylsavytskyi on 20.01.15.
 */
//@Service
public class LegacyServiceImpl {

  /*  @Autowired
    private GameDAO gameDAO;

    public ResultPrediction getResultFromRoleDistribution(Integer season, List<Integer> roles1, List <Integer> roles2,
                                                          List <Integer> roles3, List <Integer> roles4, List <Integer> roles5,
                                                          List <Integer> roles6, List <Integer> roles7, List <Integer> roles8,
                                                          List <Integer> roles9, List <Integer> roles10)  {
        List <List<Integer>> roles = new ArrayList<List<Integer>>();
        roles.add(roles1);
        roles.add(roles2);
        roles.add(roles3);
        roles.add(roles4);
        roles.add(roles5);
        roles.add(roles6);
        roles.add(roles7);
        roles.add(roles8);
        roles.add(roles9);
        roles.add(roles10);

        List<Game> allGames = gameDAO.getGames(season);
        List<Game> successGames = new ArrayList<Game>();
        List <List <Game>> games = new ArrayList<List<Game>>();

        int i=0;
        for (List <Integer> role : roles) {
            i++;
            List <Game> gamesNumber = new ArrayList<Game>();
            List <Integer> numbers = new ArrayList<Integer>();
            numbers.add(i);
            List<Player> playerGames = gameDAO.getPlayerGamesByDefinedData(Constants.DEFAULT_PLAYER, season,
                    numbers, role, Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING,
                    Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING);
            for(Player playerGame : playerGames) {
                gamesNumber.add(playerGame.getGame());
            }
            games.add(gamesNumber);
        }

        for (Game game: allGames) {
            int j = 0;
            for (List <Game> gamesNumber : games) {
                j++;
                if (!gamesNumber.contains(game))
                    break;
                if (j==games.size())
                    successGames.add(game);
            }
        }

        return new ResultPrediction(successGames, allGames.size());

    }

    public List<PerGameStatistics> getStatisticsPerGame(Integer limit, Integer criteria, Integer season) {

        List<PerGameStatistics> perGameStatistics = new ArrayList<PerGameStatistics>();
        List<Member> players = gameDAO.getMembers();

        for (Member player : players) {
            List<Player> statisticsList = gameDAO.getPlayerGamesByDefinedData(player.getNickname(), season,
                    Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING,
                    Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING);

            if (statisticsList.size() >= limit) {
                double foulsPerGame = 0;
                double bestVoicesPerGame = 0;
                for (Player statistics : statisticsList) {
                    foulsPerGame += statistics.getFouls();
                    bestVoicesPerGame += statistics.getBestVoices();
                }
                foulsPerGame /= (double) statisticsList.size();
                bestVoicesPerGame /= (double) statisticsList.size();

                perGameStatistics.add(new PerGameStatistics(player.getNickname(), foulsPerGame, bestVoicesPerGame));
            }

        }

        switch (criteria) {
            case Constants.SORT_BY_BEST_VOICES_PER_GAME:
                Collections.sort(perGameStatistics, new PerGameStatisticsBestVoicesComparator());
                break;
            case Constants.SORT_BY_FOULS_PER_GAME:
                Collections.sort(perGameStatistics, new PerGameStatisticsFoulsComparator());
        }

        return perGameStatistics;
    }


    public List<Result> getSeasonPlot(String nickname, Integer role, Integer criteria, Integer season) {

        List<Result> results = new ArrayList<Result>();

        List<List<Player>> playerGames = new ArrayList<List<Player>>();

        ArrayList<Integer> roleList = new ArrayList<Integer>();

        if (nickname == "")
            nickname = null;

        if (!role.equals(0))
            roleList.add(role);

        for (int i = 1; i < 11; i++) {
            ArrayList<Integer> number = new ArrayList<Integer>();
            number.add(i);

            playerGames.add(gameDAO.getPlayerGamesByDefinedData(nickname, season, number, roleList,
                    Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING,
                    Constants.DISABLE_FILTERING));
        }

        for (List<Player> gamesNumber : playerGames) {
            int currentWins = 0;
            for (Player game : gamesNumber)
                if (game.isWin()) currentWins++;
            if (nickname != null) {
                results.add(new Result(gameDAO.getMember(nickname),
                        gamesNumber.size(), currentWins));
            } else {
                results.add(new Result(null,
                        gamesNumber.size(), currentWins));
            }
        }

        return results;
    }

    public List<Result> getStatisticsRequest(List<Integer> numbers, List<Integer> roles, List<Integer> lives,
                                             List<Integer> bestVoices, List<Integer> finalDecisions,
                                             List<Integer> fouls, Integer criteria, Integer limit, Integer season) {

        List<Member> players = gameDAO.getMembers();

        List<Result> results = new ArrayList<Result>();
        List<List<Player>> playersGames = new ArrayList<List<Player>>();
        List<List<Player>> playersGamesWin = new ArrayList<List<Player>>();

        for (Member player : players) {
            List<Player> playerGames = gameDAO.getPlayerGamesByDefinedData(player.getNickname(), season,
                    numbers, roles, lives, bestVoices, finalDecisions, fouls);

            if (playerGames.size() < limit) continue;

            playersGames.add(playerGames);

            List<Player> playerGamesWin = new ArrayList<Player>();
            for (Player playerGame : playerGames) {
                if (playerGame.isWin()) {
                    playerGamesWin.add(playerGame);
                }
            }
            playersGamesWin.add(playerGamesWin);

            results.add(new Result(player, playerGames.size(), playerGamesWin.size()));

        }

        switch (criteria) {
            case Constants.SORT_BY_GAMES:
                Collections.sort(results, new ResultGamesComparator());
                break;
            case Constants.SORT_BY_GAMES_WIN:
                Collections.sort(results, new ResultGamesWinComparator());
                break;
            case Constants.SORT_BY_WIN_PERCENT:
                Collections.sort(results, new ResultWinProcentComparator());
                break;
        }

        return results;
    }

    public PlayerStatistics getStatisticsPlayerRequest(String nickname, Integer season) {

        List<Player> statisticsList = gameDAO.getPlayerGamesByDefinedData(nickname, season,
                Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING,
                Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING);

        int games = statisticsList.size();
        int gamesWin = 0;
        int gamesDon = 0;
        int gamesDonWin = 0;
        int gamesMafia = 0;
        int gamesMafiaWin = 0;
        int gamesSheriff = 0;
        int gamesSheriffWin = 0;
        int gamesCitizen = 0;
        int gamesCitizenWin = 0;
        double fouls = 0;
        double bestVoices = 0;
        int finalDecisions = 0;
        int killedOneNight = 0;
        int killed = 0;
        int redAwayOneDay = 0;
        int redAway = 0;

        for (Player statistics : statisticsList) {

            if (statistics.isWin()) {
                gamesWin++;
            }

            if (statistics.getFinalDecision() != 0) {
                finalDecisions++;
            }

            fouls += statistics.getFouls();
            bestVoices += statistics.getBestVoices();

            if ((statistics.getLife() == Constants.LIFE_KILLED_ONE_NIGHT) ||
                    (statistics.getLife() == Constants.LIFE_KILLED_TWO_NIGHT) ||
                    (statistics.getLife() == Constants.LIFE_KILLED_THREE_NIGHT) ||
                    (statistics.getLife() == Constants.LIFE_KILLED_FOUR_NIGHT) ||
                    (statistics.getLife() == Constants.LIFE_KILLED_FIVE_PLUS_NIGHT)) {
                killed++;

                if (statistics.getLife() == Constants.LIFE_KILLED_ONE_NIGHT) {
                    killedOneNight++;
                }
            }

            if (((statistics.getRole() == Constants.ROLE_CITIZEN) ||
                    (statistics.getRole() == Constants.ROLE_SHERIFF)) &&
                    ((statistics.getLife() == Constants.LIFE_ZERO_DAY_AWAY) ||
                            (statistics.getLife() == Constants.LIFE_ONE_DAY_AWAY) ||
                            (statistics.getLife() == Constants.LIFE_TWO_DAY_AWAY) ||
                            (statistics.getLife() == Constants.LIFE_THREE_DAY_AWAY) ||
                            (statistics.getLife() == Constants.LIFE_FOUR_DAY_AWAY) ||
                            (statistics.getLife() == Constants.LIFE_FIVE_PLUS_DAY_AWAY))) {

                redAway++;
                if (statistics.getLife() == Constants.LIFE_ONE_DAY_AWAY) {
                    redAwayOneDay++;
                }

            }


            switch (statistics.getRole()) {
                case Constants.ROLE_DON:
                    gamesDon++;
                    if (statistics.isWin()) {
                        gamesDonWin++;
                    }
                    break;
                case Constants.ROLE_CITIZEN:
                    gamesCitizen++;
                    if (statistics.isWin()) {
                        gamesCitizenWin++;
                    }
                    break;
                case Constants.ROLE_MAFIA:
                    gamesMafia++;
                    if (statistics.isWin()) {
                        gamesMafiaWin++;
                    }
                    break;
                case Constants.ROLE_SHERIFF:
                    gamesSheriff++;
                    if (statistics.isWin()) {
                        gamesSheriffWin++;
                    }
                    break;
            }
        }

        double win = 0.0, winDon = 0.0, winMafia = 0.0, winCitizen = 0.0, winSheriff = 0.0;
        if (games > 0) win = 100.0 * gamesWin / games;
        if (gamesDon > 0) winDon = 100.0 * gamesDonWin / gamesDon;
        if (gamesMafia > 0) winMafia = 100.0 * gamesMafiaWin / gamesMafia;
        if (gamesCitizen > 0) winCitizen = 100.0 * gamesCitizenWin / gamesCitizen;
        if (gamesSheriff > 0) winSheriff = 100.0 * gamesSheriffWin / gamesSheriff;

        bestVoices = statisticsList.isEmpty() ? 0.0 : bestVoices / statisticsList.size();
        fouls = statisticsList.isEmpty() ? 0.0 : fouls / statisticsList.size();

        return new PlayerStatistics(nickname, games, win, gamesDon, winDon, gamesMafia, winMafia, gamesSheriff,
                winSheriff, gamesCitizen, winCitizen, fouls, bestVoices, finalDecisions, killedOneNight, killed,
                redAway, redAwayOneDay);
    }

*/
}
