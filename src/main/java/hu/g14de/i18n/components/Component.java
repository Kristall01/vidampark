package hu.g14de.i18n.components;

import hu.g14de.JsonPrintable;

public interface Component extends JsonPrintable
{
	void buildTo(StringBuilder builder, Object[] params);
	
}
