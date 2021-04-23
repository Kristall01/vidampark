package hu.g14de.gamestate.mapelements;

import com.google.gson.JsonObject;

public abstract class JsonBuildingTemplate implements IBuildingTemplate {
	
	protected transient JsonObject o;
	
	public JsonBuildingTemplate(JsonObject o) {
		this.o = o;
	}
	
	@Override
	public boolean needsRoadConnection() {
		return o.get("needsRoad").getAsBoolean();
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
	
	/*@Override
	public Placeable createInstance(Cell cell, boolean instantBuild) {
		return new SimplePlaceable(this, cell, instantBuild);
	}*/
	
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
	
	@Override
	public int getIdleCost() {
		return o.has("idleCost")?o.get("idleCost").getAsInt() : 0;
	}
	
}
