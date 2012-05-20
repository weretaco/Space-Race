package com.medievaltech.models;
import com.medievaltech.utils.DoublePoint;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ship {
	//instance variables
	private DoublePoint coordinates;
	private DoublePoint destination;
	private Paint paint;
	private int speed; //pixels per second
	 
	//Create an instance of a stationary ship
	public Ship(DoublePoint coordinates, int speed, Paint paint) {
		this.coordinates = coordinates;
		this.speed = speed;
		this.paint = paint;
	}
	
	//Create an instance of a moving ship
	public Ship(DoublePoint coordinates, DoublePoint destination, int speed, Paint paint) {
		this.coordinates = coordinates;
		this.destination = destination;
		this.speed = speed;
		this.paint = paint;
	}
	
	//Sets the destination coordinates of the ship
	public void flyTo(DoublePoint destination) {
		this.destination = destination;
	}
	
	public void update(long lastUpdatedAt) {
		long secondSinceLastUpdate =  (System.currentTimeMillis() - lastUpdatedAt)/1000;
		double movementAngle = Math.toDegrees((this.destination.y() - this.coordinates.y())/(this.destination.x() - this.coordinates.x()));
		
		
		double newX = this.coordinates.x() + ( Math.sin(movementAngle) * this.speed * secondSinceLastUpdate );
		double newY = this.coordinates.y() + ( Math.cos(movementAngle) * this.speed * secondSinceLastUpdate );
		
		this.coordinates.set(newX, newY);
	}
	
	public void draw(Canvas c) {
		c.drawPoint((float)this.coordinates.x(), (float)this.coordinates.y(), this.paint);
	}
}
