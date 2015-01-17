package com.tyhyidon.faust.game.filter;

import com.tyhyidon.faust.game.model.Result;

import java.util.Comparator;

/**
 */
public class ResultWinProcentComparator implements Comparator <Result> {

    @Override
    public int compare(Result sr1, Result sr2) {
        return (int)(sr2.getWinProcent()-sr1.getWinProcent());
    }
}
