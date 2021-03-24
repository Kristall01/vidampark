package hu.g14de;


public class Cell
{
    //Constructor
    public Cell(Map mapReference, int x, int y, Placeable content)
    {
        this.mapReference = mapReference;
        this.x = x;
        this.y = y;
        this.content = content;
    }

    //Variables
    private int x;
    private int y;

    private Map mapReference;
    private Placeable content;

    //Getters
    public int getX () {return this.x;}
    public int getY () {return this.y;}

    public Map getMapReference () {return this.mapReference;}
    public Placeable getContent()
    {
        return content;
    }
    //Setters

    public void setContent(Placeable content) { this.content = content; }

    //Methods

}
