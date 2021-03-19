package hu.g14de;

public interface JsonPrintable
{
	
	default String toJsonString()
	{
		return Utils.toJson(this);
	}
	
}
