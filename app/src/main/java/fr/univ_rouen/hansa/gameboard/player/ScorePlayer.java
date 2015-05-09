package fr.univ_rouen.hansa.gameboard.player;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.LinkedList;
import java.util.Map;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.VictoryCoellen;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;

public class ScorePlayer implements IScorePlayer {

    private int score;

    public ScorePlayer() {
        score = 0;
    }

    @Override
    public void increaseScore() {
        score++;
    }

    @Override
    public void decreaseScore() {
        score--;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

}
