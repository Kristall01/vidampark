package hu.g14de.gamestate.mapelements.basics;

import hu.g14de.gamestate.mapelements.IBuildingTemplate;
import hu.g14de.gamestate.Cell;

public class Road extends PrimitivePlaceable {
	
	public Road visitedFrom = null;
	
	public Road(Cell cell, int id, IBuildingTemplate template, boolean instantBuild) {
		super(cell, id, template, instantBuild);
	}
	
}
