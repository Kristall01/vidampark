package hu.g14de.i18n.messages;

import hu.g14de.Utils;
import hu.g14de.i18n.components.Component;

public class DynamicMessage implements Message
{
	
	private final Component[] components;
	private final String original;
	
	public DynamicMessage(Component[] components, String original)
	{
		Utils.checkNull(components, original);
		
		this.components = components;
		this.original = original;
	}
	
	@Override
	public String original()
	{
		return this.original;
	}
	
	@Override
	public String getValue(StringBuilder b, Object[] params)
	{
		Utils.checkNull(b, params);
		
		for (Component component : components) {
			component.buildTo(b, params);
		}
		return b.toString();
	}
	
}
