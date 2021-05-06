package hu.g14de.gamestate;
import hu.g14de.gamestate.mapelements.Placeable;
import hu.g14de.VidamparkApp;

import static hu.g14de.Utils.checkNull;

public class Cell
{
    //Variables
    private final IMap map;
	private final Coordinate cords;
	private Placeable content;

    //Constructor
    public Cell(IMap mapReference, Coordinate cords, Placeable content)
    {
        checkNull(mapReference, cords);

        this.map = mapReference;
        this.cords = cords;
        this.content = content;
    }
    
    public VidamparkApp getApp() {
    	return map.getApp();
	}
	
	public Coordinate getCoordinate() {
		return this.cords;
	}
	
	public IMap getMap() {
		return this.map;
	}
	
	public Placeable getContent()
    {
        return this.content;
    }

    public boolean hasContent() {
    	return content != null;
	}
	
	public void setContent(Placeable p) {
    	this.content = p;
	}
	
	public boolean hasContentType(String type) {
    	return hasContent() && getContent().getTemplate().type().equals(type);
	}

}
