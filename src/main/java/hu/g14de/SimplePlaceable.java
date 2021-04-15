package hu.g14de;

public class SimplePlaceable implements Placeable {
	
	private IBuildingTemplate template;
	
	public SimplePlaceable(IBuildingTemplate template) {
		this.template = template;
	}
	
	@Override
	public IBuildingTemplate getTemplate() {
		return template;
	}
	
}
