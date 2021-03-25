package hu.g14de.restapi.signals.out;

import hu.g14de.restapi.signals.SignalOut;

public class SignalOutConnectioncrash extends SignalOut {
	
	private String reason;
	
	public SignalOutConnectioncrash(String s) {
		super("connectioncrash");
		this.reason = s;
	}
}
