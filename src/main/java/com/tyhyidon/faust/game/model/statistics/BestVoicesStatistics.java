package com.tyhyidon.faust.game.model.statistics;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.enums.Role;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by vasylsavytskyi on 07.06.15.
 */
public class BestVoicesStatistics {

    private Map<Integer, Long> games;
    private Map<Role, Map<Integer, Long>> roleGames;

    public BestVoicesStatistics(List<Player> players) {
        Collector<Player, ?, Map<Integer, Long>> byBestVoiceCollector = groupingBy(Player::getBestVoices, counting());
        Collector<Player, ?, Map<Role, Map<Integer, Long>>> byRoleAndBestVoiceCollector =
                groupingBy(Player::getRole, byBestVoiceCollector);
        games = players.stream().collect(byBestVoiceCollector);
        roleGames = players.stream().collect(byRoleAndBestVoiceCollector);
    }

    public Map<Integer, Long> getGames() {
        return games;
    }

    public Map<Role, Map<Integer, Long>> getRoleGames() {
        return roleGames;
    }

}
