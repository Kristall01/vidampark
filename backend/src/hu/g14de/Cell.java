package hu.g14de;


public class Cell
{
    //Variables
    private int x;
    private int y;
    private Placeable content;
    private Map mapReference;

    //Getters
    public int getX () {return this.x;}
    public int getY () {return this.y;}
    public Placeable getContent () {return this.content;}
    public Map getMapReference () {return this.mapReference;}

    //Setters
    public void setX(int value)
    {
        //Illegal argument given -> check before function call
        this.x = value;
    }
    public void setY(int value)
    {
        //Illegal argument given -> check before function call
        this.y = value;
    }
    public void setContent(Placeable value)
    {
        //Illegal argument given -> check before function call
        this.content = value;
    }
    public void setMapReference(Map value)
    {
        //Illegal argument given -> check before function call
        this.mapReference = value;
    }

    //Methods
}
