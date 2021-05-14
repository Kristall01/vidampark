package hu.g14de.gamestate.guestactions;

import hu.g14de.gamestate.Cell;
import hu.g14de.gamestate.Guest;
import hu.g14de.gamestate.mapelements.Joinable;

import java.util.Queue;

public class Wandering extends Walking {
	
	private Joinable target;
	
	public Wandering(Guest g, Joinable target, Queue<Cell> path) {
		super(g, path);
		this.target = target;
	}
	
	@Override
	protected void unreachable() {
		Guest g = getGuest();
		g.setState(new Idle(g));
	}
	
	@Override
	protected void reachedTarget() {
		Guest g = getGuest();
		if(target.joinGuest(g)) {
			g.setState(new Busy(g, target.getTemplate()));
		}
		else {
			g.setState(new Idle(g));
		}
	}
	
}
