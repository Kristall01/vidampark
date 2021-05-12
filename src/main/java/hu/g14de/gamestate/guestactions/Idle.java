package hu.g14de.gamestate.guestactions;

import hu.g14de.Utils;
import hu.g14de.gamestate.Guest;
import hu.g14de.gamestate.IMap;
import hu.g14de.gamestate.mapelements.Joinable;

public class Idle extends GuestState {

	enum Target {Food, Fun, Leave}

	public Idle(Guest g) {
		super(g);
	}
	
	private void tryFood() {
		IMap map = getGuest().getParent().getMap();
		Joinable randomBuilding = map.getRandomFoodExecpt(getGuest().getLastBuildingEntered());
		if(randomBuilding == null) {
			getGuest().leavePark();
			return;
		}
		getGuest().goTo(randomBuilding);
	}
	
	private void tryGame() {
		IMap map = getGuest().getParent().getMap();
		Joinable randomBuilding = map.getRandomGameExcept(getGuest().getLastBuildingEntered());
		if(randomBuilding == null) {
			getGuest().leavePark();
			return;
		}
		getGuest().goTo(randomBuilding);
	}

	public Target thinkAboutLife() //FIXME: Naming conventions
	{
		int lowerBoundForFun = 1; //FIXME: Decide when enough is enough for the guest
		int lowerBoundForFood = 1; //FIXME: Decide when enough is enough for the guest
		int lowerBoundForMoney = 0; //FIXME: Decide when enough is enough for the guest

		int funLevel = this.getGuest().getFunLevel();
		int foodLevel = this.getGuest().getFoodLevel();
		int money = this.getGuest().getMoney();

		if(funLevel > foodLevel && foodLevel >lowerBoundForFood &&funLevel > lowerBoundForFun)
		{
			return Target.Food;
		}
		else if(funLevel < foodLevel && foodLevel >lowerBoundForFood &&funLevel > lowerBoundForFun)
		{
			return Target.Fun;
		}


		return Target.Leave;
	}

	@Override
	public void tick() {
		switch (thinkAboutLife())
		{
			case Food:
				tryFood();
				break;
			case Fun:
				tryGame();
				break;
			default:
				this.getGuest().leavePark(); //FIXME: Not sure this is how it works
				break;
		}
	}
}
