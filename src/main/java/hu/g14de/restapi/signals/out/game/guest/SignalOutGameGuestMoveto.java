package hu.g14de.restapi.signals.out.game.guest;

import hu.g14de.gamestate.Coordinate;
import hu.g14de.gamestate.Guest;
import hu.g14de.restapi.signals.SignalOut;

public class SignalOutGameGuestMoveto extends SignalOut {
	
	private int guestID, x, y;
	
	public SignalOutGameGuestMoveto(int guestID, int x, int y) {
		super("moveto");
		this.guestID = guestID;
		this.x = x;
		this.y = y;
	}
	
	public SignalOutGameGuestMoveto(Guest guest) {
		this(guest, guest.getPosition());
	}
	
	public SignalOutGameGuestMoveto(Guest guest, Coordinate cell) {
		this(guest.getID(), cell.getX(), cell.getY());
	}
	
}
