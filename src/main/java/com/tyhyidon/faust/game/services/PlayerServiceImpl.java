package com.tyhyidon.faust.game.services;

import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.enums.Result;
import com.tyhyidon.faust.game.entity.enums.Season;
import com.tyhyidon.faust.game.model.RatingSnapshot;
import com.tyhyidon.faust.game.rating.RatingCalculator;
import com.tyhyidon.faust.game.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Created by vasylsavytskyi on 05.05.15.
 */
@Service
public class PlayerServiceImpl {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private RatingCalculator ratingCalculator;

    public List<Double> calculateRating(List<Player> players, Result result) {
        return players.stream().map(p -> {p.setGame(new Game(result));return ratingCalculator.calculateRating(p);}).
                collect(toList());
    }

    public List<RatingSnapshot> showRating(Season season) {
        return ratingCalculator.calculateSeasonRating((!season.equals(Season.ALL) ?
                playerRepository.findByGameSeason(season).
                stream() : StreamSupport.stream(playerRepository.findAll().spliterator(), true))
                .collect(groupingBy(Player::getMember)).values());
    }

    public long amount() {
        return playerRepository.count();
    }

    public List<Player> getGamePlayers(long gameId) {
        return playerRepository.findByGameId(gameId);
    }
}
