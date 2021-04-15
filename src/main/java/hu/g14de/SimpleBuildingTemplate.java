package hu.g14de;

public class SimpleBuildingTemplate implements IBuildingTemplate {

	private boolean needRoad;
	private String type;
	private int cost;
	private int time;
	private boolean road;
	
	public SimpleBuildingTemplate(boolean needRoad, String type, int cost, int time, boolean road) {
		Utils.checkNull(type);
		
		this.needRoad = needRoad;
		this.type = type;
		this.cost = cost;
		this.time = time;
		this.road = road;
	}
	
	@Override
	public boolean needsRoadConnection() {
		return needRoad;
	}
	
	@Override
	public String type() {
		return type;
	}
	
	@Override
	public int getBuildCost() {
		return cost;
	}
	
	@Override
	public int getBuildTime() {
		return time;
	}
	
	@Override
	public boolean isRoad() {
		return road;
	}
	
	@Override
	public Placeable createInstance() {
		return new SimplePlaceable(this);
	}
}