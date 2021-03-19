package hu.g14de.usermanager;

import hu.g14de.Utils;
import hu.g14de.TranslatedException;
import java.util.HashMap;

public class UserManager
{

	private final HashMap<String, User> usermap = new HashMap<>();
	
	/**
	 * Creates a new user and stores it.
	 *
	 * @param email new user's email address
	 * @param password new user's email address
	 * @return new user on success, null if creation failed
	 */
	public User createUser(String email, String password) throws User.InvalidEmailAddressException, User.InvalidPasswordException
	{
		if(this.usermap.containsKey(email))
		{
			throw new EmailTakenException();
		}
		User u = new User(this, email, password);
		this.usermap.put(email, u);
		return u;
	}
	
	/**
	 * Gets user by its email address. Returns null if no user was found by this email address.
	 * @param email user's email
	 * @return User belonging to this email. Null if no user was found.
	 */
	public User getUserByEmail(String email)
	{
		Utils.checkNull(email);
		
		return this.usermap.getOrDefault(email, null);
	}
	
	public static class EmailTakenException extends TranslatedException
	{
		
		public EmailTakenException()
		{
			super("error.usermanager.email-address-taken");
		}
		
	}

}
