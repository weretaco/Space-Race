package com.medievaltech.utils;

public class DoublePoint {
	private double x, y;
	
	public DoublePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double x() {
		return x;
	}
	
	public double y() {
		return y;
	}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double distanceTo(DoublePoint point) {
		return Math.sqrt(Math.pow((point.x() - x), 2.0) + Math.pow((point.y() - y), 2.0));
	}
}
