package hu.g14de.i18n;

import hu.g14de.Utils;
import hu.g14de.i18n.messages.Message;

import java.util.HashMap;

public class Lang
{
	
	private final HashMap<String, Message> messageMap = new HashMap<>();
	private final StringBuilder sharedBuilder = new StringBuilder(50);
	
	public void registerMessage(String ID, Message message)
	{
		Utils.checkNull(ID, message);
		
		if(this.messageMap.containsKey(ID)) {
			throw new IllegalStateException("this ID is already registered");
		}
		this.messageMap.put(ID, message);
	}
	
	public Message getMessage(String ID)
	{
		Utils.checkNull(ID);
		
		return this.messageMap.getOrDefault(ID, null);
	}
	
	public String translate(String msgID, Object... args)
	{
		//nem kell null check, a getMessage() elvégzi (redundáns nullCheck elkerülése)
		
		Message m = getMessage(msgID);
		if(m == null)
			return msgID;
		return m.getValue(this.sharedBuilder, args);
	}
	
}
