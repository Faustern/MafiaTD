package com.tyhyidon.faust.game.filter;

import java.util.Comparator;

/**
 */
public class PerGameStatisticsFoulsComparator implements Comparator<PerGameStatistics> {
    @Override
    public int compare (PerGameStatistics sr1, PerGameStatistics sr2) {
        return (sr2.getFoulsPerGame()-sr1.getFoulsPerGame())>0.0 ? 1 : -1;
    }
}
