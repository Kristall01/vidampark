package hu.g14de.gamestate;

import hu.g14de.Utils;
import hu.g14de.gamestate.guestactions.IGuestState;
import hu.g14de.gamestate.guestactions.Idle;
import hu.g14de.gamestate.guestactions.Leaving;
import hu.g14de.gamestate.guestactions.Wandering;
import hu.g14de.gamestate.mapelements.IBuildingTemplate;
import hu.g14de.gamestate.mapelements.Joinable;
import hu.g14de.restapi.signals.out.game.guest.SignalOutGameGuestDespawn;
import hu.g14de.restapi.signals.out.game.guest.SignalOutGameGuestMoveto;
import hu.g14de.restapi.signals.out.game.guest.SignalOutGameGuestSpawn;

import java.util.Queue;

public class Guest {
	
	private int id, funLevel, foodLevel, money;
	private GameState parent;
	private IGuestState currentState;
	private Cell currentCell;
	private boolean visible = false;
	private String color;
	private IBuildingTemplate lastBuildingEntered;
	
	public Guest(GameState parent, int id, Cell entrance) {
		this.id = id;
		this.parent = parent;
		this.currentState = new Idle(this);
		this.currentCell = entrance;
		this.color = '#'+Integer.toHexString(Utils.getRandom().nextInt(16777216)); //256 ^ 3
		
		setVisible(true);
	}
	
	public void setVisible(boolean newState) {
		if(visible == newState) {
			return;
		}
		this.visible = newState;
		if(newState) {
			parent.broadcastSignal(new SignalOutGameGuestSpawn(this));
		}
		else {
			parent.broadcastSignal(new SignalOutGameGuestDespawn(this));
		}
	}
	
	public String getColor() {
		return color;
	}
	
	public void setState(IGuestState state) {
		if(currentState != null) {
			currentState.unmounted();
		}
		this.currentState = state;
		state.mounted();
	}
	
	public GameState getParent() {
		return parent;
	}
	
	public void setCell(Cell cell) {
		this.currentCell = cell;
		if(visible) {
			parent.broadcastSignal(new SignalOutGameGuestMoveto(this));
		}
	}
	
	public void goTo(Joinable target) {
		Queue<Cell> cellWay = parent.getMap().findWay(currentCell, c -> c.hasContent() && c.getContent().equals(target), false);
		setState(new Wandering(this, target, cellWay));
	}
	
	public void leavePark() {
		IMap map = parent.getMap();
		Queue<Cell> cells = map.findRoute(currentCell, map.getEntrance(), true);
		setState(new Leaving(this, cells));
	}
	
	public Coordinate getPosition() {
		return currentCell.getCoordinate();
	}
	
	public void tick() {
		currentState.tick();
	}
	
	public void notifyGameover(Cell leaveCell) {
		setCell(leaveCell);
		setState(new Idle(this));
	}
	
	public boolean readyToRemove() {
		return currentState.readyToRemove();
	}
	
	public int getID() {
		return id;
	}
	
	public void addFunLevel(int roundMoralBoost) {
	}
	
	public void setLastJoined(IBuildingTemplate temp) {
		lastBuildingEntered = temp;
	}
	
	public IBuildingTemplate getLastBuildingEntered() {
		return lastBuildingEntered;
	}
	
	public boolean isVisible() {
		return visible;
	}
}
