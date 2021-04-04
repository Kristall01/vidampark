package hu.g14de.restapi.signals.out.common;

import hu.g14de.restapi.signals.SignalOut;

public class SignalCommonOutLog extends SignalOut
{
	
	private String content;
	
	public SignalCommonOutLog(String text)
	{
		super("log");
		content = text;
	}
	
}
