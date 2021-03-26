package hu.g14de.restapi.signals.in.auth;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hu.g14de.TranslatedException;
import hu.g14de.Utils;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.auth.SignalOutAuthSessionerror;
import hu.g14de.restapi.signals.out.auth.SignalOutAuthSessionok;
import hu.g14de.usermanager.User;
import hu.g14de.usermanager.UserManager;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SignalInAuthSessionid implements SignalIn
{
	
	@Override
	public void execute(Connection c, JsonElement e)
	{
		try {
			String sessionIDRaw = e.getAsJsonObject().get("sessionid").getAsString();
			String decoded = new String(Base64.getDecoder().decode(sessionIDRaw), StandardCharsets.UTF_8);
			JsonObject credentialsObject = Utils.fromJson(decoded).getAsJsonObject();
			
			User u = c.getServer().getApp().getUserManager().getUserByEmail(credentialsObject.get("email").getAsString());
			if(u == null) {
				throw new UserManager.InvalidCredentialsException();
			}
			if(!u.matchPassword(credentialsObject.get("password").getAsString())) {
				throw new UserManager.InvalidCredentialsException();
			}
			c.sendSignal(new SignalOutAuthSessionok());
			c.setSignalInDomain(c.getServer().getGameDomain());
		}
		catch (TranslatedException ex) {
			c.sendSignal(new SignalOutAuthSessionerror());
		}
	}
	
}
