package hu.g14de.restapi;

import hu.g14de.VidamparkApp;
import hu.g14de.restapi.signals.SignalDomain;
import hu.g14de.restapi.signals.in.auth.SignalInAuthLogin;
import hu.g14de.restapi.signals.in.auth.SignalInAuthRegister;
import hu.g14de.restapi.signals.in.auth.SignalInAuthSessionid;
import io.javalin.Javalin;
import io.javalin.websocket.WsCloseContext;
import io.javalin.websocket.WsConnectContext;
import io.javalin.websocket.WsErrorContext;
import io.javalin.websocket.WsMessageContext;

public class ConnectionServer {
	
	private final Javalin javalin;
	private final VidamparkApp app;
	private SignalDomain authDomain = new SignalDomain();
	private SignalDomain gameDomain = new SignalDomain();
	
	public ConnectionServer(VidamparkApp app, int port) {
		this.app = app;
		
		authDomain.add("login", new SignalInAuthLogin());
		authDomain.add("sessionid", new SignalInAuthSessionid());
		authDomain.add("register", new SignalInAuthRegister());
		
		javalin = Javalin.create(config -> {
			config.showJavalinBanner = false;
		});
		
		javalin.ws("/", c -> {
			c.onMessage(this::handleWsMessage);
			c.onClose(this::handleWsClose);
			c.onConnect(this::handleWsConnect);
			c.onError(this::handleWsError);
		});
		
		javalin.start(port);
	}
	
	
	private void handleWsConnect(WsConnectContext connectEvent) {
		connectEvent.attribute("connection", new Connection(this, connectEvent));
	}
	
	private void handleWsMessage(WsMessageContext messageEvent) {
		messageEvent.<Connection>attribute("connection").signalReceive(messageEvent.message());
	}
	
	private void handleWsError(WsErrorContext errorEvent) {
		errorEvent.<Connection>attribute("connection").close();
	}
	
	private void handleWsClose(WsCloseContext closeEvent) {
		closeEvent.<Connection>attribute("connection").close();
	}
	
	public VidamparkApp getApp() {
		return app;
	}
	
	public SignalDomain getAuthDomain() {
		return authDomain;
	}
}
