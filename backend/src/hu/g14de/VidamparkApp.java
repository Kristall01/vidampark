package hu.g14de;

import hu.g14de.i18n.Lang;
import hu.g14de.restapi.ConnectionServer;
import hu.g14de.usermanager.UserManager;

import java.io.*;

public class VidamparkApp {
	
	//private RestApiServer restApiManager;
	private Lang lang;
	private UserManager userManager;
	private ConnectionServer connectionServer;
	
	public VidamparkApp() throws IOException {
		File langFile = new File("lang.cfg");
		copyFile("lang.cfg");
		String langPath = System.getenv(("LANG_PATH"));
		if(langPath == null) {
			langPath = "lang.cfg";
		}
		lang = Lang.readLangFile(new File(langPath));
		userManager = new UserManager(this);
		connectionServer = new ConnectionServer(this, 8080);
		//restApiManager = new RestApiServer(this, 8080);
	}
	
	private void copyFile(String fileName) throws IOException {
		File target = new File(fileName);
		if(!target.isFile()) {
			InputStream in = getClass().getClassLoader().getResourceAsStream("lang.cfg");
			if(in != null) {
				FileOutputStream out = new FileOutputStream(target);
				in.transferTo(out);
				in.close();
				out.close();
			}
		}
	}
	
	public ConnectionServer getConnectionServer() {
		return connectionServer;
	}
	
	public Lang getLang() {
		return lang;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}
	
}
