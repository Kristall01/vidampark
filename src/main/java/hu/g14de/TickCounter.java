package hu.g14de;

public class TickCounter implements Tickable {

	private int current;
	private final int limit;
	private Runnable runTask;
	
	public TickCounter(Runnable r, int limit) {
		this.current = 0;
		this.limit = limit;
		this.runTask = r;
	}
	
	@Override
	public void tick() {
		++current;
		if(current == limit) {
			current = 0;
			runTask.run();
		}
	}
	
}
