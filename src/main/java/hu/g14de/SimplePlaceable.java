package hu.g14de;

import hu.g14de.gamestate.Cell;

public class SimplePlaceable implements Placeable {
	
	private IBuildingTemplate template;
	private Cell cell;
	
	public SimplePlaceable(IBuildingTemplate template, Cell cell) {
		this.template = template;
		this.cell = cell;
	}
	
	@Override
	public Cell getCell() {
		return cell;
	}
	
	@Override
	public IBuildingTemplate getTemplate() {
		return template;
	}
	
}
