package com.medievaltech.models;

import com.medievaltech.utils.*;
import android.graphics.*;

public class Planet extends Location {
	public int radius;
	public Paint p;
	
	public Planet(int x, int y, int radius, Paint p) {
		super(new DoublePoint(x, y));
		this.radius = radius;
		this.p = p;
	}
	
	public void draw(Canvas c) {
		c.drawCircle((float)x(), (float)y(), radius, p);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canDock(Ship ship) {
		return this.getCoordinates().distanceTo(ship.getCoordinates()) <= (double)radius;
	}
}
