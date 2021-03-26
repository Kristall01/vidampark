package hu.g14de.restapi.signals;

import java.util.HashMap;
import java.util.Map;

public class SignalDomain
{
	
	private final Map<String, SignalIn> signalMap = new HashMap<>();
	private String name;
	
	public SignalDomain(String name) {
		this.name = name;
	}
	
	public void add(String name, SignalIn c)
	{
		this.signalMap.put(name, c);
	}
	
	public SignalIn getSignal(String type)
	{
		return this.signalMap.get(type);
	}
	
	public String getName() {
		return name;
	}
}
