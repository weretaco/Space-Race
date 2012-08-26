package com.medievaltech.utils;

import java.util.Random;

import android.util.Log;

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
	
	public static void printCurrentMethod() {
		StackTraceElement e = new Throwable().getStackTrace()[1]; 
 
		String callerMethodName = e.getMethodName(); 
		String callerClassName = e.getClassName();
		
		if(callerMethodName.equals(callerClassName))
			Log.i(Settings.APP_NAME, "Inside "+callerClassName+" constructor");
		else
			Log.i(Settings.APP_NAME, "Inside "+callerClassName+"."+callerMethodName+"()");
	}
}
