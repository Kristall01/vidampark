package hu.g14de.gamestate;

import hu.g14de.Tickable;
import hu.g14de.Utils;
import hu.g14de.gamestate.mapelements.Joinable;
import hu.g14de.restapi.signals.out.game.guest.SignalOutGameGuestDespawn;
import hu.g14de.restapi.signals.out.game.guest.SignalOutGameGuestMove;
import hu.g14de.restapi.signals.out.game.guest.SignalOutGameGuestSpawn;

import java.util.LinkedList;
import java.util.Queue;

public class Guest {
	
	private int id, funLevel, foodLevel, money;
	private GameState parent;
	private Action currentAction;
	private Queue<Cell> path = null;
	private Cell currentCell;
	private Joinable wanderingTarget;
	
	private Action wandering, leaving, idle,
		busy = new Action(Tickable.none, GuestState.BUSY),
		done = new Action(Tickable.none, GuestState.DONE);
	
	//setup tasks
	private Guest() {
		wandering = new Action(() -> {
			Cell currentCell = path.poll();
			if(currentCell == null) {
				wanderingTarget.joinGuest(this);
				this.currentAction = busy;
				parent.broadcastSignal(new SignalOutGameGuestDespawn(this));
				return;
			}
			//check if road was removed
			if(!currentCell.hasContentType("road")) {
				currentAction = done;
				return;
			}
			this.currentCell = currentCell;
		}, GuestState.WANDERING);
		idle = new Action(() -> {
			boolean i = Utils.getRandom().nextBoolean();
			//TODO remove me, hardcoded game targeting
			final Joinable j = parent.getMap().getRandomGame();
			/*
			if(i) {
				j = parent.getMap().getRandomFood();
			}
			else{
				j = parent.getMap().getRandomGame();
			}
			*/
			if(j == null) {
				this.currentAction = leaving;
				LinkedList<Cell> cells = parent.getMap().findRoute(currentCell, parent.getMap().getEntrance());
				if(cells == null) {
					this.currentAction = done;
					return;
				}
				
				parent.broadcastSignal(new SignalOutGameGuestMove(this, cells));
				return;
			}
			
			/*LinkedList<Cell> cellWay = parent.getMap().findWay(
				currentCell,
				c -> c.hasContentType(j.getTemplate().type())
			);*/
			
			LinkedList<Cell> cellWay = parent.getMap().findWay(
				currentCell,
				c -> c.hasContent() && c.getContent().equals(j)
			);
			
			parent.broadcastSignal(new SignalOutGameGuestMove(this, cellWay));
			wanderingTarget = j;
			currentAction = wandering;
			path = cellWay;
		}, GuestState.IDLE);
	}
	
	public Coordinate getPosition() {
		return currentCell.getCoordinate();
	}
	
	public Guest(GameState parent, int id, Cell entrance) {
		this();
		this.id = id;
		this.parent = parent;
		this.currentAction = idle;
		this.currentCell = entrance;
	}
	
	public void addFunLevel(int roundMoralBoost) {
	
	}
	
	public void addFoodLevel(int foodLevel) {
	
	}
	
	public void tick() {
		currentAction.tickable.tick();
	}
	
	public void notifyGameover(Cell leaveCell) {
		if(currentAction.state == GuestState.BUSY) {
			currentAction = idle;
			currentCell = leaveCell;
			parent.broadcastSignal(new SignalOutGameGuestSpawn(this, leaveCell.getCoordinate()));
		}
	}
	
	public boolean remove() {
		return currentAction.state == GuestState.DONE;
	}
	
	public int getID() {
		return id;
	}
	
	private static class Action {
		
		public final Tickable tickable;
		public final GuestState state;
		
		public Action(Tickable tickable, GuestState state) {
			this.tickable = tickable;
			this.state = state;
		}
		
	}
	
	private static enum GuestState {
		WANDERING,
		LEAVING,
		IDLE,
		BUSY,
		DONE
	}
	
}
