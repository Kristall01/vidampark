package hu.g14de;

import java.math.BigInteger;
import java.util.Collection;

public class GameState
{
    /**
     * Unique ID
     */
    private String id;
    /**
     * Player given name
     */
    private String name;
    /**
     * Money balance
     */
    private BigInteger balance;
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
    private Collection<Guest> guests;
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

    public GameState()
    {

    }

//Getters
    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public BigInteger getBalance()
    {
        return balance;
    }

    public Map getMap()
    {
        return map;
    }

    public BuildingCatalog getBuildingCatalog()
    {
        return buildingCatalog;
    }

    public Scheduler getScheduler()
    {
        return scheduler;
    }

    public long getStartTime()
    {
        return startTime;
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