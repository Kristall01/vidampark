package hu.g14de.restapi.signals.out.game.guest;

import hu.g14de.gamestate.Coordinate;
import hu.g14de.gamestate.Guest;
import hu.g14de.restapi.signals.SignalOut;

public class SignalOutGameGuestSpawn extends SignalOut {
	
	private int x,y,id;
	private String color;
	
	public SignalOutGameGuestSpawn(Guest guest) {
		this(guest, guest.getPosition());
	}
	
	public SignalOutGameGuestSpawn(Guest guest, Coordinate c) {
		super("spawnguest");
		this.y = c.getY();
		this.x = c.getX();
		this.id = guest.getID();
		this.color = guest.getColor();
	}
	
}
