package hu.g14de.gamestate;

import hu.g14de.gamestate.mapelements.IBuildingTemplate;

import java.util.Collection;

public interface IBuildingCatalog {

	IBuildingTemplate getTemplateByID(String ID);
	Collection<IBuildingTemplate> getAvailableTemplates();

}
