package hu.g14de.restapi.signals.out.game;

import hu.g14de.restapi.signals.SignalOut;

public class SignalOutGameLog extends SignalOut {
	
	private String message;
	
	public SignalOutGameLog(String message) {
		super("log");
		
		this.message = message;
	}
	
}
