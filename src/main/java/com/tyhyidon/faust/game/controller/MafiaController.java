package com.tyhyidon.faust.game.controller;

import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.entity.Member;
import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.enums.Life;
import com.tyhyidon.faust.game.entity.enums.Result;
import com.tyhyidon.faust.game.entity.enums.Role;
import com.tyhyidon.faust.game.entity.enums.Season;
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

    @RequestMapping(value = {"/seasons"})
    public Season[] getSeasons() {
        return Season.values();
    }

    @RequestMapping(value = {"/member-nicknames"})
    public List<String> getMemberNicknames() {
        return memberService.allNicknames();
    }

    @RequestMapping(value = {"/members"})
    public List<Member> getMembers() {
        return memberService.all();
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/member"})
    public void addMember(@RequestBody Member member) {
        memberService.addUpdate(member);
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/rating"})
    public List<Double> calculateRating(@RequestBody Game game) {
        return playerService.calculateRating(game.getPlayers(), game.getResult());
    }

    @RequestMapping(value = {"/member/games-number"})
    public int getMemberGamesAmount(String nickname) {
        return memberService.getMemberGameNumber(nickname);
    }

    @RequestMapping(value = {"/rating"})
    public List<RatingSnapshot> getRating(@RequestParam Season season) {
        return playerService.showRating(season);
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/game"})
    public void addUpdateGame(@RequestBody Game game) {
        gameService.addUpdate(game);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = {"/member"})
    public void removeMember(@RequestParam String nickname) {
        memberService.remove(nickname);
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/games"})
    public List<Game> getGames(@RequestParam Season season) {
        return gameService.findBySeason(season);
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/players"})
    public List<Player> getGamePlayers(@RequestParam int gameId) {
        return playerService.getGamePlayers(gameId);
    }
}