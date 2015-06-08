package com.tyhyidon.faust.game.model.statistics;

import com.tyhyidon.faust.game.entity.Player;

import java.util.List;

/**
 * Created by vasylsavytskyi on 06.06.15.
 */
public class MemberStatistics {

    private PositionsStatistics positions;
    private RolesStatistics roles;
    private LivesStatistics lives;
    private BestVoicesStatistics bestVoices;
    private FoulsStatistics fouls;
    private ResultsStatistics results;

    public MemberStatistics(List<Player> players) {
        positions = new PositionsStatistics(players);
        roles = new RolesStatistics(players);
        lives = new LivesStatistics(players);
        bestVoices = new BestVoicesStatistics(players);
        fouls = new FoulsStatistics(players);
        results = new ResultsStatistics(players);
    }

    public PositionsStatistics getPositions() {
        return positions;
    }

    public RolesStatistics getRoles() {
        return roles;
    }

    public LivesStatistics getLives() {
        return lives;
    }

    public BestVoicesStatistics getBestVoices() {
        return bestVoices;
    }

    public FoulsStatistics getFouls() {
        return fouls;
    }

    public ResultsStatistics getResults() {
        return results;
    }

}
