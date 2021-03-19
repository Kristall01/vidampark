package hu.g14de;

import hu.g14de.i18n.Lang;

public class TranslatedException extends RuntimeException
{

	private final String msgID;
	
	public TranslatedException(String msgID)
	{
		Utils.checkNull(msgID);
		
		this.msgID = msgID;
	}
	
	public String translate(Lang lang, Object... args)
	{
		Utils.checkNull(lang, args);
		
		return lang.translate(this.msgID, args);
	}
	
}
