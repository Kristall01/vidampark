import hu.g14de.VidamparkApp;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		VidamparkApp app = new VidamparkApp();
		app.getUserManager().createUser("asd@asd.com", "asd@asd.com");
	}
	
}
