package com.tyhyidon.faust.game.rating;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.model.PlayerSnapshot;

/**
 * Created by Василий on 03.01.14.
 */
public interface RatingCalculator {

    Double calculateResultRating();

    Double calculateLifeRating();

    Double calculateBestVoicesRating();

    Double calculateFinalDecisionRating();

    Double calculateFoulsRating();

    Double calculateTotalRating();

    Double calculateRating(PlayerSnapshot playerSnapshot);

    void calculateRating(Player player);
}
