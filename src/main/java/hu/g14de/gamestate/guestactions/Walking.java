package hu.g14de.gamestate.guestactions;

import hu.g14de.gamestate.Cell;
import hu.g14de.gamestate.Guest;

import java.util.Queue;

public abstract class Walking extends GuestState {
	
	private Queue<Cell> path;
	
	public Walking(Guest g, Queue<Cell> path) {
		super(g);
		this.path = path;
	}
	
	@Override
	public void tick() {
		if(path == null) {
			unreachable();
			return;
		}
		Cell currentCell = path.poll();
		if(currentCell != null) {
			if(!currentCell.hasContent() || !currentCell.getContent().getTemplate().isRoad()) {
				unreachable();
				return;
			}
			getGuest().setCell(currentCell);
			return;
		}
		reachedTarget();
	}
	
	protected abstract void unreachable();
	
	protected abstract void reachedTarget();
	
}
