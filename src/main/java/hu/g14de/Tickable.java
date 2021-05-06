package hu.g14de;

public interface Tickable {
	
	void tick();
	
	Tickable none = () -> {};
	
}
