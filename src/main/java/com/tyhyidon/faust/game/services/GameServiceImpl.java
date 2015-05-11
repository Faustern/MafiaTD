package com.tyhyidon.faust.game.services;

import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.entity.enums.Season;
import com.tyhyidon.faust.game.repositories.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.*;


@Service
public class GameServiceImpl {

    private static Logger LOG = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private GameRepository gameRepository;

    public Game addUpdate(Game game) {
        Optional.ofNullable(game.getPlayers()).ifPresent(v -> v.stream().forEach(p -> p.setGame(game)));
        return gameRepository.save(game);
    }

    public List<Game> findBySeason(Season season) {
        return StreamSupport.stream(gameRepository.findBySeason(season).spliterator(), true).collect(toList());
    }

    public Game find(long id) {
        return gameRepository.findOne(id);
    }

    public void remove(long gameId) {
        gameRepository.delete(gameId);
    }

    public long amount() {
        return gameRepository.count();
    }


}
