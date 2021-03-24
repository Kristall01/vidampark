package hu.g14de;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Set;

import hu.g14de.usermanager.User;

public class GameState
{
    /**
     * Reference to User
     */
    private User user;
    private static int nextId = 0;
    /**
     * Unique ID
     */
    private int id;
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
    private Map map;
    /**
     * Buildings
     */
    private BuildingCatalog buildingCatalog;
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
    private long startTime;

    /**
     * Constructor with strictly new game
     * @param user
     * @param name
     */
    public GameState(User user, String name)
    {
        this.user = user;
        this.name = name;
        this.id = nextId++;
        balance = new Balance();
        this.map = new Map(25,25,this);
        buildingCatalog = new BuildingCatalog();
        guests = new ArrayList<Guest>();
        scheduler = new Scheduler();
        started = false;
        startTime = System.currentTimeMillis();
    }

//Getters
    public String getId()
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

    public BuildingCatalog getBuildingCatalog()
    {
        return this.buildingCatalog;
    }

    public Scheduler getScheduler()
    {
        return this.scheduler;
    }

    public long getStartTime()
    {
        return this.startTime;
    }

//Setters
    public void setName(String name)
    {
        //Checks...
        this.name = name;
    }

//Methods

    /**
     * Catches Ticks
     */
    public void notifyTick()
    {

    }

    /**
     * Opens park
     */
    public void startPark()
    {

    }

}