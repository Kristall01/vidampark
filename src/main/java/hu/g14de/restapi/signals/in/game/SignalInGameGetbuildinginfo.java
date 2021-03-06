package hu.g14de.restapi.signals.in.game;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hu.g14de.gamestate.Cell;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.game.SignalOutGameBuildinginfo;

public class SignalInGameGetbuildinginfo implements SignalIn {
	
	@Override
	public void execute(Connection c, JsonElement e) {
		JsonObject o = e.getAsJsonObject();
		int x = o.get("x").getAsInt();
		int y = o.get("y").getAsInt();
		
		Cell cell = c.getObservedGamestate().getMap().getCellAt(x, y);
		if(cell == null || !cell.hasContent()) {
			return;
		}
		c.sendSignal(new SignalOutGameBuildinginfo(cell.getContent()));
	}
	
}
