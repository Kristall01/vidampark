package hu.g14de.gamestate;

import hu.g14de.VidamparkApp;
import hu.g14de.restapi.signals.out.game.SignalOutGameTickspeed;

public class Scheduler {
	
	private static final long initialSpeed = 500;
	
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
	
	public synchronized void setRelativeSpeed(double d) {
		setAbsoluteSpeed((long)(initialSpeed*d));
	}
	
	public synchronized void setAbsoluteSpeed(long ticks) {
		this.tickGap = ticks;
		state.broadcastSignal(new SignalOutGameTickspeed(this));
	}
	
	public void recalcState() {
		boolean newState = !state.isManualPause() && state.hasObserver() && state.isOpen();
		if(running() == newState) {
			return;
		}
		if(newState) {
			start();
		}
		else {
			stop();
		}
	}
	
	private void loopTask() {
		try {
			long now, then;
			while(running()) {
				now = System.currentTimeMillis();
				try {
					state.receiveTick();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				then = System.currentTimeMillis();
				Thread.sleep(Math.max(1, tickGap - (then-now)));
			}
		}
		catch (InterruptedException e) {
			loopThread = null;
		}
	}
	
	public boolean running() {
		return loopThread != null;
	}
	
	private synchronized void stop() {
		loopThread = null;
	}
	
	public long getTickGap() {
		return tickGap;
	}
	
	private synchronized void start() {
		if(running()) {
			return;
		}
		loopThread = new Thread(this::loopTask);
		loopThread.start();
	}
	
}
