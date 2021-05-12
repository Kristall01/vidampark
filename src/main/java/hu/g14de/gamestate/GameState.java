package hu.g14de.gamestate;

import com.google.gson.JsonObject;
import hu.g14de.TickCounter;
import hu.g14de.TranslatedException;
import hu.g14de.Utils;
import hu.g14de.VidamparkApp;
import hu.g14de.restapi.Connection;
import hu.g14de.restapi.signals.SignalOut;
import hu.g14de.restapi.signals.out.common.SignalOutCommonSetscene;
import hu.g14de.restapi.signals.out.game.SignalOutGamePause;
import hu.g14de.restapi.signals.out.game.SignalOutGameStartpark;
import hu.g14de.usermanager.User;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static hu.g14de.Utils.checkNull;

public class GameState
{
	private final User user;
	private final int id;
	private int nextGuestID = 0;
	private String name;
	private Balance balance;
	private final IMap map;
	private LinkedList<Connection> observers = new LinkedList<>();
	private Scheduler scheduler;
	private IBuildingCatalog catalog;
	private ArrayList<Guest> guests = new ArrayList<>();
	private TickCounter guestCounter = new TickCounter(this::addRandomGuest, 4);
	
	private boolean manualPause = false;
	private ParkStatus parkStatus = ParkStatus.CLOSED;

	public GameState(User user, String name, int ID, int width, int height, IBuildingCatalog catalog)
	{
		checkNull(user, name, catalog);
		this.setName(name);
	
		this.user = user;
		this.id = ID;
		this.catalog = catalog;
		
		try {
			this.balance = new Balance(this, 500000);
		}
		catch (Balance.NegativeMoneyException e) {
			//impossible here
		}
		this.map = new IMap(this,width,height);
		Coordinate entrance = map.getEntrance().getCoordinate();
		this.map.placeBuilding(entrance.getX(), entrance.getY(), catalog.getTemplateByID("road"));
		this.scheduler = new Scheduler(this);
	}

	public void destructor()
	{
		broadcastSignal(new SignalOutCommonSetscene("select"));
		observers.clear();
	}
	
	public void togglePaused() {
		this.manualPause = !manualPause;
		broadcastSignal(new SignalOutGamePause(this));
		scheduler.recalcState();
	}
	
	public boolean isManualPause() {
		return manualPause;
	}
	
	public boolean hasObserver() {
		return observers.size() != 0;
	}
	
	public void addRandomGuest() {
		int nextID = nextGuestID++;
		Cell entrance = map.getEntrance();
		Guest guest = new Guest(this, nextID, entrance);
		guests.add(guest);
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
		if(name.trim().length() == 0)
			throw new TranslatedException("error.gamestate.invalid-name", name);
		if(name.length() > 16 || name.length() < 3)
			throw new TranslatedException("error.gamestate.invalid-name-length");
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
		
		scheduler.recalcState();
	}
	
	public void removeObserver(Connection c) {
		observers.remove(c);
		scheduler.recalcState();
	}
	
	public void broadcastSignal(SignalOut signalOut) {
		for (Connection observer : observers) {
			observer.sendSignal(signalOut);
		}
	}
	
	public Scheduler getScheduler() {
		return scheduler;
	}
	
	public synchronized void receiveTick() {
		this.balance.addMoney(BigInteger.valueOf(1));
		this.map.receiveTick();
		Iterator<Guest> it = guests.iterator();
		while(it.hasNext()) {
			Guest g = it.next();
			g.tick();
			if(g.readyToRemove()) {
				it.remove();
			}
		}
		guestCounter.tick();
		balance.broadcastChanges();
	}
	
	public void dropGuestAt(Guest g, Cell cell) {
		g.notifyGameover(cell);
	}
	
	/*public void closePark() {
		if(parkStatus != ParkStatus.OPEN) {
			return;
		}
		parkStatus = ParkStatus.CLOSING;
	}*/
	
	public void startPark() {
		if(parkStatus != ParkStatus.CLOSED) {
			return;
		}
		parkStatus = ParkStatus.OPEN;
		broadcastSignal(new SignalOutGameStartpark());
		scheduler.recalcState();
	}
	
	public boolean isOpen() {
		return parkStatus == ParkStatus.OPEN;
	}
	
	public void setTickSpeed(long i) {
		scheduler.setAbsoluteSpeed(i);
	}
	
	public List<Guest> getGuestsCopy() {
		return List.copyOf(guests);
	}
	
}