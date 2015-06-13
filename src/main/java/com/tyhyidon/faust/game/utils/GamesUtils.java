package com.tyhyidon.faust.game.utils;

import com.tyhyidon.faust.game.entity.Player;
import com.tyhyidon.faust.game.entity.enums.Result;

/**
 * Created by vasylsavytskyi on 08.06.15.
 */
public class GamesUtils {

    public static boolean isWin(Player player) {
        Result result = player.getGame().getResult();
        switch (player.getRole()) {
            case CITIZEN:
            case SHERIFF:
                switch (result) {
                    case CITY:
                    case CITY_CLEAR:
                        return true;
                }
                break;
            case MAFIA:
            case DON:
                switch (result) {
                    case MAFIA:
                    case MAFIA_CLEAR:
                        return true;
                }
        }
        return false;
    }

    public static boolean isClearWin(Player player) {
        Result result = player.getGame().getResult();
        switch (player.getRole()) {
            case CITIZEN:
            case SHERIFF:
                switch (result) {
                    case CITY_CLEAR:
                        return true;
                }
                break;
            case MAFIA:
            case DON:
                switch (result) {
                    case MAFIA_CLEAR:
                        return true;
                }
        }
        return false;
    }

    public static boolean isBlack(Player player) {
        switch (player.getRole()) {
            case MAFIA:
            case DON:
                return true;
            default:
                return false;
        }
    }

    public static boolean wasKilled(Player player) {
        switch (player.getLife()) {
            case KILLED_1ST:
            case KILLED_2ND:
            case KILLED_3RD:
            case KILLED_4TH:
            case KILLED_5TH_PLUS:
                return true;
            default:
                return false;
        }
    }

    public static boolean wasOut(Player player) {
        switch (player.getLife()) {
            case OUT_0:
            case OUT_1ST:
            case OUT_2ND:
            case OUT_3RD:
            case OUT_4TH:
            case OUT_5TH_PLUS:
                return true;
            default:
                return false;
        }
    }
}
