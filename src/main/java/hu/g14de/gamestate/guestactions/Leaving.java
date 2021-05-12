package hu.g14de.gamestate.guestactions;

import hu.g14de.gamestate.Cell;
import hu.g14de.gamestate.Guest;

import java.util.Queue;

public class Leaving extends Walking {
	
	public Leaving(Guest g, Queue<Cell> path) {
		super(g, path);
	}
	
	@Override
	protected void unreachable() {
		Guest g = getGuest();
		g.setState(new Done(g));
	}
	
	@Override
	protected void reachedTarget() {
		Guest g = getGuest();
		g.setState(new Done(g));
	}
	
}
