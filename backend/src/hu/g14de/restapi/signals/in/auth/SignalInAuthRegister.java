package hu.g14de.restapi.signals.in.auth;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hu.g14de.TranslatedException;
import hu.g14de.Utils;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.auth.SignalOutAuthRegistererror;
import hu.g14de.restapi.signals.out.auth.SignalOutAuthRegisterok;
import hu.g14de.usermanager.User;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class SignalInAuthRegister implements SignalIn {
	
	@Override
	public void execute(Connection c, JsonElement e) {
		try {
			JsonObject o = e.getAsJsonObject();
			String email = o.get("email").getAsString();
			String password = o.get("password").getAsString();
			String password2 = o.get("password2").getAsString();
			if(email.length() == 0 | password.length() == 0 || password2.length() == 0) {
				throw new TranslatedException("error.restapi.signalin.auth.fill-all");
			}
			if(!password2.equals(password)) {
				throw new TranslatedException("error.restapi.signalin.signalinauthregister.password-mismatch");
			}
			User u = c.getServer().getApp().getUserManager().createUser(email, password);
			
			JsonObject token = new JsonObject();
			token.addProperty("email", email);
			token.addProperty("password", password);
			
			c.setUser(u);
			c.sendSignal(new SignalOutAuthRegisterok(Base64.getEncoder().encodeToString(Utils.toJson(token).getBytes(StandardCharsets.UTF_8))));
			c.setSignalInDomain(c.getServer().getGameDomain());
		}
		catch (TranslatedException ex) {
			String reason = ex.translate(c.getServer().getApp().getUserManager().getVidamparkApp().getLang());
			c.sendSignal(new SignalOutAuthRegistererror(reason));
		}
	}
	
}
