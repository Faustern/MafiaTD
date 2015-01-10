package com.tyhyidon.faust.game.logic;

import com.tyhyidon.faust.game.filter.*;
import com.tyhyidon.faust.game.model.Game;
import com.tyhyidon.faust.game.model.Player;
import com.tyhyidon.faust.game.model.Statistics;
import com.tyhyidon.faust.game.player.Constants;
import com.tyhyidon.faust.game.player.GameDAO;
import com.tyhyidon.faust.game.rating.RatingCalculatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 */
@Component
public class Logic {
    private static Logger logger = LoggerFactory.getLogger(Logic.class);

    @Autowired
    private GameDAO gameDAO;

    @Resource
    private Properties ratingProperties;

    public static final String DATE_FORMAT_FOR_UI_AS_STRING = "yyyy/mm/dd";

    public List<String> addPlayerToDB(String nickname, String vkontakte) {
        gameDAO.addPlayer(nickname, vkontakte);
        return getPlayersNicknames();
    }

    @Transactional
    public List<Result> saveGameIntoDB(String date, Integer season, String masterNickname, Integer result, List<String> nickNames,
                                       List<Integer> roles, List<Integer> lives, List<Integer> bestVoices,
                                       List<Integer> finalDecisions, List<Integer> fouls) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_FOR_UI_AS_STRING);
        Date parsedDate =  format.parse(date);

        List<Statistics> statistics = getStatistics(result, season, date, masterNickname, nickNames, roles, lives, bestVoices, finalDecisions, fouls);
        Game game = gameDAO.addGame(season, result, gameDAO.getPlayerByNickname(masterNickname), parsedDate);

        for (Statistics statistic : statistics) {
            statistic.setGame(game);
            gameDAO.addStatistics(statistic);
        }
        return showRating(season);
    }

    public List<String> getPlayersNicknames() {
        List<String> nicknames = new ArrayList<String>();
        List<Player> players = gameDAO.getAllPlayers();
        for (Player player : players) {
            nicknames.add(player.getNickname());
        }
        return nicknames;
    }

    public List<Statistics> getStatistics(Integer result, Integer season, String date, String masterNickname,
                                          List<String> nickNames, List<Integer> roles, List<Integer> lives,
                                          List<Integer> bestVoices, List<Integer> finalDecisions, List<Integer> fouls) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_FOR_UI_AS_STRING);
        Date parsedDate =  (Date)format.parse(date);

        List<Statistics> statistics = new ArrayList<Statistics>();

        Iterator<String> nickNamesIterator = nickNames.iterator();
        Iterator<Integer> rolesIterator = roles.iterator();
        Iterator<Integer> livesIterator = lives.iterator();
        Iterator<Integer> bestVoicesIterator = bestVoices.iterator();
        Iterator<Integer> finalDecisionsIterator = finalDecisions.iterator();
        Iterator<Integer> foulsIterator = fouls.iterator();
        for (int i = 0; i < nickNames.size(); i++) {
            String nicknamesItem = nickNamesIterator.next();
            Integer rolesItem = rolesIterator.next();
            Integer livesItem = livesIterator.next();
            Integer bestVoicesItem = bestVoicesIterator.next();
            Integer finalDecisionsItem = finalDecisionsIterator.next();
            Integer foulsItem = foulsIterator.next();

            statistics.add(new Statistics(gameDAO.getPlayerByNickname(nicknamesItem),
                    new Game(result, season, parsedDate, gameDAO.getPlayerByNickname(masterNickname)),
                    i + 1, rolesItem, livesItem, bestVoicesItem, finalDecisionsItem, foulsItem,
                    new RatingCalculatorImpl(result, rolesItem, livesItem, bestVoicesItem,
                            finalDecisionsItem, foulsItem, ratingProperties)));
        }

        return statistics;
    }

    public PlayerStatistics getStatisticsPlayerRequest(String nickname, Integer season) {

        List<Statistics> statisticsList = gameDAO.getPlayerGamesByDefinedData(nickname, season,
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

        for (Statistics statistics : statisticsList) {

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


    public List<Result> getStatisticsRequest(List<Integer> numbers, List<Integer> roles, List<Integer> lives,
                                             List<Integer> bestVoices, List<Integer> finalDecisions,
                                             List<Integer> fouls, Integer criteria, Integer limit, Integer season) {

        List<Player> players = gameDAO.getAllPlayers();

        List<Result> results = new ArrayList<Result>();
        List<List<Statistics>> playersGames = new ArrayList<List<Statistics>>();
        List<List<Statistics>> playersGamesWin = new ArrayList<List<Statistics>>();

        for (Player player : players) {
            List<Statistics> playerGames = gameDAO.getPlayerGamesByDefinedData(player.getNickname(), season,
                    numbers, roles, lives, bestVoices, finalDecisions, fouls);

            if (playerGames.size() < limit) continue;

            playersGames.add(playerGames);

            List<Statistics> playerGamesWin = new ArrayList<Statistics>();
            for (Statistics playerGame : playerGames) {
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

    public List<Result> getSeasonPlot(String nickname, Integer role, Integer criteria, Integer season) {

        List<Result> results = new ArrayList<Result>();

        List<List<Statistics>> playerGames = new ArrayList<List<Statistics>>();

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

        for (List<Statistics> gamesNumber : playerGames) {
            int currentWins = 0;
            for (Statistics game : gamesNumber)
                if (game.isWin()) currentWins++;
            if (nickname != null) {
                results.add(new Result(gameDAO.getPlayerByNickname(nickname),
                        gamesNumber.size(), currentWins));
            } else {
                results.add(new Result(null,
                        gamesNumber.size(), currentWins));
            }
        }

        return results;
    }


    public List<PerGameStatistics> getStatisticsPerGame(Integer limit, Integer criteria, Integer season) {

        List<PerGameStatistics> perGameStatistics = new ArrayList<PerGameStatistics>();
        List<Player> players = gameDAO.getAllPlayers();

        for (Player player : players) {
            List<Statistics> statisticsList = gameDAO.getPlayerGamesByDefinedData(player.getNickname(), season,
                    Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING,
                    Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING);

            if (statisticsList.size() >= limit) {
                double foulsPerGame = 0;
                double bestVoicesPerGame = 0;
                for (Statistics statistics : statisticsList) {
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

    public List<Result> showRating(Integer season) {

        ArrayList<Result> results = new ArrayList<Result>();
        List<Player> players = gameDAO.getAllPlayers();
        Integer maxSize = 0;

        for (Player player : players) {
            List<Statistics> playerGames = gameDAO.getPlayerGamesByDefinedData(player.getNickname(), season,
                    Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING,
                    Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING);

            if (maxSize < playerGames.size()) {
                maxSize = playerGames.size();
            }

            Double playerRating = 0.0;
            int gamesWin = 0;

            for (Statistics playerGame : playerGames) {
                if (playerGame.isWin()) {
                    gamesWin++;
                }
                playerRating += playerGame.getTotalRating();
            }

            if (playerGames.size()>1) {
                playerRating /= playerGames.size();
            }

            results.add(new Result(player, playerGames.size(), gamesWin,
                    playerGames, playerRating));
        }

        for (Result result : results) {
            if (result.getGamesPlayed().size() >= Constants.RATING_GAMES_LIMIT) {
                result.setRating(result.getRating() *
                        (1 - (maxSize - result.getGamesPlayed().size()) / (2.0 * maxSize)));
            }
        }

        Collections.sort(results, new ResultRatingComparator());
        Collections.sort(results, new ResultRatingComparator());

        return results;
    }

    public ResultPrediction getResultFromRoleDistribution(Integer season, List <Integer> roles1, List <Integer> roles2,
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

        List<Game> allGames = gameDAO.getAllGames(season);
        List<Game> successGames = new ArrayList<Game>();
        List <List <Game>> games = new ArrayList<List<Game>>();

        int i=0;
        for (List <Integer> role : roles) {
            i++;
            List <Game> gamesNumber = new ArrayList<Game>();
            List <Integer> numbers = new ArrayList<Integer>();
            numbers.add(i);
            List<Statistics> playerGames = gameDAO.getPlayerGamesByDefinedData(Constants.DEFAULT_PLAYER, season,
                    numbers, role, Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING,
                    Constants.DISABLE_FILTERING, Constants.DISABLE_FILTERING);
            for(Statistics playerGame : playerGames) {
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
}
