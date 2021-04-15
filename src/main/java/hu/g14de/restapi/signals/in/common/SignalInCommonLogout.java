package hu.g14de.restapi.signals.in.common;

import com.google.gson.JsonElement;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.common.SignalOutCommonLogout;

public class SignalInCommonLogout implements SignalIn {
	
	@Override
	public void execute(Connection c, JsonElement e) {
		if(c.isAuthenticated()) {
			c.sendSignal(new SignalOutCommonLogout());
			c.setSignalInDomain(c.getServer().getAuthDomain());
		}
	}
	
}
