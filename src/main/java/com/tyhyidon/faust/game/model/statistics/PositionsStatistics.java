package com.tyhyidon.faust.game.model.statistics;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.enums.Role;
import com.tyhyidon.faust.game.utils.GamesUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by vasylsavytskyi on 07.06.15.
 */
public class PositionsStatistics {

    private Map<Integer, Long> games;
    private Map<Integer, Long> winningGames;
    private Map<Role, Map<Integer, Long>> roleGames;
    private Map<Role, Map<Integer, Long>> roleWinningGames;

    public PositionsStatistics(List<Player> players) {
        Collector<Player, ?, Map<Integer, Long>> byPositionCollector = groupingBy(Player::getNumber, counting());
        Collector<Player, ?, Map<Role, Map<Integer, Long>>> byRoleAndPositonCollector =
                groupingBy(Player::getRole, byPositionCollector);
        games = players.stream().collect(byPositionCollector);
        winningGames = players.stream().filter(GamesUtils::isWin).collect(byPositionCollector);
        roleGames = players.stream().collect(byRoleAndPositonCollector);
        roleWinningGames = players.stream().filter(GamesUtils::isWin).collect(byRoleAndPositonCollector);
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
