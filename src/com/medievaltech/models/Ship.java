package com.medievaltech.models;

import com.medievaltech.utils.DoublePoint;
import android.graphics.*;

public class Ship {
	//instance variables
	private DoublePoint coordinates;
	private Location destination;
	private Paint paint;
	private int speed; //pixels per second
	private State currentState;
	private enum State {
		DOCKED, FLYING
	}
	//Create an instance of a stationary ship
	public Ship(DoublePoint coordinates, int speed, Paint paint) {
		this.coordinates = coordinates;
		this.speed = speed;
		this.paint = paint;
		this.currentState = State.DOCKED;
	}
	
	//Create an instance of a moving ship
	public Ship(DoublePoint coordinates, Location destination, int speed, Paint paint) {
		this.coordinates = coordinates;
		this.destination = destination;
		this.speed = speed;
		this.paint = paint;
		this.currentState = State.FLYING;
	}
	
	//Sets the destination coordinates of the ship
	public void flyTo(Location destination) {
		if(this.isDocked())
			this.undock();
		this.destination = destination;
		this.currentState = State.FLYING;
	}
	
	public DoublePoint getCoordinates() {
		return this.coordinates;
	}
	
	public void update(long lastUpdatedAt) {
		double secondSinceLastUpdate =  (System.currentTimeMillis() - lastUpdatedAt)/1000.00;
		double movementAngle = Math.atan2(this.destination.y() - this.coordinates.y(), this.destination.x() - this.coordinates.x());
		
		double newX = this.coordinates.x() + ( Math.cos(movementAngle) * this.speed * secondSinceLastUpdate);
		double newY = this.coordinates.y() + ( Math.sin(movementAngle) * this.speed * secondSinceLastUpdate);
		
		this.coordinates.set(newX, newY);
		
		if(hasReachedDestination())
		{
			dock(this.destination);
		}
	}
	
	public void dock(Location location) {
		location.dockShip(this);
		this.currentState = State.DOCKED;
	}
	
	public void undock() {
		this.destination.undockShip(this);
	}
	
	public boolean isDocked() {
		return currentState == State.DOCKED;
	}
	
	public boolean isFlying() {
		return currentState == State.FLYING;
	}
	
	public boolean hasReachedDestination() {
		return this.destination.canDock(this);
	}
	
	public void draw(Canvas c) {
		if(!isDocked()) {
			c.drawRect((float)coordinates.x()-2, (float)coordinates.y()-2, (float)coordinates.x()+2, (float)coordinates.y()+2, paint);
		}
	}
}
