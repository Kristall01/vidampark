package hu.g14de.restapi;

import com.google.gson.JsonObject;
import hu.g14de.Utils;
import hu.g14de.VidamparkApp;
import hu.g14de.gamestate.GameState;
import hu.g14de.restapi.signals.SignalDomain;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.SignalOut;
import hu.g14de.restapi.signals.out.common.SignalCommonOutLog;
import hu.g14de.restapi.signals.out.common.SignalOutConnectioncrash;
import hu.g14de.usermanager.User;
import io.javalin.websocket.WsContext;

public class Connection  {
	
	private WsContext context;
	private SignalDomain inDomain;
	private ConnectionServer server;
	private GameState observedGamestate;
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
	
	public VidamparkApp getApp() {
		return server.getApp();
	}
	
	public void signalReceive(String message)
	{
		try {
			JsonObject ob = Utils.fromJson(message).getAsJsonObject();
			String type = ob.get("type").getAsString();
			SignalIn in = server.getCommonDomain().getSignal(type);
			if(in == null) {
				in = inDomain.getSignal(type);
			}
			if(in == null) {
				crash("unknown signal '"+type+"' in domain '"+inDomain.getName()+"'");
				return;
			}
			in.execute(this, ob.get("data"));
		}
		catch (Exception e) {
			e.printStackTrace();
			crash(e.toString());
		}
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void sendLogMessage(String message) {
		sendSignal(new SignalCommonOutLog(message));
	}
	
	public void close() {
		this.setObservedGamestate(null);
		context.session.close();
	}
	
	public User getUser() {
		return user;
	}
	
	public void sendSignal(SignalOut o) {
		try {
			JsonObject ob = new JsonObject();
			ob.addProperty("type", o.type());
			ob.add("data", Utils.gson().toJsonTree(o.serializedData()));
			context.send(Utils.toJson(ob));
		}
		catch (Exception ex) {
			if(o instanceof SignalOutConnectioncrash) {
				return;
			}
			ex.printStackTrace();
			crash("failed to send signal. "+ex);
		}
	}
	
	public GameState getObservedGamestate() {
		return observedGamestate;
	}
	
	public void setObservedGamestate(GameState observedGamestate) {
		if(this.observedGamestate != null) {
			this.observedGamestate.removeObserver(this);
			this.observedGamestate = null;
		}
		if(observedGamestate != null) {
			this.observedGamestate = observedGamestate;
			this.observedGamestate.addObserver(this);
		}
	}
	
	public void crash(String message) {
		sendSignal(new SignalOutConnectioncrash(message));
		close();
	}
	
	public void setSignalInDomain(SignalDomain domain) {
		this.inDomain = domain;
	}
	
}
