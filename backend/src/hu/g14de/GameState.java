package hu.g14de;

import java.math.BigInteger;
import java.util.Collection;
import java.util.ArrayList;
import static hu.g14de.Utils.checkNull;
import hu.g14de.usermanager.User;

public class GameState
{
    /**
     * Reference to User
     */
    private final User user;
    private static int nextId = 0;
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
    private final long startTime;

    /**
     * Constructor with strictly new game
     * @param user reference to user
     * @param name map name given by player
     */
    public GameState(User user, String name)
    {
        checkNull(user);

        this.user = user;
        this.setName(name);
        this.id = nextId++;
        started = false;
        startTime = System.currentTimeMillis();

        balance = new Balance(this, 5000);
        this.map = new Map(25,25,this);
        buildingCatalog = new BuildingCatalog();
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
        if(name.length() > 16 || name.length() < 3)
            throw new TranslatedException("error.gamestate.invalid-name");

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
        started = true;
    }

}