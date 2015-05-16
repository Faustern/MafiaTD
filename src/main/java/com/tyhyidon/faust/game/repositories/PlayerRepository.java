package com.tyhyidon.faust.game.repositories;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.enums.Season;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by vasylsavytskyi on 25.04.15.
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findByGameSeason(Season season);

    List<Player> findByGameId(long id);
}
