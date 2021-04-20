package hu.g14de;

import hu.g14de.gamestate.Cell;

public interface IBuildingTemplate {
	
	boolean needsRoadConnection();
	String type();
	String getNickname();
	int getBuildCost();
	int getBuildTime();
	boolean isRoad();
	Placeable createInstance(Cell at);
	int width();
	int height();
	String mapIcon();
	String shopIcon();
	Object serialize();
	
}
