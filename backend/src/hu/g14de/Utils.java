package hu.g14de;

import com.google.gson.Gson;

import java.util.Objects;

public class Utils {
	
	private static Gson gson;
	
	static {
		gson = new Gson();
	}
	
	public static String toJson(Object o) {
		return gson.toJson(o);
	}
	
	public static void checkNull(Object... args)
	{
		for (Object arg : args)
		{
			Objects.requireNonNull(arg);
		}
	}
	
}
