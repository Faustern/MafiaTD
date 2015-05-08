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
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.*;


@Service
public class GameServiceImpl {

    private static Logger LOG = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private GameRepository gameRepository;

    public Game add(Game game) {
        game.getPlayers().stream().forEach(p -> p.setGame(game));
        return gameRepository.save(game);
    }

    public Game find(long id) {
        return gameRepository.findOne(id);
    }

    public void remove(Game game) {
        gameRepository.delete(game);
    }

    public long amount() {
        return gameRepository.count();
    }


}
