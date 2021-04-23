package hu.g14de.restapi.signals.out.game;

import hu.g14de.gamestate.mapelements.IBuildingTemplate;
import hu.g14de.restapi.signals.SignalOut;

import java.util.ArrayList;
import java.util.Collection;

public class SignalOutGameCatalog extends SignalOut {
	
	private Object templates;
	
	public SignalOutGameCatalog(Collection<IBuildingTemplate> templates) {
		super("catalog");
		ArrayList<Object> l = new ArrayList<>();
		templates.forEach(e -> l.add(e.serialize()));
		this.templates = l;
	}
	
	@Override
	public Object serializedData() {
		return templates;
	}
}
