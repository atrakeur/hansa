package fr.univ_rouen.hansa.gameboard.player;

import android.graphics.Color;

import com.google.gson.annotations.Expose;

public enum PlayerColor {
    red(Color.RED),
    blue(Color.BLUE),
    yellow(Color.YELLOW),
    green(Color.GREEN),
    purple(Color.rgb(127, 15, 126));
    @Expose
    private final int color;

    private PlayerColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
