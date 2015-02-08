package com.tyhyidon.faust.game.legacy.filter;

import com.tyhyidon.faust.game.legacy.Result;

import java.util.Comparator;

/**
 * Created by Василий on 02.02.14.
 */
public class ResultGamesComparator implements Comparator <Result> {
    @Override
    public int compare (Result sr1, Result sr2) {
        return sr2.getGames()-sr1.getGames();
    }
}