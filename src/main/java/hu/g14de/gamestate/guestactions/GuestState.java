package hu.g14de.gamestate.guestactions;

import hu.g14de.gamestate.Guest;

public class GuestState implements IGuestState {
	
	private Guest g;
	
	public GuestState(Guest g) {
		this.g = g;
	}
	
	@Override
	public Guest getGuest() {
		return g;
	}
}
