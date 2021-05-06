package hu.g14de.restapi.signals.out.game.guest;

import hu.g14de.gamestate.Guest;
import hu.g14de.restapi.signals.SignalOut;

public class SignalOutGameGuestDespawn extends SignalOut {
	
	private int guestid;
	
	public SignalOutGameGuestDespawn(Guest guest) {
		super("guestdespawn");
		this.guestid = guest.getID();
	}
	
	@Override
	public Object serializedData() {
		return guestid;
	}
}
