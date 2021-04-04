package hu.g14de.restapi.signals.out.select;

import hu.g14de.restapi.signals.SignalOut;

public class SignalOutSelectAdd extends SignalOut {
	
	private int id;
	private String name;
	private long createTime;
	
	public SignalOutSelectAdd(int id, String name, long createTime) {
		super("add");
		this.id = id;
		this.name = name;
		this.createTime = createTime;
	}
}
