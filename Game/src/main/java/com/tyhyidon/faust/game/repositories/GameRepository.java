package com.tyhyidon.faust.game.repositories;

import com.tyhyidon.faust.game.entity.Game;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vasylsavytskyi on 25.04.15.
 */
public interface GameRepository extends CrudRepository<Game, Long>{
}
