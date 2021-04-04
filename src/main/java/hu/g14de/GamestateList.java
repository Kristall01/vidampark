package hu.g14de;

import hu.g14de.usermanager.User;

import java.util.HashMap;
import java.util.List;

public class GamestateList {
	
	private User user;
	private HashMap<Integer, GameState> map;
	private GameState activeGamestate = null;
	private int nextID = 0;
	
	public GamestateList(User user) {
		Utils.checkNull(user);
		
		this.user = user;
		this.map = new HashMap<Integer, GameState>();
	}
	
	public User getUser() {
		return user;
	}
	
	public GameState createGamestate() {
		int id = ++nextID;
		GameState s = new GameState(this.user, "unnamed", id);
		map.put(id, s);
		return s;
	}
	
	public List<GameState> getGamestates() {
		return List.copyOf(map.values());
	}
	
	public GameState getGamestate(int ID) {
		return map.get(ID);
	}
	
	public void activateState(int ID) {
	
	}
	
	public void deactivateState() {
	
	}
	
}
