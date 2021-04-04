package hu.g14de;
import static hu.g14de.Utils.checkNull;

public class Cell
{
    //Variables
    private final Map map;
	private final Coordinate cords;
	private Placeable content;

    //Constructor
    public Cell(Map mapReference, Coordinate cords, Placeable content)
    {
        checkNull(mapReference, cords, content);

        this.map = mapReference;
        this.cords = cords;
        this.content = content;
    }
	
	public Coordinate getCoordinate() {
		return this.cords;
	}
	
	public Map getMap() {
		return this.map;
	}
	
	public Placeable getContent()
    {
        return this.content;
    }
    //Setters

    public boolean hasContent() {
    	return content != null;
	}

}
