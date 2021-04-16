package hu.g14de.restapi.signals.in.select;

import com.google.gson.JsonElement;
import hu.g14de.gamestate.GameState;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalIn;
import hu.g14de.restapi.signals.out.select.SignalOutSelectDelete;

public class SignalInSelectDelete implements SignalIn {
    @Override
    public void execute(Connection c, JsonElement e) {

        int ID = e.getAsInt();

        GameState state = c.getUser().getList().getGamestate(ID);

        if(state == null) {
            c.crash("invalid gamestate ID");
            return;
        }

        c.getUser().getList().remove(ID);
        c.sendSignal(new SignalOutSelectDelete(ID));
    }
}
