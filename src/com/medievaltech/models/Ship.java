package com.medievaltech.models;
import com.medievaltech.utils.DecimalPoint;

import android.graphics.Point;

public class Ship {
	//instance variables
	private DecimalPoint coordinates;
	private DecimalPoint destination;
	private int speed; //pixels per second
	 
	//Create an instance of a stationary ship
	public Ship(DecimalPoint coordinates, int speed) {
		this.coordinates = coordinates;
		this.speed = speed;
	}
	
	//Create an instance of a moving ship
	public Ship(DecimalPoint coordinates, DecimalPoint destination, int speed) {
		this.coordinates = coordinates;
		this.destination = destination;
		this.speed = speed;
	}
	
	//Sets the destination coordinates of the ship
	public void flyTo(DecimalPoint destination) {
		this.destination = destination;
	}
	
	public void update(long lastUpdatedAt) {
		long secondSinceLastUpdate =  (System.currentTimeMillis() - lastUpdatedAt)/1000;
		double movementAngle = Math.toDegrees((this.destination.y() - this.coordinates.y())/(this.destination.x() - this.coordinates.x()));
		
		
		double newX = this.coordinates.x() + ( Math.sin(movementAngle) * this.speed * secondSinceLastUpdate );
		double newY = this.coordinates.y() + ( Math.cos(movementAngle) * this.speed * secondSinceLastUpdate );
		
		this.coordinates.set(newX,newY);
	}
	
	public void draw() {
		
	}
}
