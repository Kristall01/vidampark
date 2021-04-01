package hu.g14de.restapi.signals.in.game;

import com.google.gson.JsonElement;
import hu.g14de.GameState;
import hu.g14de.Map;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.game.SignalOutGameBalance;
import hu.g14de.restapi.signals.out.game.SignalOutGameMapsize;

public class SignalInGameInit implements SignalIn {
	
	@Override
	public void execute(Connection c, JsonElement e) {
		GameState g = c.getUser().getState();
		c.sendSignal(new SignalOutGameBalance(g.getBalance().getMoney()));
		Map m = g.getMap();
		c.sendSignal(new SignalOutGameMapsize(m.getxSize(), m.getySize()));
	}
	
}
