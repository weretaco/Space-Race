package com.medievaltech.models;

import com.medievaltech.utils.DoublePoint;
import android.graphics.*;
import android.util.Log;

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
	public Ship(Location location, int speed, Paint paint) {
		this.coordinates = location.getCoordinates().clone();
		this.destination = location;
		this.speed = speed;
		this.paint = paint;
	}
	
	//Create an instance of a moving ship
	public Ship(DoublePoint coordinates, Location destination, int speed, Paint paint) {
		this.coordinates = coordinates.clone();
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
	
	public void update(int speedMultiple, long lastUpdatedAt) {
		if(this.currentState == State.FLYING)
		{
			try
			{
				int adjustedSpeed = speedMultiple * 2 + this.speed;
				double secondSinceLastUpdate =  (System.currentTimeMillis() - lastUpdatedAt)/1000.00;
				double movementAngle = Math.atan2(this.destination.y() - this.coordinates.y(), this.destination.x() - this.coordinates.x());
		
				double newX = this.coordinates.x() + ( Math.cos(movementAngle) * adjustedSpeed * secondSinceLastUpdate);
				double newY = this.coordinates.y() + ( Math.sin(movementAngle) * adjustedSpeed * secondSinceLastUpdate);
			
				this.coordinates.set(newX, newY);
		
				if(hasReachedDestination())
				{
					dock(this.destination);
				}
			} catch(Exception e) {
				Log.e("SpaceRaceError", this.toString());
			}
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
		if(this.currentState == State.FLYING) {
			//c.drawText("X:" + coordinates.x() + " Y:" + coordinates.y(), (float)coordinates.x()-15, (float)coordinates.y()-15, paint);  
			c.drawRect((float)coordinates.x()-2, (float)coordinates.y()-2, (float)coordinates.x()+2, (float)coordinates.y()+2, paint);
		}
	}
	
	public String toString() {
		return " Coordinates: " + this.coordinates + " Current State: " + this.currentState.toString() + " Destination: " + ((this.destination != null) ? this.destination.toString() : "null") + " Speed: " + this.speed + " Paint: " +this.paint.toString();
	}
}
