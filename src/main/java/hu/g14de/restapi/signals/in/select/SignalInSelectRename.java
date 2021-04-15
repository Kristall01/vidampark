package hu.g14de.restapi.signals.in.select;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hu.g14de.gamestate.GameState;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.select.SignalOutSelectRename;

public class SignalInSelectRename implements SignalIn {
	
	@Override
	public void execute(Connection c, JsonElement e) {
		JsonObject o = (JsonObject)e;
		GameState state = c.getUser().getList().getGamestate(o.get("id").getAsInt());
		if(state == null) {
			c.crash("invalid gamestate ID");
			return;
		}
		state.setName(o.get("name").getAsString());
		c.sendSignal(new SignalOutSelectRename(state));
	}
	
}
