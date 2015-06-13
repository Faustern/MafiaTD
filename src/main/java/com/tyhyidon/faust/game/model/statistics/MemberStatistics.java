package com.tyhyidon.faust.game.model.statistics;

/**
 * Created by vasylsavytskyi on 06.06.15.
 */
public class MemberStatistics {

    private TableStatistics table;
    private PositionsStatistics positions;
    private RolesStatistics roles;
    private LivesStatistics lives;
    private BestVoicesStatistics bestVoices;
    private FoulsStatistics fouls;
    private ResultsStatistics results;

    public TableStatistics getTable() {
        return table;
    }

    public void setTable(TableStatistics table) {
        this.table = table;
    }

    public PositionsStatistics getPositions() {
        return positions;
    }

    public void setPositions(PositionsStatistics positions) {
        this.positions = positions;
    }

    public RolesStatistics getRoles() {
        return roles;
    }

    public void setRoles(RolesStatistics roles) {
        this.roles = roles;
    }

    public LivesStatistics getLives() {
        return lives;
    }

    public void setLives(LivesStatistics lives) {
        this.lives = lives;
    }

    public BestVoicesStatistics getBestVoices() {
        return bestVoices;
    }

    public void setBestVoices(BestVoicesStatistics bestVoices) {
        this.bestVoices = bestVoices;
    }

    public FoulsStatistics getFouls() {
        return fouls;
    }

    public void setFouls(FoulsStatistics fouls) {
        this.fouls = fouls;
    }

    public ResultsStatistics getResults() {
        return results;
    }

    public void setResults(ResultsStatistics results) {
        this.results = results;
    }
}
