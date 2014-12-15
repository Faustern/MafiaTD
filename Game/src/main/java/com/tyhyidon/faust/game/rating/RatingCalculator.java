package com.tyhyidon.faust.game.rating;

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
}
