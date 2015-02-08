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
    public Double calcResultRating(Player player) {
        return getRating(player.getResult() + "." + player.getRole() + ".RESULT");
    }

    @Override
    public Double calcLifeRating(Player player) {
        return getRating(player.getResult() + "." + player.getRole() + ".LIFE." + player.getLife());
    }

    @Override
    public Double calcVoicesRating(Player player) {
        return player.getBestVoices() * getRating(player.getResult()  + "." + player.getRole()  + ".VOICES");
    }

    @Override
    public Double calcDecisionRating(Player player) {
        return player.getFinalDecision()>0 ? getRating(player.getResult()  + "." + player.getRole()  + ".DECISION")/
                player.getFinalDecision() : 0.0;
    }

    @Override
    public Double calcFoulsRating(Player player) {
        return getRating(player.getResult()  + "." + player.getRole()  + ".FOULS." + player.getFouls());
    }

    @Override
    public Double calculateRating(Player player) {
        Double totalRating = calcResultRating(player) + calcLifeRating(player) + calcVoicesRating(player) +
                calcDecisionRating(player) + calcFoulsRating(player);
        return totalRating > 0 ? totalRating : 0.0;
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

    private Double getRating(String condition) {
        return Double.parseDouble(ratingProperties.getProperty(condition));
    }
}
