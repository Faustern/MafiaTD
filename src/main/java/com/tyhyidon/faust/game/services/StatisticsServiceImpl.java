package com.tyhyidon.faust.game.services;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.enums.Life;
import com.tyhyidon.faust.game.entity.enums.Result;
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
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.*;

/**
 * Created by vasylsavytskyi on 07.06.15.
 */
@Service
public class StatisticsServiceImpl {

    private static final int PLAYER_AMOUNT = 10;
    private Collector<Player, ?, Map<Role, Long>> byRoleCollector = groupingBy(Player::getRole, counting());
    private Collector<Player, ?, Map<Life, Long>> byLifeCollector = groupingBy(Player::getLife, counting());
    private Collector<Player, ?, Map<Role, Map<Life, Long>>> byRoleAndLifeCollector =
            groupingBy(Player::getRole, byLifeCollector);
    private Collector<Player, ?, Map<Integer, Long>> byPositionCollector = groupingBy(Player::getNumber, counting());
    private Collector<Player, ?, Map<Role, Map<Integer, Long>>> byRoleAndPositonCollector =
            groupingBy(Player::getRole, byPositionCollector);
    private Collector<Player, ?, Map<Result, Long>> byResultCollector =
            groupingBy(p -> p.getGame().getResult(), counting());
    private Collector<Player, ?, Map<Role,Double>> byRoleAndBestVoicesAverageCollector =
            groupingBy(Player::getRole, averagingInt(Player::getBestVoices));
    private Collector<Player, ?, Map<Role,Double>> byRoleAndFoulsAverageCollector =
            groupingBy(Player::getRole, averagingInt(Player::getFouls));

    @Autowired
    private PlayerRepository playerRepository;

    public MemberStatistics getMemberStatistics(String nickname, Season season)
            throws InstantiationException, IllegalAccessException {
        List<Player> players = getPlayers(nickname, season);
        MemberStatistics memberStatistics = new MemberStatistics();
        Integer[] range = new Integer[PLAYER_AMOUNT];
        memberStatistics.setPositions((PositionsStatistics) getBaseByRoleStatistics(
                players, byPositionCollector, byRoleAndPositonCollector,
                IntStream.rangeClosed(1, PLAYER_AMOUNT - 1).boxed().collect(toList()).toArray(range),
                PositionsStatistics.class));
        memberStatistics.setRoles((RolesStatistics) getBaseStatistics(
                players, byRoleCollector, Role.values(), RolesStatistics.class));
        memberStatistics.setLives((LivesStatistics) getBaseByRoleStatistics(
                players, byLifeCollector, byRoleAndLifeCollector, Life.values(), LivesStatistics.class));
        memberStatistics.setBestVoices((BestVoicesStatistics)
                getAverageByRoleStatistics(players, byRoleAndBestVoicesAverageCollector, BestVoicesStatistics.class));
        memberStatistics.setFouls((FoulsStatistics) getAverageByRoleStatistics(players, byRoleAndFoulsAverageCollector,
                FoulsStatistics.class));
        memberStatistics.setResults((ResultsStatistics) getBaseStatistics(
                players, byResultCollector, Result.values(), ResultsStatistics.class));
        return memberStatistics;
    }

    public <T> BaseByRoleStatistics getBaseByRoleStatistics(List<Player> players,
            Collector<Player, ?, Map<T, Long>> collector, Collector<Player, ?, Map<Role, Map<T, Long>>> byRoleCollector,
            T[] factorArray, Class<? extends BaseByRoleStatistics> clazz)
            throws IllegalAccessException, InstantiationException {
        Map<Role, List<Long>> allByRole = new HashMap<>();
        Map<Role, List<Long>> winsByRole = new HashMap<>();
        Map<Role, List<Long>> clearWinsByRole = new HashMap<>();
        Map<Role, Map<T, Long>> allByRoleMap = players.stream().collect(byRoleCollector);
        Map<Role, Map<T, Long>> winsByRoleMap =
                players.stream().filter(GamesUtils::isWin).collect(byRoleCollector);
        Map<Role, Map<T, Long>> clearWinsByRoleMap =
                players.stream().filter(GamesUtils::isClearWin).collect(byRoleCollector);
        Arrays.asList(factorArray).stream().forEach(f -> {
            Arrays.asList(Role.values()).stream().forEach(r -> {
                allByRole.putIfAbsent(r, new ArrayList<>());
                allByRole.get(r).add(allByRoleMap.getOrDefault(r, new HashMap<>()).getOrDefault(f, 0L));
                winsByRole.putIfAbsent(r, new ArrayList<>());
                winsByRole.get(r).add(winsByRoleMap.getOrDefault(r, new HashMap<>()).getOrDefault(f, 0L));
                clearWinsByRole.putIfAbsent(r, new ArrayList<>());
                clearWinsByRole.get(r).add(clearWinsByRoleMap.getOrDefault(r, new HashMap<>()).getOrDefault(f, 0L));
            });
        });
        BaseByRoleStatistics baseByRoleStatistics =
                (BaseByRoleStatistics)getBaseStatistics(players, collector, factorArray, clazz);
        baseByRoleStatistics.setAllByRole(allByRole);
        baseByRoleStatistics.setWinsByRole(winsByRole);
        baseByRoleStatistics.setClearWinsByRole(clearWinsByRole);
        return baseByRoleStatistics;
    }

    private List<Player> getPlayers(String nickname, Season season) {
        switch (season){
            case ALL:
                return nickname.equals("")
                        ? StreamSupport.stream(playerRepository.findAll().spliterator(), true).collect(toList())
                        : playerRepository.findByMemberNickname(nickname);
            default:
                return nickname.equals("")
                        ? playerRepository.findByGameSeason(season)
                        : playerRepository.findByMemberNicknameAndGameSeason(nickname, season);
        }
    }

    private <T> BaseStatistics getBaseStatistics(
            List<Player> players, Collector<Player, ?, Map<T, Long>> collector, T[] factorArray ,
            Class<? extends BaseStatistics> clazz) throws IllegalAccessException, InstantiationException {
        List<Long> all = new ArrayList<>();
        List<Long> wins = new ArrayList<>();
        List<Long> clearWins = new ArrayList<>();
        Map<T, Long> allMap = players.stream().collect(collector);
        Map<T, Long> winsMap = players.stream().filter(GamesUtils::isWin).collect(collector);
        Map<T, Long> clearWinsMap = players.stream().filter(GamesUtils::isClearWin).collect(collector);
        Arrays.asList(factorArray).stream().forEach(f -> {
            all.add(allMap.getOrDefault(f, 0L));
            wins.add(winsMap.getOrDefault(f, 0L));
            clearWins.add(clearWinsMap.getOrDefault(f, 0L));
        });
        BaseStatistics baseStatistics = clazz.newInstance();
        baseStatistics.setAll(all);
        baseStatistics.setWins(wins);
        baseStatistics.setClearWins(clearWins);
        return baseStatistics;
    }

    public AverageByRoleStatistics getAverageByRoleStatistics(List<Player> players,
            Collector<Player, ?, Map<Role, Double>> byRoleCollector, Class<? extends AverageByRoleStatistics> clazz)
            throws IllegalAccessException, InstantiationException {
        List<Double> averageByRole= new ArrayList<>();
        Map<Role, Double> averageByRoleMap = players.stream().collect(byRoleCollector);
        Arrays.asList(Role.values()).stream().forEach(r -> {
            averageByRole.add(averageByRoleMap.getOrDefault(r, 0.0));
        });
        AverageByRoleStatistics averageByRoleStatistics = clazz.newInstance();
        averageByRoleStatistics.setAverageByRole(averageByRole);
        return averageByRoleStatistics;
    }

}
