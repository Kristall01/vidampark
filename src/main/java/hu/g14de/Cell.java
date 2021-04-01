package hu.g14de;
import static hu.g14de.Utils.checkNull;

public class Cell
{
    //Variables
    private int x;
    private int y;

    private Map mapReference;
    private Placeable content;

    //Constructor
    public Cell(Map mapReference, int x, int y, Placeable content)
    {
        checkNull(mapReference, content);

        this.mapReference = mapReference;
        this.x = x;
        this.y = y;
        this.content = content;
    }

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

}
