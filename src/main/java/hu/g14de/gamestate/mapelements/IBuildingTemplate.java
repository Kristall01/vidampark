package hu.g14de.gamestate.mapelements;

import hu.g14de.gamestate.Cell;

public interface IBuildingTemplate {
	
	boolean needsRoadConnection();
	String type();
	String getNickname();
	int getBuildCost();
	int getBuildTime();
	boolean isRoad();
	Placeable createInstance(Cell at, boolean instantBuild);
	int width();
	int height();
	String mapIcon();
	String shopIcon();
	Object serialize();
	int getIdleCost();
	
}
