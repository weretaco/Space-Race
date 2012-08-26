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
	
	/*
	public int getCollisionRadius() {
		return radius+5;
	}
	*/
	
	public void draw(Canvas c) {
		c.drawCircle((float)x(), (float)y(), radius, p);
		c.drawText(this.ships.size() + "" ,(float)x() - radius - 10, (float)y(), p); 
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public boolean canDock(Ship ship) {
		return this.getCoordinates().distanceTo(ship.getCoordinates()) <= (double)radius;
	}
}
