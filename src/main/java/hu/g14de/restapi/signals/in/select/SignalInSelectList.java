package hu.g14de.restapi.signals.in.select;

import com.google.gson.JsonElement;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.select.SignalOutSelectList;

public class SignalInSelectList implements SignalIn {
	
	@Override
	public void execute(Connection c, JsonElement e) {
		c.sendSignal(new SignalOutSelectList(c.getUser().getList()));
	}
}
