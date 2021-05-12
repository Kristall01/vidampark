package hu.g14de.gamestate.mapelements;

import hu.g14de.gamestate.Guest;

public interface Joinable extends Placeable {
	
	boolean joinGuest(Guest g);
	
}
