package hu.g14de.restapi.signals.in.auth;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hu.g14de.TranslatedException;
import hu.g14de.Utils;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.auth.SignalOutAuthLoginerror;
import hu.g14de.restapi.signals.out.auth.SignalOutAuthLoginok;
import hu.g14de.restapi.signals.out.auth.SignalOutAuthRegisterok;
import hu.g14de.usermanager.User;
import hu.g14de.usermanager.UserManager;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SignalInAuthLogin implements SignalIn
{
	
	@Override
	public void execute(Connection c, JsonElement e)
	{
		try {
			JsonObject ob = e.getAsJsonObject();
			String email = ob.get("email").getAsString();
			String password = ob.get("password").getAsString();
			User u = c.getServer().getApp().getUserManager().getUserByEmail(email);
			if(u == null) {
				throw new UserManager.InvalidCredentialsException();
			}
			if(!u.matchPassword(password)) {
				throw new UserManager.InvalidCredentialsException();
			}
			
			JsonObject token = new JsonObject();
			token.addProperty("email", email);
			token.addProperty("password", password);
			c.sendSignal(new SignalOutAuthLoginok(Base64.getEncoder().encodeToString(Utils.toJson(token).getBytes(StandardCharsets.UTF_8))));
			c.setUser(u);
			c.setSignalInDomain(c.getServer().getGameDomain());
		}
		catch(TranslatedException ex)
		{
			String reason = ex.translate(c.getServer().getApp().getUserManager().getVidamparkApp().getLang());
			c.sendSignal(new SignalOutAuthLoginerror(reason));
		}
	}
	
}
