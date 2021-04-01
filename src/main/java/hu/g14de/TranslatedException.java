package hu.g14de;

import hu.g14de.i18n.Lang;

public class TranslatedException extends RuntimeException
{

	private final String msgID;
	private Object[] args;
	
	public TranslatedException(String msgID, Object... args)
	{
		Utils.checkNull(msgID, args);
		
		this.args = args;
		this.msgID = msgID;
	}
	
	public String translate(Lang lang)
	{
		Utils.checkNull(lang, this.args);
		
		return lang.translate(this.msgID, this.args);
	}
	
}
