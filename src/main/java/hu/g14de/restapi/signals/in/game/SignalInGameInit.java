package hu.g14de.restapi.signals.in.game;

import com.google.gson.JsonElement;
import hu.g14de.gamestate.Cell;
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
		for (int x = 0; x < m.width(); x++) {
			for (int y = 0; y < m.height(); y++) {
				Cell cell = m.getCellAt(x,y);
				if(cell != null && cell.hasContent()) {
					c.sendSignal(new SignalOutGameUpdatecell(cell));
				}
			}
		}
	}
	
}
