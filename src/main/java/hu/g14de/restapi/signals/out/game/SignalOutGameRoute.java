package hu.g14de.restapi.signals.out.game;

import com.google.gson.JsonElement;
import hu.g14de.gamestate.Coordinate;
import hu.g14de.restapi.signals.SignalOut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SignalOutGameRoute extends SignalOut {
	
	private List<JsonElement> coords;
	
	public SignalOutGameRoute(Collection<Coordinate> coords) {
		super("route");
		
		if(coords != null) {
			ArrayList<JsonElement> e = new ArrayList<>();
			for (Coordinate coord : coords) {
				e.add(coord.serialize());
			}
			this.coords = e;
		}
	}
	
	
}
