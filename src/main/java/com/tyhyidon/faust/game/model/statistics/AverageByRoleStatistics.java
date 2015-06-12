package com.tyhyidon.faust.game.model.statistics;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.enums.Role;
import com.tyhyidon.faust.game.utils.GamesUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static java.util.stream.Collectors.*;

/**
 * Created by vasylsavytskyi on 07.06.15.
 */
public class AverageByRoleStatistics {

    private List<Double> averageByRole;

    public List<Double> getAverageByRole() {
        return averageByRole;
    }

    public void setAverageByRole(List<Double> averageByRole) {
        this.averageByRole = averageByRole;
    }
}
