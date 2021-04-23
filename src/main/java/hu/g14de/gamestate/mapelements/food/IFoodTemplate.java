package hu.g14de.gamestate.mapelements.food;

import hu.g14de.gamestate.mapelements.IBuildingTemplate;

public interface IFoodTemplate extends IBuildingTemplate {
	
	int getMaxGuests();
	int getEatTime();
	int getFoodBoost();
	
}
