package hu.g14de.restapi.signals.in.game;

import com.google.gson.JsonElement;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;

public class SignalInGameTickspeed implements SignalIn {
	
	@Override
	public void execute(Connection c, JsonElement e) {
		c.getObservedGamestate().setTickSpeed(e.getAsInt());
	}
}
