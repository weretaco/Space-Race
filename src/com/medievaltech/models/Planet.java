package com.medievaltech.models;

import com.medievaltech.*;
import com.medievaltech.utils.*;

import android.graphics.*;

public class Planet implements Entity {
	public DoublePoint location;
	public int radius;
	public Paint p;
	
	public Planet(int x, int y, int radius, Paint p) {
		location = new DoublePoint(x, y);
		this.radius = radius;
		this.p = p;
	}
	
	public void update(long lastUpdatedAt) {

	}	
	
	public void draw(Canvas c) {
		c.drawCircle((float)location.x(), (float)location.y(), radius, p);
	}
}