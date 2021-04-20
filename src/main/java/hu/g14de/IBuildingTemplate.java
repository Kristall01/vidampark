package hu.g14de;

public interface IBuildingTemplate {
	
	boolean needsRoadConnection();
	String type();
	String getNickname();
	int getBuildCost();
	int getBuildTime();
	boolean isRoad();
	Placeable createInstance();
	int width();
	int height();
	String mapIcon();
	String shopIcon();
	Object serialize();
	
}
