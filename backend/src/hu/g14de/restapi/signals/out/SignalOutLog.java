package hu.g14de.restapi.signals.out;

import hu.g14de.restapi.signals.SignalOut;

public class SignalOutLog extends SignalOut
{
	
	private String content;
	
	public SignalOutLog(String text)
	{
		super("log");
		content = text;
	}
	
}
