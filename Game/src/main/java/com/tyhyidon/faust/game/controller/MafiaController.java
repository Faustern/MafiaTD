package com.tyhyidon.faust.game.controller;

import com.tyhyidon.faust.game.model.RatingSnapshot;
import com.tyhyidon.faust.game.services.GameServiceImpl;
import com.tyhyidon.faust.game.model.GameSnapshot;
import com.tyhyidon.faust.game.legacy.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<RatingSnapshot> getRating(@RequestParam int season) {
        return gameService.showRating(season);
    }


    @RequestMapping(method = RequestMethod.POST, value = {"/game"})
    public boolean addGame(@RequestBody GameSnapshot game) {
        return gameService.addGame(game);
    }


}