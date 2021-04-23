package hu.g14de.restapi.signals.in.game;

import com.google.gson.JsonElement;
import hu.g14de.gamestate.mapelements.Placeable;
import hu.g14de.gamestate.GameState;
import hu.g14de.gamestate.IMap;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.game.*;

public class SignalInGameInit implements SignalIn {
	
	@Override
	public void execute(Connection c, JsonElement e) {
		GameState g = c.getObservedGamestate();
		c.sendSignal(new SignalOutGameBalance(g.getBalance().getMoney()));
		IMap m = g.getMap();
		c.sendSignal(new SignalOutGameMapsize(m.width(), m.height()));
		if(c.getObservedGamestate().isStarted()) {
			c.sendSignal(new SignalOutGameStartpark());
		}
		c.sendSignal(new SignalOutGameCatalog(c.getObservedGamestate().getCatalog().getAvailableTemplates()));
		for (Placeable building : c.getObservedGamestate().getMap().getBuildings()) {
			c.sendSignal(new SignalOutGameUpdatecell(building.getCell(), 0));
		}
		c.sendSignal(new SignalOutGameInitiated());
	}
	
}
