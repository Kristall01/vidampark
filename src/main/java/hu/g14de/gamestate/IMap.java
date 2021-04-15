package hu.g14de.gamestate;

import hu.g14de.IBuildingTemplate;
import hu.g14de.Placeable;
import hu.g14de.TranslatedException;
import hu.g14de.VidamparkApp;
import hu.g14de.restapi.signals.out.game.SignalOutGameUpdatecell;

import java.util.LinkedList;

public class IMap {
	
	private final int width, height;
	private final GameState gamestate;
	private final Cell[] grid;
	private final LinkedList<Object> buildingProgress = new LinkedList<>();
	
	public IMap(GameState gamestate, int width, int height) {
		this.gamestate = gamestate;
		this.height = height;
		this.width = width;
		this.grid = new Cell[width*height];
		for(int i = 0; i < this.grid.length; ++i) {
			int x = i / width;
			int y = i % width;
			this.grid[i] = new Cell(this, new Coordinate(x,y), null);
		}
	}
	
	public VidamparkApp getApp() {
		return gamestate.getApp();
	}
	
	
	public int width() {
		return this.width;
	}
	
	public int height() {
		return this.height;
	}
	
	public GameState getGamestate() {
		return this.gamestate;
	}
	
	public Cell getCellAt(int x, int y) {
		if(x < 0 || y < 0 || x >= width() || y >= height())
			return null;
		return grid[width*y+x];
	}
	
	public void placeBuilding(final int x, final int y, final IBuildingTemplate template) throws OutOfMapCoordinateException {
		Cell center = getCellAt(x,y);
		if(center == null) {
			throw new OutOfMapCoordinateException(x,y);
		}
		if(center.hasContent()) {
			throw new CellAlreadyOccupiedException();
		}
		if(template.needsRoadConnection()) {
			boolean hasRoad = false;
			for(int iX = x-1; iX <= x+1; ++iX) {
				for(int iY = y-1; iY <= y+1; ++iY) {
					Cell c = getCellAt(iX, iY);
					if(c == null) {
						continue;
					}
					if(!c.hasContent()) {
						continue;
					}
					if(c.getContent().getTemplate().isRoad()) {
						hasRoad = true;
					}
				}
			}
			if(!hasRoad) {
				throw new BuildingNeedsRoadException();
			}
		}
		int buildtime = 0;
		Placeable p = template.createInstance();
		center.setContent(p);
		if(getGamestate().isStarted()) {
			buildtime = template.getBuildTime();
			buildingProgress.add(p);
		}
		getGamestate().broadcastSignal(new SignalOutGameUpdatecell(center));
	}
	
	public void receiveTick() {
	
	}
	
	public static class BuildingNeedsRoadException extends TranslatedException {
		
		public BuildingNeedsRoadException() {
			super("error.map.needs-road");
		}
	}
	
	public static class CellAlreadyOccupiedException extends TranslatedException {
		
		public CellAlreadyOccupiedException() {
			super("error.map.cell-already-occupied");
		}
		
	}
	
	public static class OutOfMapCoordinateException extends Exception {

		public final int x,y;
		
		public OutOfMapCoordinateException(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
}
