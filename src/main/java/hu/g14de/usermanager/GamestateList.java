package hu.g14de.usermanager;

import com.google.gson.JsonObject;
import hu.g14de.SimpleBuildingCatalog;
import hu.g14de.TranslatedException;
import hu.g14de.Utils;
import hu.g14de.VidamparkApp;
import hu.g14de.gamestate.GameState;
import hu.g14de.gamestate.IBuildingCatalog;

import java.util.HashMap;
import java.util.List;

public class GamestateList {
	
	private User user;
	private HashMap<Integer, GameState> map;
	private int nextID = 0;
	
	public GamestateList(User user) {
		Utils.checkNull(user);
		
		this.user = user;
		this.map = new HashMap<>();
	}
	
	public VidamparkApp getApp() {
		return user.getApp();
	}
	
	public User getUser() {
		return user;
	}

	public GameState createGamestate(String name, int height, int width) {

		if (name.trim().length() == 0)
			throw new TranslatedException("invalid-name",name);
		if (name.length() < 3 || name.length() > 16)
			throw new TranslatedException("invalid-name-length");
		if (height < 5 || height > 200)
			throw new TranslatedException("invalid-map-size");
		if (width < 5 || width > 200)
			throw new TranslatedException("invalid-map-size");

		int id = ++nextID;
		IBuildingCatalog fakeCatalog = getApp().getCatalog();

		GameState s = new GameState(this.user, name, id, width, height, new SimpleBuildingCatalog(fakeCatalog));
		map.put(id, s);
		return s;
	}
	
	public List<GameState> getGamestates() {
		return List.copyOf(map.values());
	}
	
	public GameState getGamestate(int ID) {
		return map.get(ID);
	}

    public void remove(int id) {
		Utils.checkNull(map.get(id));
		GameState state = this.map.remove(id);
		state.destructor();
    }

    public static class GamestateNotFoundException extends Exception {}
	
}
