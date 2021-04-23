package hu.g14de.gamestate.mapelements.basics;

import com.google.gson.JsonObject;
import hu.g14de.gamestate.Cell;
import hu.g14de.gamestate.mapelements.JsonBuildingTemplate;

public class PrimitiveTemplate extends JsonBuildingTemplate {
	
	public PrimitiveTemplate(JsonObject o) {
		super(o);
	}
	
	@Override
	public PrimitivePlaceable createInstance(Cell cell, boolean instantBuild) {
		return new PrimitivePlaceable(cell,this,instantBuild);
	}
	
}
