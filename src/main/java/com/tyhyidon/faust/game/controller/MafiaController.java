package com.tyhyidon.faust.game.controller;

import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.entity.Member;
import com.tyhyidon.faust.game.entity.enums.Life;
import com.tyhyidon.faust.game.entity.enums.Result;
import com.tyhyidon.faust.game.entity.enums.Role;
import com.tyhyidon.faust.game.model.RatingSnapshot;
import com.tyhyidon.faust.game.services.GameServiceImpl;
import com.tyhyidon.faust.game.services.MemberServiceImpl;
import com.tyhyidon.faust.game.services.PlayerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MafiaController {

    private static Logger LOG = LoggerFactory.getLogger(MafiaController.class);

    @Autowired
    private GameServiceImpl gameService;

    @Autowired
    private PlayerServiceImpl playerService;

    @Autowired
    private MemberServiceImpl memberService;

    @RequestMapping(value = {"/main"})
    public String main() {
        return "main";
    }

    @RequestMapping(value = {"/results"})
    public Result[] getResults() {
        return Result.values();
    }

    @RequestMapping(value = {"/roles"})
    public Role [] getRoles() {
        return Role.values();
    }

    @RequestMapping(value = {"/lives"})
    public Life[] getLives() {
        return Life.values();
    }

    @RequestMapping(value = {"/members"})
    public List<String> getMembers() {
        return memberService.all();
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/member"})
    public void addMember(@RequestBody String nickname) {
        memberService.add(new Member(nickname));
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/rating"})
    public List<Double> calculateRating(@RequestBody Game game) {
        return playerService.calculateRating(game.getPlayers(), game.getResult());
    }

    @RequestMapping(value = {"/rating"})
    public List<RatingSnapshot> getRating(@RequestParam int season) {
        return playerService.showRating(season);
    }


    @RequestMapping(method = RequestMethod.POST, value = {"/game"})
    public void addGame(@RequestBody Game game) {
        gameService.add(game);
    }

}