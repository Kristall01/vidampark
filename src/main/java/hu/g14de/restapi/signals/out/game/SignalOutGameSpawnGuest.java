package hu.g14de.restapi.signals.out.game;

import hu.g14de.restapi.signals.SignalOut;

public class SignalOutGameSpawnGuest extends SignalOut {
	
	private int x,y;
	
	public SignalOutGameSpawnGuest(int x, int y) {
		super("spawnguest");
		this.y = y;
		this.x = x;
	}
}
