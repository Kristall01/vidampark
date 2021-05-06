package hu.g14de.gamestate.mapelements.basics;

import com.google.gson.JsonObject;
import hu.g14de.gamestate.Cell;
import hu.g14de.gamestate.mapelements.JsonBuildingTemplate;
import hu.g14de.gamestate.mapelements.Placeable;

public class RoadTemplate extends JsonBuildingTemplate {
	
	public RoadTemplate(JsonObject o) {
		super(o);
	}
	
	@Override
	public Placeable createInstance(Cell at, boolean instantBuild) {
		return new Road(at, this, instantBuild);
	}
	
}
