package hu.g14de.restapi.signals.out.select;

import hu.g14de.gamestate.GameState;
import hu.g14de.restapi.signals.SignalOut;

public class SignalOutSelectAdd extends SignalOut {

	private GameState state;
	
	public SignalOutSelectAdd(GameState state) {
		super("add");
		this.state = state;
	}
	
	@Override
	public Object serializedData() {
		return state.getAsListEntry();
	}
	
}
