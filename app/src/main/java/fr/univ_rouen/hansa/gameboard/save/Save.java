package fr.univ_rouen.hansa.gameboard.save;


import java.io.Serializable;
import java.util.Date;

import fr.univ_rouen.hansa.gameboard.board.GameBoard;

public class Save implements Serializable {

    private static final long serialVersionUID = 7944125519864616L;

    private final GameBoard board;
    private final Date date;

    public Save(GameBoard b, Date d) {
        if (b == null || d == null) {
            throw new IllegalArgumentException();
        }

        this.board = b;
        this.date = d;
    }

    public GameBoard getBoard() {
        return board;
    }

    public Date getDate() {
        return date;
    }

}
