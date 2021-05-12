package hu.g14de.gamestate.guestactions;

import hu.g14de.gamestate.Guest;
import hu.g14de.gamestate.mapelements.IBuildingTemplate;

public class Busy extends GuestState {
	
	private IBuildingTemplate temp;
	
	public Busy(Guest g, IBuildingTemplate template) {
		super(g);
		this.temp = template;
	}
	
	@Override
	public void mounted() {
		Guest g = getGuest();
		g.setLastJoined(temp);
		getGuest().setVisible(false);
	}
	
	@Override
	public void unmounted() {
		getGuest().setVisible(true);
	}
	
}
