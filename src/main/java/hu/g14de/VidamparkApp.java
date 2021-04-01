package hu.g14de;

import hu.g14de.i18n.Lang;
import hu.g14de.restapi.ConnectionServer;
import hu.g14de.usermanager.UserManager;

import java.io.*;
import java.net.URL;
import java.security.CodeSource;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class VidamparkApp {
	
	//private RestApiServer restApiManager;
	private Lang lang;
	private UserManager userManager;
	private ConnectionServer connectionServer;
	
	public VidamparkApp() throws IOException {
		File langFile = new File("lang.cfg");
		File baseDir = new File(System.getProperty("user.dir"));
		copyFile(baseDir, "lang.cfg");
		String langPath = "lang.cfg";
		
		File frontend = new File("frontend");
		if(!frontend.isDirectory()) {
			frontend.mkdir();
			CodeSource src = getClass().getProtectionDomain().getCodeSource();
			if (src != null) {
				URL jar = src.getLocation();
				ZipInputStream zip = new ZipInputStream(jar.openStream());
				while(true) {
					ZipEntry e = zip.getNextEntry();
					if (e == null)
						break;
					String name = e.getName();
					if (name.startsWith("frontend/")) {
						if(e.isDirectory()) {
							new File(baseDir, e.getName()).mkdirs();
						}
						else {
							copyFile(baseDir, name);
						}
						//Do something with this entry.
					}
				}
			}
			else {
				//Fail...
			}
			
		}
		
		lang = Lang.readLangFile(new File(langPath));
		userManager = new UserManager(this);
		String portText = System.getenv("PORT");
		int port = 8080;
		if(portText != null) {
			try {
				port = Integer.parseInt(portText);
				System.out.println("using alternative port "+portText);
			}
			catch (NumberFormatException ex) {
				System.out.println("invalid port number '"+portText+"'");
			}
		}
		connectionServer = new ConnectionServer(this, port);
		//restApiManager = new RestApiServer(this, 8080);
	}
	
	private void copyFile(File baseDir, String fileName) throws IOException {
		File target = new File(baseDir, fileName);
		if(!target.isFile()) {
			InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
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
