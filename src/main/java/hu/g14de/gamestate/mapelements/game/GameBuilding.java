package hu.g14de.gamestate.mapelements.game;

import hu.g14de.TickCounter;
import hu.g14de.Tickable;
import hu.g14de.TranslatedException;
import hu.g14de.gamestate.Cell;
import hu.g14de.gamestate.Guest;
import hu.g14de.gamestate.mapelements.Joinable;
import hu.g14de.gamestate.mapelements.basics.BasicPlaceable;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

public class GameBuilding extends BasicPlaceable implements Joinable {
	
	private Guest[] ingameGuestArray;
	private int ingameGuestCount;
	
	private int minPlayers;
	private Queue<Guest> waitingGuests = new LinkedList<>();
	private Tickable receiver;
	private GameBuildingPhase currentPhase;
	
	public GameBuilding(IGameTemplate template, int id, Cell cell, boolean instantBuild) {
		super(cell, id, template);
		ingameGuestArray = new Guest[template.getMaxPlayers()];
		if(instantBuild) {
			minPlayers = template.getMinPlayersDefault();
			currentPhase = GameBuildingPhase.WAITING;
			receiver = this::checkStart;
		}
		else {
			currentPhase = GameBuildingPhase.BUILDING;
			receiver = new TickCounter(this::switchToWaitPhase, template.getBuildTime());
		}
	}
	
	@Override
	public void receiveTick() {
		receiver.tick();
	}
	
	@Override
	public IGameTemplate getTemplate() {
		return (IGameTemplate) super.getTemplate();
	}
	
	private void switchToWaitPhase() {
		this.currentPhase = GameBuildingPhase.WAITING;
		this.receiver = this::checkStart;
	}
	
	public void setMinPlayers(int minplayers) {
		if(minplayers < 1) {
			throw new TranslatedException("error.gamestate.min-1");
		}
		this.minPlayers = minplayers;
		checkStart();
	}
	
	@Override
	public boolean joinGuest(Guest guest) {
		waitingGuests.add(guest);
		checkStart();
		return true;
	}
	
	private void checkStart() {
		if(currentPhase == GameBuildingPhase.WAITING &&
			waitingGuests.size() >= minPlayers &&
			getCell().getMap().getGamestate().getBalance().hasMoneyAtLeast(BigInteger.valueOf(getTemplate().getRoundCost()))) {
			int min = Math.min(waitingGuests.size(), getTemplate().getMaxPlayers());
			int i = 0;
			for (; i < min; i++) {
				ingameGuestArray[i] = waitingGuests.poll();
			}
			ingameGuestCount = i;
			currentPhase = GameBuildingPhase.PLAYING;
			receiver = new TickCounter(() -> {
				for(int j = 0; j < ingameGuestCount; ++j) {
					Guest c = ingameGuestArray[j];
					c.addFunLevel(getTemplate().getRoundMoralBoost());
					getCell().getMap().getGamestate().dropGuestAt(c, getRandomRoadConnection().getCell());
					ingameGuestArray[j] = null;
				}
				ingameGuestCount = 0;
				switchToWaitPhase();
			}, getTemplate().getPlaytime());
		}
	}
	
	public boolean readyToQueue() {
		return currentPhase == GameBuildingPhase.PLAYING || currentPhase == GameBuildingPhase.WAITING;
	}
	
}
