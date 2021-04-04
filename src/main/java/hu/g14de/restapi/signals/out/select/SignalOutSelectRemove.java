package hu.g14de.restapi.signals.out.select;

import hu.g14de.restapi.signals.SignalOut;

public class SignalOutSelectRemove extends SignalOut {
	
	private int ID;
	
	public SignalOutSelectRemove(int ID) {
		super("remove");
		this.ID = ID;
	}
	
}
