package hu.g14de.gamestate.mapelements.game;

import hu.g14de.gamestate.mapelements.IBuildingTemplate;

public interface IGameTemplate extends IBuildingTemplate {

	int getMaxPlayers();
	int getPlaytime();
	int getRoundCost();
	int getMinPlayersDefault();
	int getRoundMoralBoost();
	int getIdleCost();

}
