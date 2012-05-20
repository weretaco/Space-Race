package com.medievaltech.utils;

public class DecimalPoint {
	private double x, y;
	
	public DecimalPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double x () {
		return x;
	}
	
	public double y () {
		return y;
	}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
