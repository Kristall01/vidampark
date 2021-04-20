package hu.g14de;

import hu.g14de.gamestate.Cell;

public class SimplePlaceable implements Placeable {
	
	private IBuildingTemplate template;
	private Cell cell;
	
	public SimplePlaceable(IBuildingTemplate template, Cell cell) {
		this.template = template;
	}
	
	@Override
	public Cell getCell() {
		return null;
	}
	
	@Override
	public IBuildingTemplate getTemplate() {
		return template;
	}
	
}
