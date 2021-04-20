package hu.g14de;

import com.google.gson.JsonObject;
import hu.g14de.gamestate.Cell;

public class JsonBuildingTemplate implements IBuildingTemplate {
	
	private transient JsonObject o;
	
	public JsonBuildingTemplate(JsonObject o) {
		this.o = o;
	}
	
	@Override
	public boolean needsRoadConnection() {
		return !o.get("type").getAsString().contentEquals("road");
	}
	
	@Override
	public String type() {
		return o.get("type").getAsString();
	}
	
	@Override
	public String getNickname() {
		return o.get("nickname").getAsString();
	}
	
	@Override
	public int getBuildCost() {
		return o.get("buildCost").getAsInt();
	}
	
	@Override
	public int getBuildTime() {
		return o.get("buildTime").getAsInt();
	}
	
	@Override
	public boolean isRoad() {
		return o.get("type").getAsString().contentEquals("road");
	}
	
	@Override
	public Placeable createInstance(Cell cell) {
		return new SimplePlaceable(this, cell);
	}
	
	@Override
	public int width() {
		return o.get("width").getAsInt();
	}
	
	@Override
	public int height() {
		return o.get("height").getAsInt();
	}
	
	@Override
	public String mapIcon() {
		return o.get("mapIcon").getAsString();
	}
	
	@Override
	public String shopIcon() {
		return o.get("shopIcon").getAsString();
	}
	
	@Override
	public Object serialize() {
		return o;
	}
	
}
