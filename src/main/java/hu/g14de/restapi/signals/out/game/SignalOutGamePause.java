package hu.g14de.restapi.signals.out.game;

import hu.g14de.gamestate.GameState;
import hu.g14de.restapi.signals.SignalOut;

public class SignalOutGamePause extends SignalOut {
	
	private boolean paused;
	
	public SignalOutGamePause(GameState state) {
		super("paused");
		paused = state.isManualPause();
	}
	
	@Override
	public Object serializedData() {
		return paused;
	}
}
