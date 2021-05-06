package hu.g14de.restapi.signals.out.game.guest;

import com.google.gson.JsonObject;
import hu.g14de.gamestate.Coordinate;
import hu.g14de.gamestate.Guest;
import hu.g14de.restapi.signals.SignalOut;

public class SignalOutGameGuestPosition extends SignalOut {
	
	private Guest guest;
	
	public SignalOutGameGuestPosition(Guest guest) {
		super("guestposition");
	}
	
	@Override
	public Object serializedData() {
		JsonObject o = new JsonObject();
		o.addProperty("id", guest.getID());
		Coordinate c = guest.getPosition();
		o.addProperty("x", c.getX());
		o.addProperty("y", c.getY());
		return o;
	}
}
