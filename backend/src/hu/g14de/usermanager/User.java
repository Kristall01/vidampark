package hu.g14de.usermanager;

public class User
{
	
	private String emailAddress;
	private String password;
	private UserManager manager;
	
	public User(UserManager manager, String emailAddress, String password)
	{
		this.manager = manager;
		this.emailAddress = emailAddress;
		this.password = password;
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
		return this.password.equals(password);
	}
	
}
