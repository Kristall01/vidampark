package hu.g14de.gamestate.guestactions;

import hu.g14de.gamestate.Guest;

public class Done extends GuestState {
	
	public Done(Guest g) {
		super(g);
	}
	
	@Override
	public boolean readyToRemove() {
		return true;
	}
	
	@Override
	public void mounted() {
		getGuest().setVisible(false);
	}
	
}
