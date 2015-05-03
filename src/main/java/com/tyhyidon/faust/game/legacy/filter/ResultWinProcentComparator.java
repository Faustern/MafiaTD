package com.tyhyidon.faust.game.legacy.filter;

import com.tyhyidon.faust.game.legacy.Result;

import java.util.Comparator;

/**
 */
public class ResultWinProcentComparator implements Comparator <Result> {

    @Override
    public int compare(Result sr1, Result sr2) {
        return (int)(sr2.getWinProcent()-sr1.getWinProcent());
    }
}
