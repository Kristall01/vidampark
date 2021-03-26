package hu.g14de.restapi.signals.out.auth;

import hu.g14de.restapi.signals.SignalOut;

public class SignalOutAuthRegisterok extends SignalOut {
	
	private String sessionid;
	
	public SignalOutAuthRegisterok(String sessionid) {
		super("registerok");
		this.sessionid = sessionid;
	}
	
}
