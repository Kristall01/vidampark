package hu.g14de.gamestate.mapelements.basics;

import hu.g14de.gamestate.mapelements.IBuildingTemplate;
import hu.g14de.gamestate.mapelements.Placeable;
import hu.g14de.gamestate.Cell;
import hu.g14de.gamestate.Coordinate;

public abstract class BasicPlaceable implements Placeable {
	
	private Cell cell;
	private IBuildingTemplate template;
	private int id;
	
	public BasicPlaceable(Cell cell, int id, IBuildingTemplate template) {
		this.cell = cell;
		this.template = template;
	}
	
	@Override
	public Road getRandomRoadConnection() {
		Coordinate c = cell.getCoordinate();
		return (Road)cell.getMap().getRoadConnection(c.getX(), c.getY(), template.width(), template.height()).getContent();
	}
	
	@Override
	public Cell getCell() {
		return cell;
	}
	
	@Override
	public IBuildingTemplate getTemplate() {
		return template;
	}
	
	@Override
	public int getID() {
		return this.id;
	}
}
