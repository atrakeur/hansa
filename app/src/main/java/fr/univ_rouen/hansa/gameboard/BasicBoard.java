package fr.univ_rouen.hansa.gameboard;

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
