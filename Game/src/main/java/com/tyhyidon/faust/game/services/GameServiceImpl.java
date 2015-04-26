package com.tyhyidon.faust.game.services;

import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.entity.Member;
import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.enums.Result;
import com.tyhyidon.faust.game.model.RatingSnapshot;
import com.tyhyidon.faust.game.rating.RatingCalculator;
import com.tyhyidon.faust.game.repositories.GameRepository;
import com.tyhyidon.faust.game.repositories.MemberRepository;
import com.tyhyidon.faust.game.repositories.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.*;

/**
 */
@Component
public class GameServiceImpl {

    private static Logger LOG = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private RatingCalculator ratingCalculator;

    public List<Double> calculateRating(List<Player> players, Result result) {
        return players.stream().map(p -> {p.setGame(new Game(result));return ratingCalculator.calculateRating(p);}).
                collect(toList());
    }

    public List<String> getMembers() {
        return StreamSupport.stream(memberRepository.findAll().spliterator(), true).
                map(Member::getNickname).collect(toList());
    }

    public List<RatingSnapshot> showRating(Integer season) {
        return ratingCalculator.calculateSeasonRating((season != 0 ? playerRepository.findByGameSeason(season).
                stream() : StreamSupport.stream(playerRepository.findAll().spliterator(), true))
        .collect(groupingBy(Player::getMember)).values());
    }

    public List<String> addMember(String nickname) {
        memberRepository.save(new Member(nickname));
        return getMembers();
    }

    public boolean addGame(Game game) {
        game.getPlayers().stream().forEach(p -> p.setGame(game));
        gameRepository.save(game);
        return true;
    }
}
