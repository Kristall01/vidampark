package hu.g14de.restapi.signals.in.game;

import com.google.gson.JsonElement;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.common.SignalCommonOutLog;

public class SignalInGamePlaceroad implements SignalIn {
	
	@Override
	public void execute(Connection c, JsonElement e) {
		c.sendSignal(new SignalCommonOutLog("got placeroad signal"));
	}
	
}
