package hu.g14de;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Utils {
	
	private static Gson gson;
	private static Random random;
	
	static {
		gson = new Gson();
		random = new Random();
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
	
	public static JsonElement fromJson(File file) throws IOException {
		return gson.fromJson(Files.readString(file.toPath()), JsonElement.class);
	}
	
	public static Random getRandom() {
		return random;
	}
	
	public static void checkNull(Object... args)
	{
		for (Object arg : args)
		{
			Objects.requireNonNull(arg);
		}
	}
	
	public static <T> T getRandomElement(List<T> l) {
		if(l.isEmpty()) {
			return null;
		}
		return l.get(getRandom().nextInt(l.size()));
	}
	
}
