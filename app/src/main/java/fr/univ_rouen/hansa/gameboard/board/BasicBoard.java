package fr.univ_rouen.hansa.gameboard.board;

public abstract class BasicBoard {

    private int background;

    protected void setBackground(int image)
    {
        this.background = image;
    }

    public int getBackground()
    {
        return background;
    }

}
