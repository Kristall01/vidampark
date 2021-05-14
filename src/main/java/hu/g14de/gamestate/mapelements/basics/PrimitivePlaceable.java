package hu.g14de.gamestate.mapelements.basics;

import hu.g14de.TickCounter;
import hu.g14de.Tickable;
import hu.g14de.gamestate.Cell;
import hu.g14de.gamestate.Guest;
import hu.g14de.gamestate.mapelements.IBuildingTemplate;

public class PrimitivePlaceable extends BasicPlaceable {
	
	private boolean built;
	private Tickable t;
	private boolean toBeRemoved = false;
	
	public PrimitivePlaceable(Cell cell, int id, IBuildingTemplate template, boolean instantBuild) {
		super(cell, id, template);
		Tickable boostCounter = new TickCounter(() -> {
			for (Guest guest : getCell().getMap().getGamestate().getGuestsCopy()) {
				guest.addFunLevel(1);
			}
		}, 5);
		if(instantBuild) {
			t = boostCounter;
		}
		else {
			t = new TickCounter(() -> {
				t = boostCounter;
			}, getTemplate().getBuildTime());
		}
	}
	
	@Override
	public void receiveTick() {
		t.tick();
	}
	
	@Override
	public void beginDestruct() {
		toBeRemoved = true;
	}
	
	@Override
	public boolean readyToBeRemoved() {
		return toBeRemoved;
	}
	
}
