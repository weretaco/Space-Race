package com.medievaltech.models;

import com.medievaltech.utils.DoublePoint;

import android.graphics.*;

public class Planet {
	public DoublePoint location;
	public int radius;
	public Paint p;
	
	public Planet(int x, int y, int radius, Paint p) {
		location = new DoublePoint(x, y);
		this.radius = radius;
		this.p = p;
	}
	
	public void draw(Canvas c) {
		c.drawCircle((float)location.x(), (float)location.y(), radius, p);
	}
}