package com.tyhyidon.faust.game.model.statistics;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.enums.Result;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by vasylsavytskyi on 07.06.15.
 */
public class ResultsStatistics {

    private Map<Result, Long> games;

    public ResultsStatistics(List<Player> players) {
        Collector<Player, ?, Map<Result, Long>> byResultCollector = groupingBy(p -> p.getGame().getResult(), counting());
        games = players.stream().collect(byResultCollector);
    }

    public Map<Result, Long> getGames() {
        return games;
    }

}
