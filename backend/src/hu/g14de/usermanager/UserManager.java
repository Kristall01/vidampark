package hu.g14de.usermanager;

public interface UserManager {

	public User createUser(String email, String password);
	public User getUserByEmail(String email);

}
