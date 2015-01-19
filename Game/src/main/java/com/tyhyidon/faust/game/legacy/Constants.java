package com.tyhyidon.faust.game.legacy;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final int LIFE_KILLED_ONE_NIGHT = 1;
	public static final int LIFE_KILLED_TWO_NIGHT = 2;
	public static final int LIFE_KILLED_THREE_NIGHT = 3;
	public static final int LIFE_KILLED_FOUR_NIGHT = 4;
	public static final int LIFE_KILLED_FIVE_PLUS_NIGHT = 5;
	 
	public static final int LIFE_ZERO_DAY_AWAY = 6;
	public static final int LIFE_ONE_DAY_AWAY = 7;
	public static final int LIFE_TWO_DAY_AWAY = 8;
	public static final int LIFE_THREE_DAY_AWAY = 9;
	public static final int LIFE_FOUR_DAY_AWAY = 10;
	public static final int LIFE_FIVE_PLUS_DAY_AWAY = 11;
	
	public static final int LIFE_NOT_AWAY = 12;
	
	public static final int ROLE_DON = 1;
	public static final int ROLE_MAFIA = 2;
	public static final int ROLE_CITIZEN = 3;
	public static final int ROLE_SHERIFF = 4;

	public static final int RESULT_CITY_CLEAR_WIN = 1;
	public static final int RESULT_CITY_WIN = 2;
	public static final int RESULT_MAFIA_WIN = 3;
	public static final int RESULT_MAFIA_CLEAR_WIN = 4;

    public static final List <Integer> DISABLE_FILTERING = new ArrayList<Integer>();
    public static final String DEFAULT_PLAYER = null;

    public static final int RATING_GAMES_LIMIT = 11;

    public static final int SORT_BY_GAMES = 1;
    public static final int SORT_BY_GAMES_WIN = 2;
    public static final int SORT_BY_WIN_PERCENT = 3;

    public static final int SORT_BY_BEST_VOICES_PER_GAME = 11;
    public static final int SORT_BY_FOULS_PER_GAME = 12;


}
