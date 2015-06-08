package com.tyhyidon.faust.game.model.statistics;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.enums.Role;
import com.tyhyidon.faust.game.utils.GamesUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by vasylsavytskyi on 07.06.15.
 */
public class FoulsStatistics {

    private Map<Integer, Long> games;
    private Map<Integer, Long> winningGames;
    private Map<Role, Map<Integer, Long>> roleGames;
    private Map<Role, Map<Integer, Long>> roleWinningGames;

    public FoulsStatistics(List<Player> players) {
        Collector<Player, ?, Map<Integer, Long>> byFoulCollector = groupingBy(Player::getFouls, counting());
        Collector<Player, ?, Map<Role, Map<Integer, Long>>> byRoleAndFoulCollector =
                groupingBy(Player::getRole, byFoulCollector);
        games = players.stream().collect(byFoulCollector);
        winningGames = players.stream().filter(GamesUtils::isWin).collect(byFoulCollector);
        roleGames = players.stream().collect(byRoleAndFoulCollector);
        roleWinningGames = players.stream().filter(GamesUtils::isWin).collect(byRoleAndFoulCollector);
    }

    public Map<Integer, Long> getGames() {
        return games;
    }

    public Map<Integer, Long> getWinningGames() {
        return winningGames;
    }

    public Map<Role, Map<Integer, Long>> getRoleGames() {
        return roleGames;
    }

    public Map<Role, Map<Integer, Long>> getRoleWinningGames() {
        return roleWinningGames;
    }
}
