package hu.g14de.usermanager;

import java.util.HashMap;

public class UserManager
{

	private HashMap<String, User> usermap = new HashMap<String, User>();
	
	/**
	 * Creates a new user and stores it.
	 *
	 * @param email new user's email address
	 * @param password new user's email address
	 * @return new user on success, null if creation failed
	 */
	public User createUser(String email, String password)
	{
		if(!usermap.containsKey(email))
		{
			return null;
		}
		User u = new User(this, email, password);
		usermap.put(email, u);
		return u;
	}
	
	/**
	 * Gets user by its email address. Returns null if no user was found by this email address.
	 * @param email user's email
	 * @return User belonging to this email. Null if no user was found.
	 */
	public User getUserByEmail(String email)
	{
		return usermap.getOrDefault(email, null);
	}

}
