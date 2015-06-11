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
}
