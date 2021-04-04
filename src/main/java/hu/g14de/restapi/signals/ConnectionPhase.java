package hu.g14de.restapi.signals;

public enum ConnectionPhase {
	
	LOGIN("login"),
	SELECT("select"),
	PLAY("play");
	
	private String name;
	
	ConnectionPhase(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
