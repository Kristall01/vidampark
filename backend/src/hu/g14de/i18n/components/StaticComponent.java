package hu.g14de.i18n.components;

import hu.g14de.Utils;

public class StaticComponent implements Component
{
	
	private final String text;
	private final int start, end;
	
	public StaticComponent(String text, int start, int end)
	{
		Utils.checkNull(text);
		
		this.text = text;
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void buildTo(StringBuilder builder, Object[] params)
	{
		Utils.checkNull(builder, params);
		
		builder.append(this.text, this.start, this.end);
	}
	
}
