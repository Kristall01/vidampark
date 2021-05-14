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
	
	private LinkedList<Guest> waitingGuests;
	private LinkedList<EatingSession> eatings = new LinkedList<>();
	private Tickable tickable;
	
	public FoodBuilding(IFoodTemplate template, int id, Cell cell, boolean instantBuild) {
		super(cell, id, template);
		Runnable switchToLive = () -> {
			waitingGuests = new LinkedList<>();
			tickable = this::tick;
		};
		if(instantBuild) {
			switchToLive.run();
		}
		else {
			tickable = new TickCounter(switchToLive, template.getBuildTime());
		}
	}
	
	@Override
	public void receiveTick() {
		tickable.tick();
	}
	
	private void tick() {
		Iterator<EatingSession> it = eatings.iterator();
		while(it.hasNext()) {
			EatingSession s = it.next();
			s.tick();
			if(s.remove()) {
				getCell().getMap().getGamestate().dropGuestAt(s.guest, getRandomRoadConnection().getCell());
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
		if(eatings.size() != getTemplate().getMaxGuests()) {
			eatings.add(new EatingSession(guest, getTemplate().getEatTime()));
		}
		else {
			waitingGuests.add(guest);
		}
		return true;
	}
	
	@Override
	public IFoodTemplate getTemplate() {
		return (IFoodTemplate) super.getTemplate();
	}
	
	public boolean readyToQueue() {
		return waitingGuests != null;
	}
	
	private static class EatingSession {
	
		private final Guest guest;
		private int eatTicks;
		
		public EatingSession(Guest g, int e) {
			guest = g;
			eatTicks = e;
		}
		
		public void tick() {
			--eatTicks;
		}
		
		boolean remove() {
			return eatTicks <= 0;
		}
		
	}
	
}
