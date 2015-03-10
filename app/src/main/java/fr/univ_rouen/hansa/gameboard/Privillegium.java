package fr.univ_rouen.hansa.gameboard;

import android.graphics.Color;

public enum Privillegium {

    //TODO adjust to correct colors
    White(Color.WHITE),
    Orange(Color.YELLOW),
    Pink(Color.RED),
    Black(Color.BLACK);

    private int color;

    Privillegium(int color) {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }

    public boolean isBetterThan(Privillegium privillegium) {
        return this.ordinal() >= privillegium.ordinal();
    }


}
