package hu.g14de.restapi.signals.out.select;

import com.google.gson.JsonObject;
import hu.g14de.gamestate.GameState;
import hu.g14de.usermanager.GamestateList;
import hu.g14de.restapi.signals.SignalOut;

import java.util.List;
import java.util.stream.Collectors;

public class SignalOutSelectList extends SignalOut {
	
	private List<JsonObject> elements;
	
	public SignalOutSelectList(GamestateList list) {
		super("list");
		elements = list.getGamestates().stream().map(GameState::getAsListEntry).collect(Collectors.toList());
	}
	
	@Override
	public Object serializedData() {
		return elements;
	}
	
}
