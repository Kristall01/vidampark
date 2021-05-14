package hu.g14de.restapi.signals.out.game;

import com.google.gson.JsonObject;
import hu.g14de.gamestate.Cell;
import hu.g14de.gamestate.mapelements.IBuildingTemplate;
import hu.g14de.gamestate.mapelements.Placeable;
import hu.g14de.restapi.signals.SignalOut;

public class SignalOutGameUpdatecell extends SignalOut {
	
	private Cell cell;
	
	public SignalOutGameUpdatecell(Cell cell) {
		super("updatecell");
		
		this.cell = cell;
	}
	
	@Override
	public Object serializedData() {
		JsonObject o = new JsonObject();
		o.addProperty("x", cell.getCoordinate().getX());
		o.addProperty("y", cell.getCoordinate().getY());
		if(cell.hasContent()) {
			Placeable p = cell.getContent();
			IBuildingTemplate template = cell.getContent().getTemplate();
			o.addProperty("complex", template.isComplex());
			o.addProperty("id", p.getID());
			o.addProperty("type", template.type());
			o.addProperty("height", template.height());
			o.addProperty("width", template.width());
			o.addProperty("mapIcon", template.mapIcon());
			o.addProperty("nickName", template.getNickname());
		}
		return o;
	}
}
