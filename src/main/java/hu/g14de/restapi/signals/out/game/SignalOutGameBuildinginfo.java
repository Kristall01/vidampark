package hu.g14de.restapi.signals.out.game;

import com.google.gson.JsonObject;
import hu.g14de.gamestate.mapelements.Placeable;
import hu.g14de.gamestate.mapelements.food.FoodBuilding;
import hu.g14de.gamestate.mapelements.game.GameBuilding;
import hu.g14de.gamestate.mapelements.game.GameBuildingPhase;
import hu.g14de.restapi.signals.SignalOut;

public class SignalOutGameBuildinginfo extends SignalOut {
	
	private Placeable p;
	
	public SignalOutGameBuildinginfo(Placeable p) {
		super("buildinginfo");
		this.p = p;
	}
	
	//added buildinfo signals
	
	@Override
	public Object serializedData() {
		JsonObject o = new JsonObject();
		o.addProperty("id", p.getID());
		o.addProperty("title", p.getTemplate().getNickname());
		JsonObject data = new JsonObject();
		if(p instanceof GameBuilding) {
			GameBuilding building = (GameBuilding) p;
			data.addProperty("min ennyi vendég kell:", building.getTemplate().getMinPlayersDefault());
			data.addProperty("max vendég kapacitás", building.getTemplate().getMaxPlayers());
			data.addProperty("sorban álló vendégek:", building.playersInQueue());
			GameBuildingPhase phase = building.getCurrentPhase();
			data.addProperty("állapot:", phase.name());
		}
		else if(p instanceof FoodBuilding) {
			FoodBuilding building = (FoodBuilding) p;
			data.addProperty("max vendég kapacitás", building.getTemplate().getMaxGuests());
			data.addProperty("sorban álló vendégek:", building.getGuestsWaitingInQueue());
			data.addProperty("státusz: ", building.isDoneBuilding() ? "várja a vendégkeket" : "építkezés alatt");
		}
		o.add("data", data);
		return data;
	}
}
