package hu.g14de.gamestate;

import com.google.gson.JsonObject;
import hu.g14de.TranslatedException;
import hu.g14de.Utils;
import hu.g14de.VidamparkApp;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalOut;
import hu.g14de.restapi.signals.out.game.SignalOutGameStartpark;
import hu.g14de.usermanager.User;

import java.math.BigInteger;
import java.util.LinkedList;

import static hu.g14de.Utils.checkNull;

public class GameState
{
	private final User user;
	private final int id;
	private String name;
	private Balance balance;
	private final IMap map;
	private LinkedList<Connection> observers = new LinkedList<>();
	private Scheduler scheduler;
	private IBuildingCatalog catalog;

	public GameState(User user, String name, int ID, IBuildingCatalog catalog)
	{
		checkNull(user, name, catalog);
		this.setName(name);
	
		this.user = user;
		this.id = ID;
		this.catalog = catalog;
		
		try {
			this.balance = new Balance(this, 5000);
		}
		catch (Balance.NegativeMoneyException e) {
			//impossible here
		}
		this.map = new IMap(this,10,10);
		this.scheduler = null;
	}
	
	public VidamparkApp getApp() {
		return user.getApp();
	}

	public int getId()
	{
		return this.id;
	}

	public String getName()
	{
		return this.name;
	}

	public Balance getBalance()
	{
		return this.balance;
	}

	public IMap getMap()
	{
		return this.map;
	}

	public User getUser() {
		return user;
	}
	
	public void setName(String name)
	{
		if(name.length() > 16 || name.length() < 3)
			throw new TranslatedException("error.gamestate.invalid-name", name);
		this.name = name;
	}
	
	public JsonObject getAsListEntry() {
		JsonObject o = new JsonObject();
		o.addProperty("id", getId());
		o.addProperty("name", getName());
		return o;
	}
	
	public IBuildingCatalog getCatalog() {
		return catalog;
	}
	
	public void addObserver(Connection c) {
		Utils.checkNull(c);
		observers.add(c);
		if(observers.size() == 1 && isStarted()) {
			scheduler.start();
		}
	}
	
	public void removeObserver(Connection c) {
		observers.remove(c);
		if(observers.size() == 0 && isStarted()) {
			scheduler.stop();
		}
	}
	
	public void broadcastSignal(SignalOut signalOut) {
		for (Connection observer : observers) {
			observer.sendSignal(signalOut);
		}
	}
	
	public synchronized void receiveTick() {
		this.balance.addMoney(BigInteger.valueOf(1));
		this.map.receiveTick();
	}
	
	public boolean isStarted() {
		return scheduler != null;
	}
	
	public void startPark() {
		if(isStarted()) {
			return;
		}
		scheduler = new Scheduler(this);
		scheduler.start();
		broadcastSignal(new SignalOutGameStartpark());
	}
	
}