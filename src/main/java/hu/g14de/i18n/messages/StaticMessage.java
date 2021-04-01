package hu.g14de.i18n.messages;

import hu.g14de.Utils;

public class StaticMessage implements Message
{
	
	private final String value;
	
	public StaticMessage(String value)
	{
		Utils.checkNull(value);
		
		this.value = value;
	}
	
	@Override
	public String original()
	{
		return this.value;
	}
	
	@Override
	public String getValue(StringBuilder b, Object... params)
	{
		Utils.checkNull(b, params);
		
		return this.value;
	}
	
}
