package com.tyhyidon.faust.game.legacy.filter;

import com.tyhyidon.faust.game.legacy.Result;

import java.util.Comparator;

/**
 */
public class ResultGamesWinComparator implements Comparator <Result>  {

    @Override
    public int compare(Result sr1, Result sr2) {
        return sr2.getGamesWin()-sr1.getGamesWin();
    }
}
