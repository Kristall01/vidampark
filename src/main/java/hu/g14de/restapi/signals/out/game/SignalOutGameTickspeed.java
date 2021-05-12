package hu.g14de.restapi.signals.out.game;

import hu.g14de.gamestate.Scheduler;
import hu.g14de.restapi.signals.SignalOut;

public class SignalOutGameTickspeed extends SignalOut {
	
	private long tickspeed;
	
	public SignalOutGameTickspeed(Scheduler sch) {
		super("tickspeed");
		this.tickspeed = sch.getTickGap();
	}
	
	@Override
	public Object serializedData() {
		return tickspeed;
	}
}
