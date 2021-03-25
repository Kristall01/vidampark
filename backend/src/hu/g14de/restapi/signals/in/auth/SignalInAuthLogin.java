package hu.g14de.restapi.signals.in.auth;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hu.g14de.TranslatedException;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.auth.SignalOutAuthLoginerror;
import hu.g14de.restapi.signals.out.auth.SignalOutAuthLoginok;
import hu.g14de.usermanager.User;
import hu.g14de.usermanager.UserManager;

public class SignalInAuthLogin implements SignalIn
{
	
	@Override
	public void execute(Connection c, JsonElement e)
	{
		try {
			JsonObject ob = e.getAsJsonObject();
			User u = c.getServer().getApp().getUserManager().getUserByEmail(ob.get("email").getAsString());
			if(u == null) {
				throw new UserManager.InvalidCredentialsException();
			}
			if(!u.matchPassword(ob.get("password").getAsString())) {
				throw new UserManager.InvalidCredentialsException();
			}
			c.sendSignal(new SignalOutAuthLoginok());
		}
		catch(TranslatedException ex)
		{
			String reason = ex.translate(c.getServer().getApp().getUserManager().getVidamparkApp().getLang());
			c.sendSignal(new SignalOutAuthLoginerror(reason));
		}
	}
	
}
