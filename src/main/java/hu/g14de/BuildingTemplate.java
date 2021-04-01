package hu.g14de;

public class BuildingTemplate
{
    private String name;
    private long buildCost;
    private long buildTime;
    private int width;
    private int height;

    public BuildingInstance createInstance()
    {
        BuildingInstance b = new BuildingInstance(height,width);
        return b;
    }
}
