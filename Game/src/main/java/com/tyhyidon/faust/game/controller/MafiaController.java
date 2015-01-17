package com.tyhyidon.faust.game.controller;

import com.tyhyidon.faust.game.filter.PerGameStatistics;
import com.tyhyidon.faust.game.filter.PlayerStatistics;
import com.tyhyidon.faust.game.model.Result;
import com.tyhyidon.faust.game.filter.ResultPrediction;
import com.tyhyidon.faust.game.logic.GameServiceImpl;
import com.tyhyidon.faust.game.model.GameSnapshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class MafiaController {

    private static Logger logger = LoggerFactory.getLogger(MafiaController.class);

    @Autowired
    private GameServiceImpl gameService;

    @RequestMapping(value = {"/main"})
    public String main() {
        return "main";
    }

    @RequestMapping(value = {"/members"})
    public List<String> getMembers() {
        return gameService.getMembers();
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/member"})
    public List<String> addMember(@RequestBody String nickname) {
        return gameService.addMember(nickname);
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/rating"})
    public List<Double> calculateRating(@RequestBody GameSnapshot game) {
        return gameService.calculateRating(game.getPlayers(), game.getResult());
    }

    @RequestMapping(value = {"/rating"})
    public List<Result> getRating(@RequestParam int season) {
        return gameService.showRating(season);
    }


    @RequestMapping(method = RequestMethod.POST, value = {"/game"})
    public boolean addGame(@RequestBody GameSnapshot game) {
        return gameService.addGame(game);
    }

    @RequestMapping(value = {"/statisticsPlayer"})
    public
    @ResponseBody
    PlayerStatistics getStatisticsPlayerRequest(@RequestParam(value="nickname", required = false) String nickname,
                                                @RequestParam(value="season", required = false) Integer season) {
        return gameService.getStatisticsPlayerRequest(nickname, season);
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
        return gameService.getStatisticsRequest(numbers, roles, lives, bestVoices, finalDecisions, fouls, criteria, limit, season);
    }

    @RequestMapping(value = {"/season_plot"})
    public
    @ResponseBody
    List <Result> getSeasonPlot(@RequestParam(value="nickname", required = true) String nickname,
                                                @RequestParam(value="role", required = true) Integer role,
                                                @RequestParam(value="criteria", required = true) Integer criteria,
                                                @RequestParam(value="season", required = true) Integer season) {
        return gameService.getSeasonPlot(nickname, role, criteria, season);
    }

    @RequestMapping(value = {"/per_game_statistics"})
    public
    @ResponseBody
    List <PerGameStatistics> getStatisticsPerGame(@RequestParam(value="limit", required = true) Integer limit,
                                                  @RequestParam(value="criteria", required = true) Integer criteria,
                                                  @RequestParam(value="season", required = true) Integer season) {
        return gameService.getStatisticsPerGame(limit, criteria, season);
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
        return gameService.getResultFromRoleDistribution(season, roles1, roles2, roles3, roles4, roles5, roles6, roles7,
                roles8, roles9, roles10);
    }

}