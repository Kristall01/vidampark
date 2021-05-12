package hu.g14de.gamestate.guestactions;

import hu.g14de.Utils;
import hu.g14de.gamestate.Guest;
import hu.g14de.gamestate.IMap;
import hu.g14de.gamestate.mapelements.Joinable;

public class Idle extends GuestState {
	
	public Idle(Guest g) {
		super(g);
	}
	
	private void tryFood() {
		IMap map = getGuest().getParent().getMap();
		Joinable randomBuilding = map.getRandomFoodExecpt(getGuest().getLastBuildingEntered());
		if(randomBuilding == null) {
			getGuest().leavePark();
			return;
		}
		getGuest().goTo(randomBuilding);
	}
	
	private void tryGame() {
		IMap map = getGuest().getParent().getMap();
		Joinable randomBuilding = map.getRandomGameExcept(getGuest().getLastBuildingEntered());
		if(randomBuilding == null) {
			getGuest().leavePark();
			return;
		}
		getGuest().goTo(randomBuilding);
	}
	
	@Override
	public void tick() {
		boolean targetCategory = Utils.getRandom().nextBoolean();
		if(targetCategory) {
			tryFood();
		}
		else {
			tryGame();
		}
	}
}
