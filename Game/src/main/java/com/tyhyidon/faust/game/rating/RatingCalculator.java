package com.tyhyidon.faust.game.rating;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.model.PlayerSnapshot;
import com.tyhyidon.faust.game.model.RatingSnapshot;

import java.util.Collection;
import java.util.List;

/**
 * Created by Василий on 03.01.14.
 */
public interface RatingCalculator {

    Double calculateResultRating(PlayerSnapshot player);

    Double calculateLifeRating(PlayerSnapshot player);

    Double calculateBestVoicesRating(PlayerSnapshot player);

    Double calculateFinalDecisionRating(PlayerSnapshot player);

    Double calculateFoulsRating(PlayerSnapshot player);

    Double calculateRating(PlayerSnapshot player);

    Double calculateTotalRating(List<PlayerSnapshot> players);

    List<RatingSnapshot>  calculateSeasonRating(Collection<List<PlayerSnapshot>> players);

    void fillRating(Player player);
}
