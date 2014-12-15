package com.tyhyidon.faust.game.filter;

import java.util.Comparator;

/**
 */
public class PerGameStatisticsBestVoicesComparator implements Comparator<PerGameStatistics>{
    @Override
    public int compare (PerGameStatistics sr1, PerGameStatistics sr2) {
        return (sr2.getBestVoicesPerGame()-sr1.getBestVoicesPerGame())>0.0 ? 1 : -1;
    }
}
