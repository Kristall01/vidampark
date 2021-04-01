package hu.g14de.restapi.signals;

import com.google.gson.JsonElement;
import hu.g14de.restapi.Connection;

public interface SignalIn
{

	void execute(Connection c, JsonElement e);

}
