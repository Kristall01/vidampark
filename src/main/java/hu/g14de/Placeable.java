package hu.g14de;

import hu.g14de.gamestate.Cell;

public interface Placeable
{
	
	Cell getCell();
	IBuildingTemplate getTemplate();
	
}
