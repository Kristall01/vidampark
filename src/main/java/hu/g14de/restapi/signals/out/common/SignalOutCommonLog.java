package hu.g14de.restapi.signals.out.common;

import hu.g14de.restapi.signals.SignalOut;

public class SignalOutCommonLog extends SignalOut
{
	
	private String msg;
	
	public SignalOutCommonLog(String text)
	{
		super("log");
		msg = text;
	}
	
	@Override
	public Object serializedData() {
		return msg;
	}
}
