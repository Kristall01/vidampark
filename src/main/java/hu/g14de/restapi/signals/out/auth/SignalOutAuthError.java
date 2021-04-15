package hu.g14de.restapi.signals.out.auth;

import hu.g14de.restapi.signals.SignalOut;

public class SignalOutAuthError extends SignalOut {
	
	private String message;
	
	public SignalOutAuthError(String message) {
		super("autherror");
		this.message = message;
	}
}
