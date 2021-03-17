package hu.g14de;

import java.util.Collection;

public class GameState
{
    private String name;
    private long start;
    private boolean started;
    private Map map;
    private Scheduler scheduler;
    private Balance balance;
    private Collection<Guest> guests;
    private Collection<BuildingTemplate> buildingCatalog;

    public getStart();
    public String getName();
    public void setName();
    public void notifyTick();
    public long getStart();
    public String getName();
    public void setName();
    public void notifyTick();
    public void startPark();
    public Balance getBalance();
    public Scheduler getScheduler();
    public Map getMap();
    public Collection<BuildingTemplate> getBuildingCatalog();
}
