package hu.g14de;

import hu.g14de.gamestate.IBuildingCatalog;
import hu.g14de.gamestate.mapelements.IBuildingTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBuildingCatalog implements IBuildingCatalog {
	
	private final HashMap<String, IBuildingTemplate> templateMap;
	
	public SimpleBuildingCatalog(Map<String, ? extends IBuildingTemplate> templateMap) {
		this.templateMap = new HashMap<>(templateMap);
	}
	
	public SimpleBuildingCatalog(IBuildingCatalog fakeCatalog) {
		if(fakeCatalog instanceof SimpleBuildingCatalog) {
			templateMap = new HashMap<>(((SimpleBuildingCatalog)fakeCatalog).templateMap);
		}
		else {
			templateMap = new HashMap<>();
			for (IBuildingTemplate template : fakeCatalog.getAvailableTemplates()) {
				register(template.type(), template);
			}
		}
	}
	
	@Override
	public IBuildingTemplate getTemplateByID(String ID) {
		return templateMap.get(ID);
	}
	
	public SimpleBuildingCatalog(SimpleBuildingCatalog copySource) {
		this(copySource.templateMap);
	}
	
	public SimpleBuildingCatalog() {
		this.templateMap = new HashMap<>();
	}
	
	public void register(String type, IBuildingTemplate template) {
		if(templateMap.containsKey(type)) {
			throw new TemplateNameTakenException();
		}
		templateMap.put(type, template);
	}
	
	@Override
	public Collection<IBuildingTemplate> getAvailableTemplates() {
		return List.copyOf(templateMap.values());
	}
	
	public static class TemplateNameTakenException extends RuntimeException {}
	
}
