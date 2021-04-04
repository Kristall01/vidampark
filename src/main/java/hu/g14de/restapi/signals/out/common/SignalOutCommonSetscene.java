package hu.g14de.restapi.signals.out.common;

import hu.g14de.restapi.signals.SignalOut;

public class SignalOutCommonSetscene extends SignalOut {
	
	private String scene;
	
	public SignalOutCommonSetscene(String scene) {
		super("setscene");
		this.scene = scene;
	}
}
