package hu.g14de;

import java.util.ArrayList;
import static hu.g14de.Utils.checkNull;

import com.google.gson.JsonObject;
import hu.g14de.usermanager.User;

public class GameState
{
    /**
     * Reference to User
     */
    private final User user;
    /**
     * Unique ID
     */
    private final int id;
    /**
     * Player given name
     */
    private String name;
    /**
     * Money balance
     */
    private Balance balance;
    /**
     * The actual map
     */
    private final Map map;
    /**
     * Guests who are actually visiting the park
     */
    private ArrayList<Guest> guests;
    /**
     * Responsible for ticking
     */
    private Scheduler scheduler;
    /**
     * Is the park opened
     */
    private boolean started;
    /**
     * Timestamp Creation Date
     */
    private final long startTime;
    private boolean active = false;

    /**
     * Constructor with strictly new game
     * @param user reference to user
     * @param name map name given by player
     */
    public GameState(User user, String name, int ID)
    {
        checkNull(user, name);

        this.user = user;
        this.setName(name);
        this.id = ID;
        started = false;
        startTime = System.currentTimeMillis();

        balance = new Balance(this, 5000);
        this.map = new Map(10,10,this);
        guests = new ArrayList<Guest>();
        scheduler = new Scheduler();
    }

//Getters
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

    public Map getMap()
    {
        return this.map;
    }

    public Scheduler getScheduler()
    {
        return this.scheduler;
    }

    public long getStartTime()
    {
        return this.startTime;
    }
	
	public User getUser() {
		return user;
	}
	
	//Setters
    public void setName(String name)
    {
        if(name.length() > 16 || name.length() < 3)
            throw new TranslatedException("error.gamestate.invalid-name", name);
        this.name = name;
    }
    
    public void activate() {
    	this.active = true;
	}
	
	public void deactivate() {
    	this.active = false;
	}
	
	public JsonObject getAsListEntry() {
    	JsonObject o = new JsonObject();
    	o.addProperty("id", getId());
    	o.addProperty("name", getName());
    	o.addProperty("createtime", startTime);
    	return o;
	}

}