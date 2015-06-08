package com.tyhyidon.faust.game.model.statistics;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.enums.Life;
import com.tyhyidon.faust.game.entity.enums.Role;
import com.tyhyidon.faust.game.utils.GamesUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by vasylsavytskyi on 06.06.15.
 */
public class RolesStatistics {

    private Map<Role, Long> games;
    private Map<Role, Long> winningGames;

    public RolesStatistics(List<Player> players) {
        Collector<Player, ?, Map<Role, Long>> byRoleCollector = groupingBy(Player::getRole, counting());
        games = players.stream().collect(byRoleCollector);
        winningGames = players.stream().filter(GamesUtils::isWin).collect(byRoleCollector);
        Arrays.asList(Role.values()).stream().forEach(r -> {
            games.putIfAbsent(r, 0L);
            winningGames.putIfAbsent(r, 0L);
        });
    }

    public Map<Role, Long> getGames() {
        return games;
    }

    public Map<Role, Long> getWinningGames() {
        return winningGames;
    }

}
