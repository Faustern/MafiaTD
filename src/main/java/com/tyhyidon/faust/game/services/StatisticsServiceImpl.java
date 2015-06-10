package com.tyhyidon.faust.game.services;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.enums.Life;
import com.tyhyidon.faust.game.entity.enums.Role;
import com.tyhyidon.faust.game.entity.enums.Season;
import com.tyhyidon.faust.game.model.statistics.*;
import com.tyhyidon.faust.game.repositories.PlayerRepository;
import com.tyhyidon.faust.game.utils.GamesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by vasylsavytskyi on 07.06.15.
 */
@Service
public class StatisticsServiceImpl {

    private Collector<Player, ?, Map<Role, Long>> byRoleCollector = groupingBy(Player::getRole, counting());
    private Collector<Player, ?, Map<Life, Long>> byLifeCollector = groupingBy(Player::getLife, counting());
    Collector<Player, ?, Map<Integer, Long>> byPositionCollector = groupingBy(Player::getNumber, counting());
    Collector<Player, ?, Map<Role, Map<Integer, Long>>> byRoleAndPositonCollector =
            groupingBy(Player::getRole, byPositionCollector);

    @Autowired
    private PlayerRepository playerRepository;

    public MemberStatistics getMemberStatistics(String nickname, Season season)
            throws InstantiationException, IllegalAccessException {
        List<Player> players = playerRepository.findByMemberNicknameAndGameSeason(nickname, season);
        MemberStatistics memberStatistics = new MemberStatistics();
        memberStatistics.setPositions(getPositionStatistics(players));
        memberStatistics.setRoles((RolesStatistics)getBaseStatistics(
                players, byRoleCollector, Role.values(), RolesStatistics.class));
        memberStatistics.setLives((LivesStatistics)getBaseStatistics(
                players, byLifeCollector, Life.values(), LivesStatistics.class));
        return memberStatistics;
    }

    public PositionsStatistics getPositionStatistics(List<Player> players) {
        List<Long> games = new ArrayList<>();
        List<Long> winningGames = new ArrayList<>();
        Map<Role, List<Long>> roleGames = new HashMap<>();
        Map<Role, List<Long>> roleWinningGames = new HashMap<>();

        Map<Integer, Long> gamesMap = players.stream().collect(byPositionCollector);
        Map<Integer, Long> winningGamesMap = players.stream().filter(GamesUtils::isWin).collect(byPositionCollector);
        Map<Role, Map<Integer, Long>> roleGamesMap = players.stream().collect(byRoleAndPositonCollector);
        Map<Role, Map<Integer, Long>> roleWinningGamesMap =
                players.stream().filter(GamesUtils::isWin).collect(byRoleAndPositonCollector);
        IntStream.range(1, 11).forEach(p -> {
            games.add(gamesMap.getOrDefault(p, 0L));
            winningGames.add(winningGamesMap.getOrDefault(p, 0L));
            Arrays.asList(Role.values()).stream().forEach(r -> {
                roleGames.putIfAbsent(r, new ArrayList<>());
                roleGames.get(r).add(roleGamesMap.getOrDefault(r, new HashMap<>()).getOrDefault(p, 0L));
                roleWinningGames.putIfAbsent(r, new ArrayList<>());
                roleWinningGames.get(r).add(roleWinningGamesMap.getOrDefault(r, new HashMap<>()).getOrDefault(p, 0L));
            });
        });
        PositionsStatistics positionStatistics = new PositionsStatistics();
        positionStatistics.setGames(games);
        positionStatistics.setWinningGames(winningGames);
        positionStatistics.setRoleGames(roleGames);
        positionStatistics.setRoleWinningGames(roleWinningGames);
        return positionStatistics;
    }

    private  <T> BaseStatistics getBaseStatistics(
            List<Player> players, Collector<Player, ?, Map<T, Long>> collector, T[] factorArray ,
            Class<? extends BaseStatistics> clazz) throws IllegalAccessException, InstantiationException {
        List<Long> games = new ArrayList<>();
        List<Long> winningGames = new ArrayList<>();
        Map<T, Long> gamesMap = players.stream().collect(collector);
        Map<T, Long> winningGamesMap = players.stream().filter(GamesUtils::isWin).collect(collector);
        Arrays.asList(factorArray).stream().forEach(f -> {
            games.add(gamesMap.getOrDefault(f, 0L));
            winningGames.add(winningGamesMap.getOrDefault(f, 0L));
        });
        BaseStatistics baseStatistics = clazz.newInstance();
        baseStatistics.setGames(games);
        baseStatistics.setWinningGames(winningGames);
        return baseStatistics;
    }

}
