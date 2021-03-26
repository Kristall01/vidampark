package hu.g14de.restapi.signals.out.game;

import hu.g14de.restapi.signals.SignalOut;

import java.math.BigInteger;

public class SignalOutGameMapsize extends SignalOut {
	
	private int width, height;
	
	public SignalOutGameMapsize(int width, int height) {
		super("mapsoze");
		this.width = width;
		this.height = height;
	}
	
}
