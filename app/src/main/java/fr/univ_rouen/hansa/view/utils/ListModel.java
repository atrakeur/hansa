package fr.univ_rouen.hansa.view.utils;


public class ListModel {
    private  String color="";
    private  String player="";

    /*********** Set Methods ******************/
    public void setColor(String color)
    {
        this.color = color;
    }



    public void setPlayer(String player)
    {
        this.player = player;
    }

    /*********** Get Methods ****************/
    public String getColor()
    {
        return this.color;
    }



    public String getPlayer()
    {
        return this.player;
    }
}

