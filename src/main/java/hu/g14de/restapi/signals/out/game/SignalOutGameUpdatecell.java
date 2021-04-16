package hu.g14de.restapi.signals.out.game;

import com.google.gson.JsonObject;
import hu.g14de.IBuildingTemplate;
import hu.g14de.gamestate.Cell;
import hu.g14de.restapi.signals.SignalOut;

public class SignalOutGameUpdatecell extends SignalOut {
	
	private int x,y, buildtime;
	private String type;
	private Cell cell;
	
	public SignalOutGameUpdatecell(Cell cell) {
		super("updatecell");
		
		this.cell = cell;
		
		this.x = cell.getCoordinate().getX();
		this.y = cell.getCoordinate().getY();
		this.type = cell.getContent().getTemplate().type();
		this.buildtime = cell.getContent().getTemplate().getBuildTime();
	}
	
	@Override
	public Object serializedData() {
		JsonObject o = new JsonObject();
		o.addProperty("x", x);
		o.addProperty("y", y);
		IBuildingTemplate template = cell.getContent().getTemplate();
		if(type == null) {
			o.add("type", null);
			o.addProperty("height", template.height());
			o.addProperty("width", template.width());
		}
		else if(buildtime != 0) {
			o.addProperty("buildtime", buildtime);
		}
		return o;
	}
}
