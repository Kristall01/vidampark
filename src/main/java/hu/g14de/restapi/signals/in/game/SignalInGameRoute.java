package hu.g14de.restapi.signals.in.game;

import com.google.gson.JsonElement;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;

public class SignalInGameRoute implements SignalIn {
	
	@Override
	public void execute(Connection c, JsonElement e) {
		/*JsonObject o = e.getAsJsonObject();
		JsonObject from = o.get("from").getAsJsonObject();
		if(o.has("to")) {
			JsonObject to = o.get("to").getAsJsonObject();
			Predicate<Cell> p = cell -> {
				if(cell == null) {
					return false;
				}
				Coordinate coordinate = cell.getCoordinate();
				return coordinate != null && coordinate.getX() == to.get("x").getAsInt() && coordinate.getY() == to.get("y").getAsInt();
			};
			Collection<Coordinate> cells = c.getObservedGamestate().getMap().findWay(from.get("x").getAsInt(), from.get("y").getAsInt(), p);
			c.sendSignal(new SignalOutGameRoute(cells));
			return;
		}
		if(o.has("type")) {
			Predicate<Cell> p = cell -> {
				if(cell == null) {
					return false;
				}
				return cell.hasContent() && cell.getContent().getTemplate().type().equals(o.get("type").getAsString());
			};
			Collection<Coordinate> cells = c.getObservedGamestate().getMap().findWay(from.get("x").getAsInt(), from.get("y").getAsInt(), p);
			c.sendSignal(new SignalOutGameRoute(cells));
			return;
		}
		c.sendSignal(new SignalOutGameRoute(null));*/
	}
}
