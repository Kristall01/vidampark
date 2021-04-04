package hu.g14de.restapi.signals;

public class SignalOut
{
	
	private final transient String type;
	
	public SignalOut(String type)
	{
		this.type = type;
	}
	
	public String type()
	{
		return this.type;
	}
	
	public Object serializedData() {
		return this;
	}
	
}
