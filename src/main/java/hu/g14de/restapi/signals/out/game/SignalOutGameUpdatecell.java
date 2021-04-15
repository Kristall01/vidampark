package hu.g14de.restapi.signals.out.game;

import com.google.gson.JsonObject;
import hu.g14de.gamestate.Cell;
import hu.g14de.restapi.signals.SignalOut;

public class SignalOutGameUpdatecell extends SignalOut {
	
	private int x,y, buildtime;
	private String type;
	
	public SignalOutGameUpdatecell(Cell cell) {
		super("updatecell");
		
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
		if(type == null) {
			o.add("type", null);
		}
		else if(buildtime != 0) {
			o.addProperty("buildtime", buildtime);
		}
		return o;
	}
}
