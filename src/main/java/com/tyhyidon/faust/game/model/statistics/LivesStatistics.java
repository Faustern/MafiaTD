package com.tyhyidon.faust.game.model.statistics;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.enums.Life;
import com.tyhyidon.faust.game.entity.enums.Role;
import com.tyhyidon.faust.game.utils.GamesUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by vasylsavytskyi on 06.06.15.
 */
public class LivesStatistics {

    private Map<Life, Long> games;
    private Map<Life, Long> winningGames;
    private Map<Life, Long> blackGames;

    public LivesStatistics(List<Player> players) {
        Collector<Player, ?, Map<Life, Long>> byLifeCollector = groupingBy(Player::getLife, counting());
        games = players.stream().collect(byLifeCollector);
        winningGames = players.stream().filter(GamesUtils::isWin).collect(byLifeCollector);
        blackGames = players.stream().filter(GamesUtils::isBlack).collect(byLifeCollector);
        Arrays.asList(Life.values()).stream().forEach(l -> {
            games.putIfAbsent(l, 0L);
            winningGames.putIfAbsent(l, 0L);
        });
    }

    public Map<Life, Long> getGames() {
        return games;
    }

    public Map<Life, Long> getWinningGames() {
        return winningGames;
    }

    public Map<Life, Long> getBlackGames() {
        return blackGames;
    }

}
