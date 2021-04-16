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
	
	private boolean isRoad(int x, int y) {
		Cell c = getCellAt(x,y);
		return c != null && c.hasContent() && c.getContent().getTemplate().isRoad();
	}
	
	private boolean hasRoadConnection(int x, int y, int width, int height) {
		if(isRoad(x-1, y-1)) {
			return true;
		}
		int iX, iY;
		iX = x;
		iY = y-1;
		for(;iX < x+width; ++iX) {
			if(isRoad(iX, iY)) {
				return true;
			}
		}
		for(; iY < y+height; ++iY) {
			if(isRoad(iX, iY)) {
				return true;
			}
		}
		iX = x-1;
		iY = y;
		for(; iY < y+height; ++iY) {
			if(isRoad(iX, iY)) {
				return true;
			}
		}
		for(;iX < x+width; ++iX) {
			if(isRoad(iX, iY)) {
				return true;
			}
		}
		if(isRoad(x+width, y+height)) {
			return true;
		}
		return false;
	}
	
	public void placeBuilding(final int x, final int y, final IBuildingTemplate template) throws OutOfMapCoordinateException {
		for(int xIndex = x; xIndex < x+template.width(); ++xIndex) {
			for (int yIndex = 0; yIndex < y+ template.height(); yIndex++) {
				Cell currentCell = getCellAt(xIndex, yIndex);
				if(currentCell == null) {
					throw new OutOfMapCoordinateException(xIndex,yIndex);
				}
				if(currentCell.hasContent()) {
					throw new CellAlreadyOccupiedException();
				}
			}
		}
		
		Cell tlCell = getCellAt(x,y);
		
		if(template.needsRoadConnection() && !hasRoadConnection(x, y, template.width(), template.height())) {
			throw new BuildingNeedsRoadException();
		}
		int buildtime = 0;
		Placeable p = template.createInstance();
		for(int iX = x; iX < x+ template.width(); ++iX) {
			for (int iY = y; iY < y+ template.height(); ++iY) {
				getCellAt(iX, iY).setContent(p);
			}
		}
		/*if(getGamestate().isStarted()) {
			buildtime = template.getBuildTime();
			buildingProgress.add(p);
		}*/
		getGamestate().broadcastSignal(new SignalOutGameUpdatecell(tlCell));
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
