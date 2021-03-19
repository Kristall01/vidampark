package hu.g14de;

import java.util.Objects;

public class Utils {
	
	public static void checkNull(Object... args)
	{
		for (Object arg : args)
		{
			Objects.requireNonNull(arg);
		}
	}
	
}
