package hu.g14de.gamestate.mapelements;

import hu.g14de.gamestate.Cell;
import hu.g14de.gamestate.mapelements.basics.Road;

public interface Placeable
{
	
	Cell getCell();
	IBuildingTemplate getTemplate();
	void receiveTick();
	Road getRandomRoadConnection();
	int getID();
}
