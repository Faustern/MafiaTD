package com.tyhyidon.faust.game.rating;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.model.RatingSnapshot;

import java.util.Collection;
import java.util.List;

/**
 * Created by Василий on 03.01.14.
 */
public interface RatingCalculator {

    Double calcResultRating(Player player);

    Double calcLifeRating(Player player);

    Double calcVoicesRating(Player player);

    Double calcDecisionRating(Player player);

    Double calcFoulsRating(Player player);

    Double calculateRating(Player player);

    Double calculateTotalRating(List<Player> players);

    List<RatingSnapshot>  calculateSeasonRating(Collection<List<Player>> players);

}
