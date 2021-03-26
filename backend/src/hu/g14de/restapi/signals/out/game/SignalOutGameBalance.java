package hu.g14de.restapi.signals.out.game;

import hu.g14de.restapi.signals.SignalOut;

import java.math.BigInteger;

public class SignalOutGameBalance extends SignalOut {
	
	private String balance;
	
	public SignalOutGameBalance(BigInteger balance) {
		super("balance");
		this.balance = balance.toString();
	}
	
}
