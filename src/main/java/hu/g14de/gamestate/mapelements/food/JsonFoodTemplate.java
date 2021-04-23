package hu.g14de.gamestate.mapelements.food;

import com.google.gson.JsonObject;
import hu.g14de.gamestate.Cell;
import hu.g14de.gamestate.mapelements.JsonBuildingTemplate;

public class JsonFoodTemplate extends JsonBuildingTemplate implements IFoodTemplate {
	
	public JsonFoodTemplate(JsonObject o) {
		super(o);
	}
	
	@Override
	public FoodBuilding createInstance(Cell cell, boolean instantBuild) {
		return new FoodBuilding(this, cell, instantBuild);
	}
	
	@Override
	public int getMaxGuests() {
		return o.get("maxGuests").getAsInt();
	}
	
	@Override
	public int getEatTime() {
		return o.get("eatTime").getAsInt();
	}
	
	@Override
	public int getFoodBoost() {
		return o.get("foodBoost").getAsInt();
	}
}
