package hu.g14de.restapi.signals.in.select;

import com.google.gson.JsonElement;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.select.SignalOutSelectAdd;

public class SignalInSelectCreate implements SignalIn {
	
	@Override
	public void execute(Connection c, JsonElement e) {
		c.sendSignal(new SignalOutSelectAdd(c.getUser().getList().createGamestate()));
	}
	
}
