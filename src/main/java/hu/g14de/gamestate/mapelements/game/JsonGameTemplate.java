package hu.g14de.gamestate.mapelements.game;

import com.google.gson.JsonObject;
import hu.g14de.gamestate.Cell;
import hu.g14de.gamestate.mapelements.JsonBuildingTemplate;
import hu.g14de.gamestate.mapelements.Placeable;

public class JsonGameTemplate extends JsonBuildingTemplate implements IGameTemplate {
	
	public JsonGameTemplate(JsonObject o) {
		super(o);
	}
	
	@Override
	public Placeable createInstance(Cell cell, boolean instantBuild) {
		return new GameBuilding(this, cell, instantBuild);
	}
	
	@Override
	public int getMaxPlayers() {
		return o.get("maxPlayers").getAsInt();
	}
	
	@Override
	public int getPlaytime() {
		return o.get("playTime").getAsInt();
	}
	
	@Override
	public int getRoundCost() {
		return o.get("roundCost").getAsInt();
	}
	
	@Override
	public int getMinPlayersDefault() {
		return o.get("minPlayers").getAsInt();
	}
	
	@Override
	public int getRoundMoralBoost() {
		return o.get("moraleBoost").getAsInt();
	}
	
}
