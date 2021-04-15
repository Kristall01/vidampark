package hu.g14de;

public class TickReciever {

	private int current;
	private final int limit;
	private Runnable runTask;
	
	public TickReciever(Runnable r, int limit) {
		this.current = 0;
		this.limit = limit;
		this.runTask = r;
	}
	
	public void tick() {
		++current;
		if(current == limit) {
			current = 0;
			runTask.run();
		}
	}
}
