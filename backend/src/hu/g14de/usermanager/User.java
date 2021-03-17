package hu.g14de.usermanager;

public interface User {
	
	public String getEmailAddress();
	public boolean matchPassword(String password);
	
	
}
