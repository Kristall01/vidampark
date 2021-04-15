package hu.g14de.usermanager;

import hu.g14de.SimpleBuildingCatalog;
import hu.g14de.Utils;
import hu.g14de.VidamparkApp;
import hu.g14de.gamestate.GameState;

import java.util.HashMap;
import java.util.List;

public class GamestateList {
	
	private User user;
	private HashMap<Integer, GameState> map;
	private int nextID = 0;
	
	public GamestateList(User user) {
		Utils.checkNull(user);
		
		this.user = user;
		this.map = new HashMap<Integer, GameState>();
	}
	
	public VidamparkApp getApp() {
		return user.getApp();
	}
	
	public User getUser() {
		return user;
	}
	
	public GameState createGamestate() {
		int id = ++nextID;
		SimpleBuildingCatalog fakeCatalog = new SimpleBuildingCatalog();
		GameState s = new GameState(this.user, "save #"+id, id, fakeCatalog);
		map.put(id, s);
		return s;
	}
	
	public List<GameState> getGamestates() {
		return List.copyOf(map.values());
	}
	
	public GameState getGamestate(int ID) {
		return map.get(ID);
	}
	
	public static class GamestateNotFoundException extends Exception {}
	
}
