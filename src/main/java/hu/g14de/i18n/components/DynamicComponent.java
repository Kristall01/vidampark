package hu.g14de.i18n.components;

import hu.g14de.Utils;

public class DynamicComponent implements Component
{
	
	private final int index;
	
	public DynamicComponent(int index)
	{
		this.index = index;
	}
	
	@Override
	public void buildTo(StringBuilder builder, Object[] params)
	{
		Utils.checkNull(builder, params);
		
		builder.append(params[this.index]);
	}
	
}
