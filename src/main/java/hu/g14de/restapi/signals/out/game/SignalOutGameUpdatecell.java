package hu.g14de.restapi.signals.out.game;

import com.google.gson.JsonObject;
import hu.g14de.IBuildingTemplate;
import hu.g14de.gamestate.Cell;
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
			IBuildingTemplate template = cell.getContent().getTemplate();
			o.addProperty("type", template.type());
			o.addProperty("height", template.height());
			o.addProperty("width", template.width());
			o.addProperty("mapIcon", template.mapIcon());
		}
		/*else if(buildtime != 0) {
			o.addProperty("buildtime", buildtime);
		}*/
		return o;
	}
}
