package hu.g14de.gamestate.mapelements.basics;

import hu.g14de.TickCounter;
import hu.g14de.Tickable;
import hu.g14de.gamestate.mapelements.IBuildingTemplate;
import hu.g14de.gamestate.Cell;

public class PrimitivePlaceable extends BasicPlaceable {
	
	private boolean built;
	private Tickable t;
	
	public PrimitivePlaceable(Cell cell, IBuildingTemplate template, boolean instantBuild) {
		super(cell, template);
		if(instantBuild) {
			t = () -> {};
		}
		else {
			t = new TickCounter(() -> {
			
			}, getTemplate().getBuildTime());
		}
	}
	
	@Override
	public void receiveTick() {
		t.tick();
	}
	
}
