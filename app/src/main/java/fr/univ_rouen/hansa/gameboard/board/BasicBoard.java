package fr.univ_rouen.hansa.gameboard.board;

public abstract class BasicBoard {

    private String image;

    protected void setImage(String image)
    {
        this.image = image;
    }

    public String getImage()
    {
        return image;
    }

}
