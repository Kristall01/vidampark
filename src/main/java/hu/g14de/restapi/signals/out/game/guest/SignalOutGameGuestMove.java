package hu.g14de.restapi.signals.out.game.guest;

import hu.g14de.gamestate.Cell;
import hu.g14de.gamestate.Coordinate;
import hu.g14de.gamestate.Guest;
import hu.g14de.restapi.signals.SignalOut;

import java.util.Arrays;
import java.util.Collection;

public class SignalOutGameGuestMove extends SignalOut {
	
	private int guestID;
	private Collection<Coordinate> path;
	
	public SignalOutGameGuestMove(Guest guest, Collection<Cell> path) {
		super("moveguest");
		this.guestID = guest.getID();
		Coordinate[] coords = new Coordinate[path.size()];
		int pathSize = path.size();
		int i = 0;
		for (Cell cell : path) {
			coords[i++] = cell.getCoordinate();
		}
		this.path = Arrays.asList(coords);
	}
}
