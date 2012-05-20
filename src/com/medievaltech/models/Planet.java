package com.medievaltech.models;

import com.medievaltech.*;
import com.medievaltech.utils.*;

import android.graphics.*;

public class Planet extends Location implements Drawable {
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
	void destroy() {
		// TODO Auto-generated method stub
		
	}
}