package hu.g14de.restapi;

import hu.g14de.VidamparkApp;
import hu.g14de.restapi.signals.SignalDomain;
import hu.g14de.restapi.signals.in.auth.SignalInAuthLogin;
import hu.g14de.restapi.signals.in.auth.SignalInAuthRegister;
import hu.g14de.restapi.signals.in.auth.SignalInAuthSessionid;
import hu.g14de.restapi.signals.in.common.SignalInCommonLogout;
import hu.g14de.restapi.signals.in.game.*;
import hu.g14de.restapi.signals.in.select.SignalInSelectCreate;
import hu.g14de.restapi.signals.in.select.SignalInSelectList;
import hu.g14de.restapi.signals.in.select.SignalInSelectRename;
import hu.g14de.restapi.signals.in.select.SignalInSelectSelect;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.websocket.WsCloseContext;
import io.javalin.websocket.WsConnectContext;
import io.javalin.websocket.WsErrorContext;
import io.javalin.websocket.WsMessageContext;

public class ConnectionServer {
	
	private final Javalin javalin;
	private final VidamparkApp app;
	private SignalDomain authDomain = new SignalDomain("auth");
	private SignalDomain gameDomain = new SignalDomain("game");
	private SignalDomain selectDomain = new SignalDomain("select");
	private SignalDomain commonDomain = new SignalDomain("common");
	
	public ConnectionServer(VidamparkApp app, int port) {
		this.app = app;
		
		commonDomain.add("logout", new SignalInCommonLogout());
		
		authDomain.add("login", new SignalInAuthLogin());
		authDomain.add("sessionid", new SignalInAuthSessionid());
		authDomain.add("register", new SignalInAuthRegister());
		
		gameDomain.add("init", new SignalInGameInit());
		gameDomain.add("placebuilding", new SignalInGamePlaceBuilding());
		gameDomain.add("startpark", new SignalInGameStartpark());
		gameDomain.add("leave", new SignalInGameLeave());
		gameDomain.add("menu", new SignalInGameMenu());
		
		selectDomain.add("create", new SignalInSelectCreate());
		selectDomain.add("select", new SignalInSelectSelect());
		selectDomain.add("list", new SignalInSelectList());
		selectDomain.add("rename", new SignalInSelectRename());
		
		javalin = Javalin.create(config -> {
			config.showJavalinBanner = false;
			config.addStaticFiles("/", "frontend", Location.EXTERNAL);
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
	
	public SignalDomain getGameDomain() {
		return gameDomain;
	}
	
	public SignalDomain getCommonDomain() {
		return commonDomain;
	}
	
	public SignalDomain getSelectDomain() {
		return selectDomain;
	}
}
