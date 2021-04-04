package hu.g14de.i18n;

import hu.g14de.Utils;
import hu.g14de.i18n.messages.Message;

import java.util.HashMap;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class Lang
{
	
	private final HashMap<String, Message> messageMap = new HashMap<>();
	private transient final StringBuilder sharedBuilder = new StringBuilder(50);
	
	public void registerMessage(String ID, Message message)
	{
		if(!registerMessageOverride(ID, message))
		{
			throw new IllegalStateException("this ID is already registered");
		}
	}
	
	public boolean registerMessageOverride(String ID, Message message)
	{
		Utils.checkNull(ID, message);
		
		if(this.messageMap.containsKey(ID))
		{
			return false;
		}
		this.messageMap.put(ID, message);
		return true;
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
	
	public static Lang readLangFile(File f) throws IOException
	{
		Utils.checkNull(f);
		
		if(!f.isFile()) {
			throw new FileNotFoundException();
		}
		
		Lang l = new Lang();
		Logger logger = Logger.getLogger("langLogger");
		try(BufferedReader reader = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8))) {
			String line;
			int lineNumber = 1;
			MessageBuilder b = new MessageBuilder();
			while(true) {
				line = reader.readLine();
				if(line == null) {
					break;
				}
				if(line.length() == 0) {
					continue;
				}
				
				int keyPosition = line.indexOf('=');
				if(keyPosition == -1) {
					logger.warning(String.format("invalid lang file '%s' at %d. line: %s", f.getName(), lineNumber, line));
				}
				else {
					try {
						Message m = b.buildMessage(line.substring(keyPosition+1));
						l.registerMessageOverride(line.substring(0, keyPosition), m);
					}
					catch (Exception e) {
						logger.warning(String.format("invalid lang file '%s' at %d: '%s': %s", f.getName(), lineNumber, line, e.toString()));
					}
				}
				++lineNumber;
			}
			return l;
		}
	}
	
}
