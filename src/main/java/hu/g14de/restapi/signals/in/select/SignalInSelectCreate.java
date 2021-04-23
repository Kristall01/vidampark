package hu.g14de.restapi.signals.in.select;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.select.SignalOutSelectAdd;

import java.util.HashMap;

public class SignalInSelectCreate implements SignalIn {


	@Override
	public void execute(Connection c, JsonElement e) {
		JsonObject o = (JsonObject)e;
		try
		{
			c.sendSignal(new SignalOutSelectAdd(c.getUser().getList().createGamestate(o.get("name").getAsString(),o.get("height").getAsInt(),o.get("width").getAsInt())));
		} catch (Exception ex) {
			//Sendsignal type error
		}
	}
	
}
