package hu.g14de.restapi.signals.out.select;

import hu.g14de.restapi.signals.SignalOut;

public class SignalOutSelectRename extends SignalOut {
	
	private String newname;
	private int ID;
	
	public SignalOutSelectRename(String newname, int ID) {
		super("rename");
		this.newname = newname;
		this.ID = ID;
	}
	
}
