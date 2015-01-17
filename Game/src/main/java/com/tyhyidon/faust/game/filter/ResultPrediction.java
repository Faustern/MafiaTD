package com.tyhyidon.faust.game.filter;

import com.tyhyidon.faust.game.entity.Game;
import com.tyhyidon.faust.game.player.Constants;

import java.util.List;

/**
 */
public class ResultPrediction {

    private int sampling = 0;

    private double occurrenceProbability = 0.0;

    private double cityClearWinProbability = 0.0;

    private double cityWinProbability = 0.0;

    private double mafiaWinProbability = 0.0;

    private double mafiaClearWinProbability = 0.0;

    public ResultPrediction(List <Game> games, int sampling) {
        this.sampling = sampling;
        if (games.size()!=0) {
            int cityClearWin = 0;
            int cityWin = 0;
            int mafiaWin = 0;
            int mafiaClearWin = 0;

            for (Game game : games) {
                switch (game.getResult()) {
                    case Constants.RESULT_CITY_CLEAR_WIN:
                        cityClearWin++;
                        cityWin++;
                        break;
                    case Constants.RESULT_CITY_WIN:
                        cityWin++;
                        break;
                    case Constants.RESULT_MAFIA_WIN:
                        mafiaWin++;
                        break;
                    case Constants.RESULT_MAFIA_CLEAR_WIN:
                        mafiaWin++;
                        mafiaClearWin++;
                        break;
                }
            }
            this.occurrenceProbability = 100.0*games.size()/sampling;
            this.cityClearWinProbability = 100.0*cityClearWin/games.size();
            this.cityWinProbability = 100.0*cityWin/games.size();
            this.mafiaWinProbability = 100.0*mafiaWin/games.size();
            this.mafiaClearWinProbability = 100.0*mafiaClearWin/games.size();
        }

    }

    public int getSampling() {
        return sampling;
    }

    public void setSampling(int sampling) {
        this.sampling = sampling;
    }

    public double getOccurrenceProbability() {
        return occurrenceProbability;
    }

    public void setOccurrenceProbability(double occurrenceProbability) {
        this.occurrenceProbability = occurrenceProbability;
    }

    public double getCityClearWinProbability() {
        return cityClearWinProbability;
    }

    public void setCityClearWinProbability(double cityClearWinProbability) {
        this.cityClearWinProbability = cityClearWinProbability;
    }

    public double getCityWinProbability() {
        return cityWinProbability;
    }

    public void setCityWinProbability(double cityWinProbability) {
        this.cityWinProbability = cityWinProbability;
    }

    public double getMafiaWinProbability() {
        return mafiaWinProbability;
    }

    public void setMafiaWinProbability(double mafiaWinProbability) {
        this.mafiaWinProbability = mafiaWinProbability;
    }

    public double getMafiaClearWinProbability() {
        return mafiaClearWinProbability;
    }

    public void setMafiaClearWinProbability(double mafiaClearWinProbability) {
        this.mafiaClearWinProbability = mafiaClearWinProbability;
    }
}
