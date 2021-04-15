package hu.g14de.restapi.signals.in.game;

import com.google.gson.JsonElement;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.common.SignalOutCommonSetscene;

public class SignalInGameLeave implements SignalIn {
	
	@Override
	public void execute(Connection c, JsonElement e) {
		c.setObservedGamestate(null);
		c.setSignalInDomain(c.getServer().getSelectDomain());
		c.sendSignal(new SignalOutCommonSetscene("select"));
	}
	
}
