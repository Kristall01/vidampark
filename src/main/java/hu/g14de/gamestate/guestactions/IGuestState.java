package hu.g14de.gamestate.guestactions;

import hu.g14de.Tickable;
import hu.g14de.gamestate.Guest;

public interface IGuestState extends Tickable {
	
	default void tick() {}
	
	Guest getGuest();
	
	default boolean readyToRemove() {
		return false;
	}
	
	default void mounted() {}
	default void unmounted() {}

}
