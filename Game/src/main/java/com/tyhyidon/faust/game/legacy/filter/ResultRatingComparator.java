package com.tyhyidon.faust.game.legacy.filter;

import com.tyhyidon.faust.game.legacy.Result;
import com.tyhyidon.faust.game.rating.Constants;

import java.util.Comparator;

/**
 */
public class ResultRatingComparator implements Comparator <Result>{

    @Override
    public int compare (Result sr1, Result sr2) {
        if ((sr1.getGamesPlayed().size()>= Constants.RATING_GAMES_LIMIT) &&
                (sr2.getGamesPlayed().size()>= Constants.RATING_GAMES_LIMIT) ||
                (sr1.getGamesPlayed().size()< Constants.RATING_GAMES_LIMIT) &&
                (sr2.getGamesPlayed().size()< Constants.RATING_GAMES_LIMIT)) {
            if (sr2.getRating()-sr1.getRating()>0) {
                return 1;
            }
            else {
                return -1;
            }
        } else if (sr1.getGamesPlayed().size()>= Constants.RATING_GAMES_LIMIT) {
            return -1;
        } else {
            return 1;
        }
    }
}
