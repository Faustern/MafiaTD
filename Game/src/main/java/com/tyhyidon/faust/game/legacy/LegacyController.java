package com.tyhyidon.faust.game.legacy;

import com.tyhyidon.faust.game.legacy.filter.PerGameStatistics;
import com.tyhyidon.faust.game.legacy.filter.PlayerStatistics;
import com.tyhyidon.faust.game.legacy.filter.ResultPrediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by vasylsavytskyi on 20.01.15.
 */
//@Controller
public class LegacyController {

 /*   @Autowired
    LegacyServiceImpl legacyService;

    @RequestMapping(value = {"/statisticsPlayer"})
    public PlayerStatistics getStatisticsPlayerRequest(@RequestParam(value="nickname", required = false) String nickname,
                                                       @RequestParam(value="season", required = false) Integer season) {
        return legacyService.getStatisticsPlayerRequest(nickname, season);
    }

    @RequestMapping(value = {"/statistics"})
    public List<Result> getStatisticsRequest(@RequestParam(value="season", required = true) Integer season,
                                             @RequestParam(value="numbers[]", required = false) List<Integer> numbers,
                                             @RequestParam(value="roles[]", required = true) List <Integer> roles,
                                             @RequestParam(value="lives[]", required = true) List<Integer> lives,
                                             @RequestParam(value="bestVoices[]", required = true) List<Integer> bestVoices,
                                             @RequestParam(value="finalDecisions[]", required = true) List<Integer> finalDecisions,
                                             @RequestParam(value="fouls[]", required = true) List<Integer> fouls,
                                             @RequestParam(required = false) Integer criteria,
                                             @RequestParam(required = false) Integer limit) {
        return legacyService.getStatisticsRequest(numbers, roles, lives, bestVoices, finalDecisions, fouls, criteria, limit, season);
    }

    @RequestMapping(value = {"/season_plot"})
    public List <Result> getSeasonPlot(@RequestParam(value="nickname", required = true) String nickname,
                                       @RequestParam(value="role", required = true) Integer role,
                                       @RequestParam(value="criteria", required = true) Integer criteria,
                                       @RequestParam(value="season", required = true) Integer season) {
        return legacyService.getSeasonPlot(nickname, role, criteria, season);
    }

    @RequestMapping(value = {"/per_game_statistics"})
    public List <PerGameStatistics> getStatisticsPerGame(@RequestParam(value="limit", required = true) Integer limit,
                                                         @RequestParam(value="criteria", required = true) Integer criteria,
                                                         @RequestParam(value="season", required = true) Integer season) {
        return legacyService.getStatisticsPerGame(limit, criteria, season);
    }

    @RequestMapping(value = {"/role_distribution_result"})
    public ResultPrediction getResultFromRoleDistribution(@RequestParam(value="season", required = true) Integer season,
                                                          @RequestParam(value="roles1[]", required = true) List <Integer> roles1,
                                                          @RequestParam(value="roles2[]", required = true) List <Integer> roles2,
                                                          @RequestParam(value="roles3[]", required = true) List <Integer> roles3,
                                                          @RequestParam(value="roles4[]", required = true) List <Integer> roles4,
                                                          @RequestParam(value="roles5[]", required = true) List <Integer> roles5,
                                                          @RequestParam(value="roles6[]", required = true) List <Integer> roles6,
                                                          @RequestParam(value="roles7[]", required = true) List <Integer> roles7,
                                                          @RequestParam(value="roles8[]", required = true) List <Integer> roles8,
                                                          @RequestParam(value="roles9[]", required = true) List <Integer> roles9,
                                                          @RequestParam(value="roles10[]", required = true) List <Integer> roles10) {
        return legacyService.getResultFromRoleDistribution(season, roles1, roles2, roles3, roles4, roles5, roles6, roles7,
                roles8, roles9, roles10);
    } */
}