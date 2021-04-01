package hu.g14de;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.Objects;

public class Utils {
	
	private static Gson gson;
	
	static {
		gson = new Gson();
	}
	
	public static String toJson(Object o) {
		return gson.toJson(o);
	}
	
	public static Gson gson() {
		return gson;
	}
	
	public static JsonElement fromJson(String json) {
		return gson.fromJson(json, JsonElement.class);
	}
	
	public static void checkNull(Object... args)
	{
		for (Object arg : args)
		{
			Objects.requireNonNull(arg);
		}
	}
	
}