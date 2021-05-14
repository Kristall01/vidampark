package hu.g14de.gamestate.mapelements.food;

import hu.g14de.TickCounter;
import hu.g14de.Tickable;
import hu.g14de.gamestate.Cell;
import hu.g14de.gamestate.Guest;
import hu.g14de.gamestate.mapelements.Joinable;
import hu.g14de.gamestate.mapelements.basics.BasicPlaceable;

import java.util.Iterator;
import java.util.LinkedList;

public class FoodBuilding extends BasicPlaceable implements Joinable {
	
	private LinkedList<Guest> waitingGuests = new LinkedList<>();
	private LinkedList<EatingSession> eatings = new LinkedList<>();
	private Tickable tickable;
	private FoodBuildingPhase phase;
	
	public FoodBuilding(IFoodTemplate template, int id, Cell cell, boolean instantBuild) {
		super(cell, id, template);
		Runnable switchToLive = () -> {
			waitingGuests = new LinkedList<>();
			tickable = this::tick;
			phase = FoodBuildingPhase.RUNNING;
		};
		if(instantBuild) {
			switchToLive.run();
		}
		else {
			phase = FoodBuildingPhase.BUILDING;
			tickable = new TickCounter(switchToLive, template.getBuildTime());
		}
	}
	
	public boolean isDoneBuilding() {
		return phase != FoodBuildingPhase.BUILDING;
	}
	
	@Override
	public void receiveTick() {
		tickable.tick();
	}
	
	@Override
	public void beginDestruct() {
		phase = FoodBuildingPhase.DESTUCT;
		Cell c = getRandomRoadConnection().getCell();
		for (Guest waitingGuest : waitingGuests) {
			waitingGuest.notifyGameover(c);
		}
		waitingGuests.clear();
	}
	
	@Override
	public boolean readyToBeRemoved() {
		return phase == FoodBuildingPhase.DESTUCT && eatings.isEmpty();
	}
	
	private void tick() {
		Iterator<EatingSession> it = eatings.iterator();
		while(it.hasNext()) {
			EatingSession s = it.next();
			s.tick();
			if(s.remove()) {
				s.guest.addFoodLevel(this.getTemplate().getFoodBoost());
				s.guest.notifyGameover(getRandomRoadConnection().getCell());
				it.remove();
			}
		}
		int min = Math.min(waitingGuests.size(), getTemplate().getMaxGuests()-eatings.size());
		for (int i = 0; i < min; i++) {
			eatings.add(new EatingSession(waitingGuests.poll(), getTemplate().getEatTime()));
		}
	}
	
	@Override
	public boolean joinGuest(Guest guest) {
		if(!readyToQueue()) {
			return false;
		}
		if(eatings.size() != getTemplate().getMaxGuests()) {
			eatings.add(new EatingSession(guest, getTemplate().getEatTime()));
		}
		else {
			waitingGuests.add(guest);
		}
		return true;
	}
	
	public int getGuestsWaitingInQueue() {
		return waitingGuests.size();
	}
	
	@Override
	public IFoodTemplate getTemplate() {
		return (IFoodTemplate) super.getTemplate();
	}
	
	public boolean readyToQueue() {
		return phase == FoodBuildingPhase.RUNNING;
	}
	
	private static class EatingSession {
	
		private final Guest guest;
		private int eatTicks;
		
		public EatingSession(Guest g, int e) {
			guest = g;
			eatTicks = e;
		}

		//public getGuest() {return this.guest;}

		public void tick() {
			--eatTicks;
		}
		
		boolean remove() {
			return eatTicks <= 0;
		}
		
	}
	
	
	
}
