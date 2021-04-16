package hu.g14de.restapi.signals.in.select;

import com.google.gson.JsonElement;
import hu.g14de.gamestate.GameState;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.common.SignalOutCommonSetscene;

public class SignalInSelectSelect implements SignalIn {
	
	@Override
	public void execute(Connection c, JsonElement e) {
		GameState state = c.getUser().getList().getGamestate(e.getAsInt());
		if(state == null) {
			c.crash("gamestate not found by ID");
			return;
		}
		c.setObservedGamestate(state);
		c.setSignalInDomain(c.getServer().getGameDomain());
		c.sendSignal(new SignalOutCommonSetscene("game"));
	}
}
