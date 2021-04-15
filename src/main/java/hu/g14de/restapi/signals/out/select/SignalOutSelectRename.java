package hu.g14de.restapi.signals.out.select;

import hu.g14de.gamestate.GameState;
import hu.g14de.restapi.signals.SignalOut;

public class SignalOutSelectRename extends SignalOut {
	
	private String newname;
	private int ID;
	
	public SignalOutSelectRename(GameState state) {
		super("rename");
		this.newname = state.getName();
		this.ID = state.getId();
	}
	
}
