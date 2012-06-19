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
	
	public static int reduceFraction(int numerator, int denaminator)
	{
		while(denaminator != 0) {
			int t = denaminator;
			denaminator = numerator % denaminator;
			numerator = t;
		}
		return numerator;
	}
}
