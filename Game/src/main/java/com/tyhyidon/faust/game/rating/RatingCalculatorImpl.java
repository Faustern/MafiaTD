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

    @Override
    public Double calculateResultRating(Player player) {
        return Double.parseDouble(ratingProperties.getProperty(
                player.getResult().toString().toLowerCase() + "." +
                        player.getRole().toString().toLowerCase() + ".result"));
    }

    @Override
    public Double calculateLifeRating(Player player) {
        return Double.parseDouble(ratingProperties.getProperty(
                player.getResult().toString().toLowerCase() + "." + player.getRole().toString().toLowerCase() +
                        ".life." + player.getLife().toString().toLowerCase()));
    }

    @Override
    public Double calculateBestVoicesRating(Player player) {
        return player.getBestVoices()*Double.parseDouble(ratingProperties.getProperty(
                player.getResult().toString().toLowerCase()  + "." + player.getRole().toString().toLowerCase()  +
                        ".best_voices"));
    }

    @Override
    public Double calculateFinalDecisionRating(Player player) {
        if (player.getFinalDecision()>0) {
            return Double.parseDouble(ratingProperties.getProperty(player.getResult().toString().toLowerCase()  + "." +
                            player.getRole().toString().toLowerCase()  + ".final_decision"))/player.getFinalDecision();
        } else {
            return 0.0;
        }
    }

    @Override
    public Double calculateFoulsRating(Player player) {
        return Double.parseDouble(ratingProperties.getProperty(player.getResult().toString().toLowerCase()  + "." +
                        player.getRole().toString().toLowerCase()  + ".fouls." + player.getFouls()));
    }

    @Override
    public Double calculateRating(Player player) {
        Double totalRating = calculateResultRating(player) + calculateLifeRating(player) +
                calculateBestVoicesRating(player) + calculateFinalDecisionRating(player) + calculateFoulsRating(player);
        if (totalRating>0) {
            return totalRating;
        } else {
            return 0.0;
        }
    }

    @Override
    public Double calculateTotalRating(List<Player> players) {
        return players.parallelStream().map(this::calculateRating).reduce((x, y) -> x + y).orElse(0.0)/players.size();
    }
    
    @Override
    public List<RatingSnapshot> calculateSeasonRating(Collection<List<Player>> data) {

        Map<Boolean, List<RatingSnapshot>> map = data.parallelStream().map(
                l -> (new RatingSnapshot(l.get(0).getMember(), calculateTotalRating(l), l.size()))).
                collect(partitioningBy(r -> r.getGames() >= 11));

        int max =  data.stream().map(List::size).max(Comparator.comparing(Integer::intValue)).orElse(0);
        map.get(true).parallelStream().forEach(r -> r.setRating(r.getRating() * (1 - (max - r.getGames()) / (2.0 * max))));

        return map.values().parallelStream().map(l -> l.stream().sorted(Comparator.comparing(r -> -r.getRating())).
                collect(toList())).reduce((x, y) -> Stream.concat(y.stream(), x.stream()).
                collect(toList())).orElse(Collections.emptyList());
    }
}
