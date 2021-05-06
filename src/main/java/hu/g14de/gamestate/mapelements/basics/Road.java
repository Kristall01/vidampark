package hu.g14de.gamestate.mapelements.basics;

import hu.g14de.gamestate.mapelements.IBuildingTemplate;
import hu.g14de.gamestate.Cell;

public class Road extends PrimitivePlaceable {
	
	public Road visitedFrom = null;
	
	public Road(Cell cell, IBuildingTemplate template, boolean instantBuild) {
		super(cell, template, instantBuild);
	}
	
}
