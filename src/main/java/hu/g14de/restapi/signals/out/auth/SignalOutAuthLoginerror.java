package hu.g14de.restapi.signals.out.auth;

import hu.g14de.restapi.signals.SignalOut;

public class SignalOutAuthLoginerror extends SignalOut {
	
	private String message;
	
	public SignalOutAuthLoginerror(String message) {
		super("loginerror");
		this.message = message;
	}
	
}
