package com.medievaltech.models;

import android.graphics.*;

public class Planet {
	public Point location;
	public int radius;
	public Paint p;
	
	public Planet(int x, int y, int radius, Paint p) {
		location = new Point(x, y);
		this.radius = radius;
		this.p = p;
	}
	
	public void draw(Canvas c) {
		c.drawCircle(location.x, location.y, radius, p);
	}
}