package hu.g14de.gamestate;

import hu.g14de.TranslatedException;
import hu.g14de.Utils;
import hu.g14de.VidamparkApp;
import hu.g14de.gamestate.mapelements.IBuildingTemplate;
import hu.g14de.gamestate.mapelements.Placeable;
import hu.g14de.gamestate.mapelements.basics.Road;
import hu.g14de.gamestate.mapelements.food.FoodBuilding;
import hu.g14de.gamestate.mapelements.game.GameBuilding;
import hu.g14de.restapi.signals.out.game.SignalOutGameUpdatecell;

import java.util.*;
import java.util.function.Predicate;

public class IMap {
	
	private final int width, height;
	private final GameState gamestate;
	private final Cell[] grid;
	private final ArrayList<Placeable> buildings = new ArrayList<>();
	private final List<GameBuilding> gameBuildings = new ArrayList<>();
	private final List<FoodBuilding> foodBuildings = new ArrayList<>();
	private Cell entrance;
	
	public IMap(GameState gamestate, int width, int height) {
		this.gamestate = gamestate;
		this.height = height;
		this.width = width;
		this.grid = new Cell[width*height];
		for(int i = 0; i < this.grid.length; ++i) {
			int x = i % width;
			int y = i / width;
			this.grid[i] = new Cell(this, new Coordinate(x,y), null);
		}
		this.entrance = getCellAt(width/2, 0);
	}
	
	public Cell getEntrance() {
		return entrance;
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
	
	public GameBuilding getRandomGame()
	{
		return gameBuildings.size() == 0 ? null : gameBuildings.get(Utils.getRandom().nextInt(gameBuildings.size()));
	}
	
	public FoodBuilding getRandomFood() {
		return foodBuildings.size() == 0 ? null : foodBuildings.get(Utils.getRandom().nextInt(foodBuildings.size()));
	}
	
	public GameState getGamestate() {
		return this.gamestate;
	}
	
	public Cell getCellAt(int x, int y) {
		if(x < 0 || y < 0 || x >= this.width() || y >= this.height())
			return null;
		return grid[width*y+x];
	}
	
	private boolean isRoad(int x, int y) {
		Cell c = getCellAt(x,y);
		return isRoad(c);
	}
	
	private boolean isRoad(Cell c) {
		return c != null && c.hasContent() && c.getContent().getTemplate().isRoad();
	}
	
	private boolean hasRoadConnection(int x, int y, int width, int height) {
		return getRoadConnection(x, y, width, height) != null;
	}
	
	public Collection<Cell> findRoute(Cell from, Cell to) {
		return null;
	}
	
	public Cell getRoadConnection(int x, int y, int width, int height) {
		int iX, iY;
		iX = x;
		iY = y-1;
		for(;iX < x+width; ++iX) {
			if(isRoad(iX, iY)) {
				return getCellAt(iX, iY);
			}
		}
		++iY;
		for(; iY < y+height; ++iY) {
			if(isRoad(iX, iY)) {
				return getCellAt(iX, iY);
			}
		}
		iX = x-1;
		iY = y;
		for(; iY < y+height; ++iY) {
			if(isRoad(iX, iY)) {
				return getCellAt(iX, iY);
			}
		}
		++iX;
		for(;iX < x+width; ++iX) {
			if(isRoad(iX, iY)) {
				return getCellAt(iX, iY);
			}
		}
		return null;
	}
	
	public void placeBuilding(final int x, final int y, final IBuildingTemplate template) throws OutOfMapCoordinateException, CellAlreadyOccupiedException {
		//check if region is taken or coordinate out of map
		for(int xIndex = x; xIndex < x+template.width(); ++xIndex) {
			for (int yIndex = y; yIndex < y+template.height(); ++yIndex) {
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
		
		//set cells' content
		Placeable p = template.createInstance(tlCell, !getGamestate().isStarted());
		for(int iX = x; iX < x+template.width(); ++iX) {
			for (int iY = y; iY < y+template.height(); ++iY) {
				getCellAt(iX, iY).setContent(p);
			}
		}
		long buildTime = 0;
		if(getGamestate().isStarted()) {
			buildTime = template.getBuildTime()*getGamestate().getScheduler().getTickGap();
		}
		buildings.add(p);
		getGamestate().broadcastSignal(new SignalOutGameUpdatecell(tlCell, buildTime));
	}
	
	public Collection<Coordinate> findWay(int x, int y, Predicate<Cell> matcher) {
			return findWay(getCellAt(x,y), matcher );
		}
		
	public Collection<Coordinate> findWay(Cell from, Predicate<Cell> matcher) {
		if(!isRoad(from)) {
			return null;
		}
		
		Road fromCell = (Road)from.getContent();
		Queue<Cell> q = new LinkedList<Cell>();
		q.add(from);
		for (Placeable building : buildings) {
			if(building instanceof Road) {
				Road r = (Road)building;
				r.visitedFrom = null;
			}
		}
		fromCell.visitedFrom = from;
		Cell[] neightbors = new Cell[4];
		while(q.size() != 0) {
			Cell current = q.poll();
			
			Coordinate currentCoord = current.getCoordinate();
			neightbors[0] = getCellAt(currentCoord.getX()+1, currentCoord.getY());
			neightbors[1] = getCellAt(currentCoord.getX()-1, currentCoord.getY());
			neightbors[2] = getCellAt(currentCoord.getX(), currentCoord.getY()+1);
			neightbors[3] = getCellAt(currentCoord.getX(), currentCoord.getY()-1);
			for (int i = 0; i < 4; i++) {
				Cell loopCurrent = neightbors[i];
				
				if(isRoad(loopCurrent)) {
					Road currentRoad = ((Road)loopCurrent.getContent());
					if(currentRoad.visitedFrom == null) {
						currentRoad.visitedFrom = current;
						q.add(loopCurrent);
					}
				}
				if(matcher.test(loopCurrent)) {
					LinkedList<Coordinate> rollback = new LinkedList<>();
					rollback.addFirst(loopCurrent.getCoordinate());
					Road rollbackCurrent = (Road)current.getContent();//((Road)loopCurrent.getContent()).visitedFrom.getContent();
					while(!rollbackCurrent.getCell().getCoordinate().equals(((Road)rollbackCurrent).visitedFrom.getContent().getCell().getCoordinate())) {
						rollback.addFirst(rollbackCurrent.getCell().getCoordinate());
						rollbackCurrent = (Road)rollbackCurrent.visitedFrom.getContent();
					}
					return rollback;
				}
			}
		}
		return null;
	}
	
	public Collection<Placeable> getBuildings() {
		return List.copyOf(buildings);
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
	
	public static class OutOfMapCoordinateException extends TranslatedException {

		public final int x,y;
		
		public OutOfMapCoordinateException(int x, int y) {
			super("error.map.out-of-map-coordinate");
			this.x = x;
			this.y = y;
		}
		
	}
	
}
