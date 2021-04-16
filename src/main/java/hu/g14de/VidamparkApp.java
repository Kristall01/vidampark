package hu.g14de;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hu.g14de.gamestate.IBuildingCatalog;
import hu.g14de.i18n.Lang;
import hu.g14de.restapi.ConnectionServer;
import hu.g14de.usermanager.UserManager;

import java.io.File;
import java.io.IOException;

public class VidamparkApp {
	
	private Lang lang;
	private UserManager userManager;
	private ConnectionServer connectionServer;
	private JsonObject config;
	private IBuildingCatalog catalog;
	
	public VidamparkApp() throws IOException {
		EnvironmentBootstrapper bootstrapper = new EnvironmentBootstrapper(new File(System.getProperty("user.dir")));
		
		bootstrapper.setupFrontend();
		
		config = (JsonObject) Utils.fromJson(bootstrapper.copyFile("config.json"));
		lang = Lang.readLangFile(bootstrapper.copyFile("lang.cfg"));
		
		userManager = new UserManager(this);
		
		catalog = readCatalog(config.get("buildings"));
		connectionServer = new ConnectionServer(this, bootstrapper.getPort(8080));
	}
	
	public ConnectionServer getConnectionServer() {
		return connectionServer;
	}
	
	private IBuildingCatalog readCatalog(JsonElement e) {
		SimpleBuildingCatalog catalog = new SimpleBuildingCatalog();
		JsonArray templates = e.getAsJsonArray();
		for(int i = 0; i < templates.size(); ++i) {
			JsonObject o = (JsonObject) templates.get(i);
			String name = o.get("name").getAsString();
			int cost = o.get("buildCost").getAsInt();
			int time = o.get("buildTime").getAsInt();
			int width = o.get("width").getAsInt();
			int height = o.get("height").getAsInt();
			boolean isRoad = name.equals("road");
			SimpleBuildingTemplate template = new SimpleBuildingTemplate(!isRoad, name, cost, time, width, height, isRoad);
			catalog.register(name, template);
		}
		return catalog;
	}
	
	public Lang getLang() {
		return lang;
	}
	
	public IBuildingCatalog getCatalog() {
		return catalog;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}
	
	public JsonObject getConfig() {
		return config;
	}

}
