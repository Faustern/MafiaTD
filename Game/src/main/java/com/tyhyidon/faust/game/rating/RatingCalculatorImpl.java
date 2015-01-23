package com.tyhyidon.faust.game.rating;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.model.RatingSnapshot;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

import static java.util.stream.Collectors.*;
import java.util.stream.Stream;

@Component
public class RatingCalculatorImpl implements RatingCalculator {

    @Resource
    private Properties ratingProperties;

    private String getResultString(int result) {
        switch (result) {
            case Constants.RESULT_MAFIA_CLEAR_WIN:
                return RatingConstants.RESULT_MAFIA_CLEAR_WIN;
            case Constants.RESULT_MAFIA_WIN:
                return RatingConstants.RESULT_MAFIA_WIN;
            case Constants.RESULT_CITY_WIN:
                return RatingConstants.RESULT_CITY_WIN;
            case Constants.RESULT_CITY_CLEAR_WIN:
                return RatingConstants.RESULT_CITY_CLEAR_WIN;
            default:
                return null;
        }
    }

    private String getRoleString(int role) {
        switch (role) {
            case Constants.ROLE_DON:
                return RatingConstants.ROLE_DON;
            case Constants.ROLE_MAFIA:
                return RatingConstants.ROLE_MAFIA;
            case Constants.ROLE_SHERIFF:
                return RatingConstants.ROLE_SHERIFF;
            case Constants.ROLE_CITIZEN:
                return RatingConstants.ROLE_CITIZEN;
            default:
                return null;
        }
    }

    private String getLifeString(int life) {
        switch (life) {
            case Constants.LIFE_KILLED_ONE_NIGHT:
                return RatingConstants.LIFE_KILLED_ONE_NIGHT;
            case Constants.LIFE_KILLED_TWO_NIGHT:
                return RatingConstants.LIFE_KILLED_TWO_NIGHT;
            case Constants.LIFE_KILLED_THREE_NIGHT:
                return RatingConstants.LIFE_KILLED_THREE_NIGHT;
            case Constants.LIFE_KILLED_FOUR_NIGHT:
                return RatingConstants.LIFE_KILLED_FOUR_NIGHT;
            case Constants.LIFE_KILLED_FIVE_PLUS_NIGHT:
                return RatingConstants.LIFE_KILLED_FIVE_PLUS_NIGHT;
            case Constants.LIFE_ZERO_DAY_AWAY:
                return RatingConstants.LIFE_ZERO_DAY_AWAY;
            case Constants.LIFE_ONE_DAY_AWAY:
                return RatingConstants.LIFE_ONE_DAY_AWAY;
            case Constants.LIFE_TWO_DAY_AWAY:
                return RatingConstants.LIFE_TWO_DAY_AWAY;
            case Constants.LIFE_THREE_DAY_AWAY:
                return RatingConstants.LIFE_THREE_DAY_AWAY;
            case Constants.LIFE_FOUR_DAY_AWAY:
                return RatingConstants.LIFE_FOUR_DAY_AWAY;
            case Constants.LIFE_FIVE_PLUS_DAY_AWAY:
                return RatingConstants.LIFE_FIVE_PLUS_DAY_AWAY;
            case Constants.LIFE_NOT_AWAY:
                return RatingConstants.LIFE_NOT_AWAY;
            default:
                return null;
        }
    }

    @Override
    public Double calculateResultRating(Player player) {
        return Double.parseDouble(ratingProperties.getProperty(getResultString(player.getResult()) +
                getRoleString(player.getRole()) + RatingConstants.RESULT));
    }

    @Override
    public Double calculateLifeRating(Player player) {
        return Double.parseDouble(ratingProperties.getProperty(getResultString(player.getResult()) +
                getRoleString(player.getRole()) + RatingConstants.LIFE + getLifeString(player.getLife())));
    }

    @Override
    public Double calculateBestVoicesRating(Player player) {
        return player.getBestVoices()*Double.parseDouble(ratingProperties.getProperty(getResultString(player.getResult()) +
                getRoleString(player.getRole()) + RatingConstants.VOICES_BEST));
    }

    @Override
    public Double calculateFinalDecisionRating(Player player) {
        if (player.getFinalDecision()>0) {
            return Double.parseDouble(ratingProperties.getProperty(getResultString(player.getResult()) + getRoleString(player.getRole()) +
                    RatingConstants.FINAL_DECISION))/player.getFinalDecision();
        } else {
            return 0.0;
        }
    }

    @Override
    public Double calculateFoulsRating(Player player) {
        return Double.parseDouble(ratingProperties.getProperty(getResultString(player.getResult()) + getRoleString(player.getRole()) +
                RatingConstants.FOULS + player.getFouls()));
    }

    @Override
    public Double calculateRating(Player player) {
        Double totalRating = calculateResultRating(player) + calculateLifeRating(player) +
                calculateBestVoicesRating(player) + calculateFinalDecisionRating(player)
                + calculateFoulsRating(player);
        if (totalRating>0) {
            return totalRating;
        } else {
            return 0.0;
        }
    }


    @Override
    public Double calculateTotalRating(List<Player> players) {
        return players.parallelStream().map(p -> calculateRating(p)).reduce((x, y) -> x + y).orElse(0.0)/players.size();
    }
    
    @Override
    public List<RatingSnapshot> calculateSeasonRating(Collection<List<Player>> data) {

        Map<Boolean, List<RatingSnapshot>> map = data.parallelStream().map(
                l -> (new RatingSnapshot(l.get(0).getMember(), calculateTotalRating(l), l.size()))).
                collect(partitioningBy(r -> r.getGames() >= Constants.RATING_GAMES_LIMIT));

        int max =  data.stream().map(l -> l.size()).max(Comparator.comparing(Integer::intValue)).orElse(0);
        map.get(true).parallelStream().forEach(r -> r.setRating(r.getRating() * (1 - (max - r.getGames()) / (2.0 * max))));

        return map.values().parallelStream().map(l -> l.stream().sorted(Comparator.comparing(r -> -r.getRating())).
                collect(toList())).reduce((x, y) -> Stream.concat(y.stream(), x.stream()).
                collect(toList())).orElse(Collections.emptyList());
    }

}
