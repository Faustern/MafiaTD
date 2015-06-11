package com.tyhyidon.faust.game.model.statistics;

import com.tyhyidon.faust.game.entity.enums.Role;

import java.util.*;


/**
 * Created by vasylsavytskyi on 07.06.15.
 */
public class BaseByRoleStatistics extends BaseStatistics {

    private Map<Role, List<Long>> allByRole;
    private Map<Role, List<Long>> winsByRole;
    private Map<Role, List<Long>> clearWinsByRole;

    public Map<Role, List<Long>> getAllByRole() {
        return allByRole;
    }

    public void setAllByRole(Map<Role, List<Long>> allByRole) {
        this.allByRole = allByRole;
    }

    public Map<Role, List<Long>> getWinsByRole() {
        return winsByRole;
    }

    public void setWinsByRole(Map<Role, List<Long>> winsByRole) {
        this.winsByRole = winsByRole;
    }

    public Map<Role, List<Long>> getClearWinsByRole() {
        return clearWinsByRole;
    }

    public void setClearWinsByRole(Map<Role, List<Long>> clearWinsByRole) {
        this.clearWinsByRole = clearWinsByRole;
    }
}
