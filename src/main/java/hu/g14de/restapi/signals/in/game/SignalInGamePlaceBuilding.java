package hu.g14de.restapi.signals.in.game;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hu.g14de.gamestate.mapelements.IBuildingTemplate;
import hu.g14de.TranslatedException;
import hu.g14de.gamestate.Balance;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.common.SignalOutCommonLog;

import java.math.BigInteger;

public class SignalInGamePlaceBuilding implements SignalIn {
	
	@Override
	public void execute(Connection c, JsonElement e) {
		JsonObject o = e.getAsJsonObject();
		int x = o.get("x").getAsInt();
		int y = o.get("y").getAsInt();
		String type = o.get("type").getAsString();
		IBuildingTemplate temp = c.getServer().getApp().getCatalog().getTemplateByID(type);
		try {
			if(!c.getObservedGamestate().getBalance().hasMoneyAtLeast(BigInteger.valueOf(temp.getBuildCost()))) {
				throw new Balance.NegativeMoneyException();
			}
			c.getObservedGamestate().getMap().placeBuilding(x,y, temp);
			c.getObservedGamestate().getBalance().removeMoney(BigInteger.valueOf(temp.getBuildCost()));
		}
		catch(Balance.NegativeMoneyException ex) {
			c.sendSignal(new SignalOutCommonLog(c.getApp().getLang().translate("error.balance.too-few-money")));
		}
		catch (TranslatedException ex) {
			c.sendSignal(new SignalOutCommonLog(ex.translate(c.getApp().getLang())));
		}
	}
	
}
