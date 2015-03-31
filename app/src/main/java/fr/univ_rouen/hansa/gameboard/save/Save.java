package fr.univ_rouen.hansa.gameboard.save;


import com.google.gson.annotations.Expose;

import java.util.Date;

public class Save {
    @Expose
    private final GameBoardSave board;
    @Expose
    private final Date date;

    public Save(GameBoardSave b, Date d) {
        if (b == null || d == null) {
            throw new IllegalArgumentException();
        }

        this.board = b;
        this.date = d;
    }

    public GameBoardSave getBoard() {
        return board;
    }

    public Date getDate() {
        return date;
    }

}
