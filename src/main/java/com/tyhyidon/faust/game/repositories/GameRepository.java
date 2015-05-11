package com.tyhyidon.faust.game.repositories;

import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.entity.enums.Season;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by vasylsavytskyi on 25.04.15.
 */
public interface GameRepository extends CrudRepository<Game, Long>{
    List<Game> findBySeason(Season season);
}
