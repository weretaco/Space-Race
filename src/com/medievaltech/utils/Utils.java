package com.medievaltech.utils;

import java.util.Random;

public class Utils {
	private static Random r = new Random();
	
	public static int randomInt(int lowerLimit, int upperLimit) {
		return (int)(r.nextDouble()*(upperLimit-lowerLimit))+lowerLimit;
	}
	
	public static int randomInt(int upperLimit) {
		return (int)(r.nextDouble()*upperLimit);
	}
}
