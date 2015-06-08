package com.tyhyidon.faust.game.services;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.enums.Season;
import com.tyhyidon.faust.game.model.statistics.MemberStatistics;
import com.tyhyidon.faust.game.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vasylsavytskyi on 07.06.15.
 */
@Service
public class StatisticsServiceImpl {

    @Autowired
    private PlayerRepository playerRepository;

    public MemberStatistics getMemberStatistics(String nickname, Season season) {
        List<Player> players = playerRepository.findByMemberNicknameAndGameSeason(nickname, season);
        return new MemberStatistics(players);
    }

}
