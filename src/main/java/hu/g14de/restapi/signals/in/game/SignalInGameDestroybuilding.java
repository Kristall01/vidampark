package hu.g14de.restapi.signals.in.game;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hu.g14de.gamestate.IMap;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;

public class SignalInGameDestroybuilding implements SignalIn {
	
	@Override
	public void execute(Connection c, JsonElement e) {
		IMap m = c.getObservedGamestate().getMap();
		JsonObject o = e.getAsJsonObject();
		int x = o.get("x").getAsInt();
		int y = o.get("y").getAsInt();
		m.destroyBuilding(x,y);
	}
	
}
