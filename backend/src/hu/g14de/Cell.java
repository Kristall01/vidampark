package hu.g14de;


public class Cell
{
    //Constructor
    public Cell(Map mapReference, int x, int y, BuildingInstance buildingInstance)
    {
        this.mapReference = mapReference;
        this.x = x;
        this.y = y;
        this.buildingInstance = buildingInstance;
    }

    //Variables
    private int x;
    private int y;

    private Map mapReference;
    private BuildingInstance buildingInstance;

    //Getters
    public int getX () {return this.x;}
    public int getY () {return this.y;}

    public Map getMapReference () {return this.mapReference;}
    public BuildingInstance getBuildingInstance()
    {
        return buildingInstance;
    }
    //Setters

    public void setBuildingInstance(BuildingInstance buildingInstance) { this.buildingInstance = buildingInstance; }




    //Methods

}
