package hu.g14de.restapi;

import com.google.gson.JsonObject;
import hu.g14de.Utils;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.SignalDomain;
import hu.g14de.restapi.signals.SignalOut;
import hu.g14de.restapi.signals.out.SignalOutConnectioncrash;
import hu.g14de.restapi.signals.out.SignalOutLog;
import hu.g14de.usermanager.User;
import io.javalin.websocket.WsContext;

public class Connection  {
	
	private WsContext context;
	private SignalDomain inDomain;
	private ConnectionServer server;
	private User user;
	
	public Connection(ConnectionServer server, WsContext context)
	{
		this.context = context;
		this.server = server;
		this.inDomain = server.getAuthDomain();
	}
	
	public ConnectionServer getServer() {
		return server;
	}
	
	public void signalReceive(String message)
	{
		try {
			JsonObject ob = Utils.fromJson(message).getAsJsonObject();
			SignalIn in = inDomain.getSignal(ob.get("type").getAsString());
			in.execute(this, ob.get("data"));
		}
		catch (Exception e) {
			e.printStackTrace();
			sendSignal(new SignalOutConnectioncrash(e.toString()));
			close();
		}
	}
	
	public void sendLogMessage(String message) {
		sendSignal(new SignalOutLog(message));
	}
	
	public void close() {
		context.session.close();
	}
	
	public User getUser() {
		return user;
	}
	
	public void sendSignal(SignalOut o) {
		JsonObject ob = new JsonObject();
		ob.addProperty("type", o.type());
		ob.add("data", Utils.gson().toJsonTree(o));
		context.send(Utils.toJson(ob));
	}
	
	public void setSignalInDomain(SignalDomain domain) {
		this.inDomain = domain;
	}
	
}
