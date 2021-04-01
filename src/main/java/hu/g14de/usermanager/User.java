package hu.g14de.usermanager;

import hu.g14de.GameState;
import hu.g14de.JsonPrintable;
import hu.g14de.Utils;
import hu.g14de.TranslatedException;

public class User implements JsonPrintable
{
	
	private String emailAddress;
	private String password;
	private final transient UserManager manager;
	private GameState state;
	
	public User(UserManager manager, String emailAddress, String password) throws InvalidEmailAddressException, InvalidPasswordException
	{
		Utils.checkNull(manager, emailAddress, password);
		
		if(!emailAddress.matches("^[a-zA-Z0-9_+&*\\-]*@[a-zA-Z0-9\\-]+.[a-z]+$"))
		{
			throw new InvalidEmailAddressException();
		}
		if(password.length() < 8)
		{
			throw new InvalidPasswordException(8);
		}
		
		this.manager = manager;
		this.emailAddress = emailAddress;
		this.password = password;
		this.state = new GameState(this, "default");
	}
	
	public GameState getState() {
		return state;
	}
	
	public UserManager getManager()
	{
		return this.manager;
	}
	
	public String getEmailAddress()
	{
		return this.emailAddress;
	}
	
	/**
	 * @param password password to test against user's password
	 * @return true, if the give password matches the user's password. Otherwise false.
	 */
	public boolean matchPassword(String password)
	{
		Utils.checkNull(password);
		
		return this.password.equals(password);
	}
	
	public static class InvalidEmailAddressException extends TranslatedException
	{
		
		public InvalidEmailAddressException()
		{
			super("error.user.invalid-email-address.format");
		}
	}
	
	public static class InvalidPasswordException extends TranslatedException
	{
		
		public InvalidPasswordException(int minLength)
		{
			super("error.user.invalid-password.too-short", minLength);
		}
		
	}
	
}
