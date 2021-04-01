package hu.g14de.restapi.signals.out.auth;

import hu.g14de.restapi.signals.SignalOut;

public class SignalOutAuthRegistererror extends SignalOut {
	
	private String message;
	
	public SignalOutAuthRegistererror(String message) {
		super("registererror");
		this.message = message;
	}
	
}
