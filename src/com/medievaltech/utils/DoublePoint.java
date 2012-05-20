package com.medievaltech.utils;

public class DoublePoint {
	private double x, y;
	
	public DoublePoint(double x, double y) {
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
