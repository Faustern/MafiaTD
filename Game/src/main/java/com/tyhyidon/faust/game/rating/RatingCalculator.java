package com.tyhyidon.faust.game.rating;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.model.RatingSnapshot;

import java.util.Collection;
import java.util.List;

/**
 * Created by Василий on 03.01.14.
 */
public interface RatingCalculator {

    Double calculateResultRating(Player player);

    Double calculateLifeRating(Player player);

    Double calculateBestVoicesRating(Player player);

    Double calculateFinalDecisionRating(Player player);

    Double calculateFoulsRating(Player player);

    Double calculateRating(Player player);

    Double calculateTotalRating(List<Player> players);

    List<RatingSnapshot>  calculateSeasonRating(Collection<List<Player>> players);

}
