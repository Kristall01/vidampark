package hu.g14de.restapi.signals.out.select;

import hu.g14de.restapi.signals.SignalOut;

public class SignalOutSelectDelete extends SignalOut {
	
	private int ID;
	
	public SignalOutSelectDelete(int ID) {
		super("delete");
		this.ID = ID;
	}
	
}
