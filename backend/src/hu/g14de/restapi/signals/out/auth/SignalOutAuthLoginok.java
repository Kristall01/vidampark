package hu.g14de.restapi.signals.out.auth;

import hu.g14de.restapi.signals.SignalOut;

public class SignalOutAuthLoginok extends SignalOut {
	
	private String sessionid;
	
	public SignalOutAuthLoginok(String sessionID) {
		super("loginok");
		this.sessionid = sessionID;
	}
	
}
