package hu.g14de.i18n;

import hu.g14de.Utils;
import hu.g14de.i18n.components.Component;
import hu.g14de.i18n.messages.DynamicMessage;
import hu.g14de.i18n.messages.Message;
import hu.g14de.i18n.messages.StaticMessage;
import hu.g14de.i18n.components.DynamicComponent;
import hu.g14de.i18n.components.StaticComponent;

import java.util.ArrayList;

public class MessageBuilder
{
	
	private final ArrayList<Component> components = new ArrayList<>();
	private int i = 0;
	private String original;
	
	private void readCommand()
	{
		int startPosition = i;
		boolean run = true;
		while(run && hasNext())
		{
			if(nextChar() == '}')
			{
				try
				{
					int buildIndex = Integer.parseInt(this.original, startPosition, this.i-1, 10);
					this.components.add(new DynamicComponent(buildIndex));
					run = false;
				}
				catch (NumberFormatException ex)
				{
					throw new IllegalStateException("invalid index number");
				}
			}
		}
		if(run)
		{
			throw new IllegalStateException("bracket has no '}' ending");
		}
	}
	
	public Message buildMessage(String input)
	{
		Utils.checkNull(input);
		
		this.original = input;
		this.i = 0;
		this.components.clear();
		
		int beginIndex = 0;
		while(hasNext())
		{
			if(nextChar() == '{')
			{
				if(i != beginIndex+1)
				{
					this.components.add(new StaticComponent(this.original, beginIndex, i-1));
				}
				readCommand();
				beginIndex = i;
			}
		}
		if(this.components.size() == 0)
		{
			return new StaticMessage(this.original);
		}
		if(beginIndex != this.original.length())
		{
			this.components.add(new StaticComponent(this.original, beginIndex, this.i));
		}
		Component[] t = new Component[this.components.size()];
		this.components.toArray(t);
		return new DynamicMessage(t, this.original);
	}
	
	private boolean hasNext()
	{
		return this.i != this.original.length();
	}
	
	private char nextChar()
	{
		return this.original.charAt(i++);
	}

}
