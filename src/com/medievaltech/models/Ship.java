package com.medievaltech.models;

import com.medievaltech.*;
import com.medievaltech.utils.DoublePoint;
import android.graphics.*;

public class Ship implements Drawable {
	//instance variables
	private DoublePoint coordinates;
	private Location destination;
	private Paint paint;
	private int speed; //pixels per second
	 
	//Create an instance of a stationary ship
	public Ship(DoublePoint coordinates, int speed, Paint paint) {
		this.coordinates = coordinates;
		this.speed = speed;
		this.paint = paint;
	}
	
	//Create an instance of a moving ship
	public Ship(DoublePoint coordinates, Location destination, int speed, Paint paint) {
		this.coordinates = coordinates;
		this.destination = destination;
		this.speed = speed;
		this.paint = paint;
	}
	
	//Sets the destination coordinates of the ship
	public void flyTo(Location destination) {
		if(this.isDocked())
			this.undock();
		this.destination = destination;
	}
	
	public DoublePoint getCoordinates() {
		return this.coordinates;
	}
	
	public void update(long lastUpdatedAt) {
		double secondSinceLastUpdate =  (System.currentTimeMillis() - lastUpdatedAt)/1000.00;
		double movementAngle = Math.toDegrees((this.destination.y() - this.coordinates.y())/(this.destination.x() - this.coordinates.x()));
		
		
		double newX = this.coordinates.x() + ( Math.sin(movementAngle) * this.speed * secondSinceLastUpdate );
		double newY = this.coordinates.y() + ( Math.cos(movementAngle) * this.speed * secondSinceLastUpdate );
		
		this.coordinates.set(newX, newY);
		
		if(hasReachedDestination())
		{
			dock(this.destination);
		}
	}
	
	public void dock(Location location) {
		location.dockShip(this);
	}
	
	public void undock() {
		this.destination.undockShip(this);
	}
	
	public boolean isDocked() {
		return destination.hasShip(this);
	}
	
	public boolean hasReachedDestination() {
		return this.destination.canDock(this);
	}
	
	public void draw(Canvas c) {
		if(!isDocked()) 
		{
			c.drawRect((float)(this.coordinates.x() - 1), (float)(this.coordinates.y() - 1), (float)(this.coordinates.x() + 1), (float)(this.coordinates.y() + 1), this.paint);
			c.drawPoint((float)this.coordinates.x(), (float)this.coordinates.y(), this.paint);
		}
	}
}