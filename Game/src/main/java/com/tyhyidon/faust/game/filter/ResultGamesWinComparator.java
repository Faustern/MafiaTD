package com.tyhyidon.faust.game.filter;

import java.util.Comparator;

/**
 */
public class ResultGamesWinComparator implements Comparator <Result>  {

    @Override
    public int compare(Result sr1, Result sr2) {
        return sr2.getGamesWin()-sr1.getGamesWin();
    }
}
