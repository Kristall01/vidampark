package hu.g14de.gamestate;

import hu.g14de.VidamparkApp;

public class Scheduler {
	
	private static final long initialSpeed = 1000;
	
	private Thread loopThread;
	private GameState state;
	private long tickGap;
	
	public Scheduler(GameState state) {
		this.state = state;
		this.tickGap = initialSpeed;
	}
	
	public VidamparkApp getApp() {
		return state.getApp();
	}
	
	public synchronized void setSpeed(double d) {
		tickGap = (long)(initialSpeed*d);
	}
	
	private void loopTask() {
		try {
			while(running()) {
				state.receiveTick();
				Thread.sleep(tickGap);
			}
		}
		catch (InterruptedException e) {
			loopThread = null;
		}
	}
	
	public boolean running() {
		return loopThread != null;
	}
	
	public synchronized void stop() {
		loopThread = null;
	}
	
	public synchronized void start() {
		if(running()) {
			return;
		}
		loopThread = new Thread(this::loopTask);
		loopThread.start();
	}
	
}
