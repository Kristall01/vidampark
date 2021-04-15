package hu.g14de;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.CodeSource;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class EnvironmentBootstrapper {
	
	private File copyBase;
	
	public EnvironmentBootstrapper(File copyBase) {
		this.copyBase = copyBase;
	}
	
	public void setupFrontend() throws IOException {
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
						new File(copyBase, e.getName()).mkdirs();
					}
					else {
						copyFile(copyBase, name);
					}
					//Do something with this entry.
				}
			}
		}
	}
	
	public File copyFile(String fileName) throws IOException {
		return copyFile(copyBase, fileName);
	}
	
	public File copyFile(File baseDir, String fileName) throws IOException {
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
		return target;
	}
	
	public int getPort(int defaultPort) {
		String portText = System.getenv("PORT");
		if(portText != null) {
			try {
				return Integer.parseInt(portText);
			}
			catch (NumberFormatException ex) {
			}
		}
		return defaultPort;
	}
	
}