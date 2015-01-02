package com.tyhyidon.faust.game.controller;

import com.tyhyidon.faust.game.filter.PerGameStatistics;
import com.tyhyidon.faust.game.filter.PlayerStatistics;
import com.tyhyidon.faust.game.filter.Result;
import com.tyhyidon.faust.game.filter.ResultPrediction;
import com.tyhyidon.faust.game.logic.Logic;
import com.tyhyidon.faust.game.model.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/Mafia")
public class MafiaController {

    private static Logger logger = LoggerFactory.getLogger(MafiaController.class);
    //kkkVasian
    @Autowired
    private Logic logic;
    
    private Logic logic2;

    @RequestMapping(value = {"/main/*"})
    public String main() {
        return "main";
    }

    @RequestMapping(value = {"/getAllPlayers"})
    public
    @ResponseBody
    List<String> getAllPlayers() {
        return logic.getPlayersNicknames();
    }

    @RequestMapping(value = {"/addPlayerToDB"})
    public
    @ResponseBody
    List<String> addPlayerToDB(@RequestParam(value="nickname", required = true) String nickname,
                               @RequestParam(value="vkontakte", required = true) String vkontakte) {
        return logic.addPlayerToDB(nickname, vkontakte);
    }


    @RequestMapping(value = {"/calculateRating"})
    public
    @ResponseBody
    List<Statistics> getStatistics(@RequestParam(value="season", required = true) Integer season,
                                   @RequestParam(value="date", required = true) String date,
                                   @RequestParam(value="result", required = true) Integer result,
                                   @RequestParam(value="master", required = true) String masterNickname,
                                   @RequestParam(value="nickNames[]", required = true) List<String> nickNames,
                                   @RequestParam(value="roles[]", required = true) List<Integer> roles,
                                   @RequestParam(value="lives[]", required = true) List<Integer> lives,
                                   @RequestParam(value="bestVoices[]", required = true) List<Integer> bestVoices,
                                   @RequestParam(value="finalDecisions[]", required = true) List<Integer> finalDecisions,
                                   @RequestParam(value="fouls[]", required = true) List<Integer> fouls
    ) throws IOException, ParseException {
        return logic.getStatistics(result, season, date, masterNickname, nickNames, roles, lives, bestVoices, finalDecisions, fouls);
    }

    @RequestMapping(value = {"/showPlayersRating"})
    public
    @ResponseBody
    List<Result> showRating(@RequestParam(value="season", required = true) Integer season) {
        return logic.showRating(season);
    }


    @RequestMapping(value = {"/saveGameIntoDB"})
    public
    @ResponseBody
    List<Result> saveGameIntoDB(
            @RequestParam(value="season", required = true) Integer season,
            @RequestParam(value="date", required = true) String date,
            @RequestParam(value="result", required = true) Integer result,
            @RequestParam(value="master", required = true) String masterNickname,
            @RequestParam(value="nickNames[]", required = true) List<String> nickNames,
            @RequestParam(value="roles[]", required = true) List<Integer> roles,
            @RequestParam(value="lives[]", required = true) List<Integer> lives,
            @RequestParam(value="bestVoices[]", required = true) List<Integer> bestVoices,
            @RequestParam(value="finalDecisions[]", required = true) List<Integer> finalDecisions,
            @RequestParam(value="fouls[]", required = true) List<Integer> fouls) throws ParseException {
        return logic.saveGameIntoDB(date, season, masterNickname, result, nickNames, roles,lives,bestVoices,finalDecisions,fouls);
    }

    @RequestMapping(value = {"/statisticsPlayer"})
    public
    @ResponseBody
    PlayerStatistics getStatisticsPlayerRequest(@RequestParam(value="nickname", required = false) String nickname,
                                                @RequestParam(value="season", required = false) Integer season) {
        return logic.getStatisticsPlayerRequest(nickname, season);
    }

    @RequestMapping(value = {"/statistics"})
    public
    @ResponseBody
    List<Result> getStatisticsRequest(@RequestParam(value="season", required = true) Integer season,
                                        @RequestParam(value="numbers[]", required = false) List<Integer> numbers,
                                         @RequestParam(value="roles[]", required = true) List <Integer> roles,
                                         @RequestParam(value="lives[]", required = true) List<Integer> lives,
                                         @RequestParam(value="bestVoices[]", required = true) List<Integer> bestVoices,
                                         @RequestParam(value="finalDecisions[]", required = true) List<Integer> finalDecisions,
                                         @RequestParam(value="fouls[]", required = true) List<Integer> fouls,
                                         @RequestParam(required = false) Integer criteria,
                                         @RequestParam(required = false) Integer limit) {
        return logic.getStatisticsRequest(numbers, roles, lives, bestVoices, finalDecisions, fouls, criteria, limit, season);
    }

    @RequestMapping(value = {"/season_plot"})
    public
    @ResponseBody
    List <Result> getSeasonPlot(@RequestParam(value="nickname", required = true) String nickname,
                                                @RequestParam(value="role", required = true) Integer role,
                                                @RequestParam(value="criteria", required = true) Integer criteria,
                                                @RequestParam(value="season", required = true) Integer season) {
        return logic.getSeasonPlot(nickname, role, criteria, season);
    }

    @RequestMapping(value = {"/per_game_statistics"})
    public
    @ResponseBody
    List <PerGameStatistics> getStatisticsPerGame(@RequestParam(value="limit", required = true) Integer limit,
                                                  @RequestParam(value="criteria", required = true) Integer criteria,
                                                  @RequestParam(value="season", required = true) Integer season) {
        return logic.getStatisticsPerGame(limit, criteria, season);
    }


    @RequestMapping(value = {"/role_distribution_result"})
    public
    @ResponseBody
    ResultPrediction  getResultFromRoleDistribution(@RequestParam(value="season", required = true) Integer season,
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
        return logic.getResultFromRoleDistribution(season, roles1, roles2, roles3, roles4, roles5, roles6, roles7,
                roles8, roles9, roles10);
    }

}