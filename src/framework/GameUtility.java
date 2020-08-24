package framework;

public class GameUtility {
	public static <T extends Comparable<T>> T clamp(T min, T max, T val) {
		if (val.compareTo(min) < 0)
			return min;
		if (val.compareTo(max) > 0)
			return max;
		return val;
	}
	public static <T extends Comparable<T>> T clampDown(T min, T val) {
		if (val.compareTo(min) < 0)
			return min;
		return val;
	}
	public static <T extends Comparable<T>> T clampUp(T max, T val) {
		if (val.compareTo(max) > 0)
			return max;
		return val;
	}
	public static boolean isInArray(int val, int[] array) {
		for (int i = 0; i < array.length; i++)
			if (val == array[i])
				return true;
		return false;
	}
	public static double sumOfArray(double[] castTimes) {
		double sum = 0;
		for (int i = 0; i < castTimes.length; i++)
			sum += castTimes[i];
		return sum;
	}
}
