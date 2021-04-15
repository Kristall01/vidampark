package hu.g14de;

public interface IBuildingTemplate {
	
	boolean needsRoadConnection();
	String type();
	int getBuildCost();
	int getBuildTime();
	boolean isRoad();
	Placeable createInstance();
	
}
