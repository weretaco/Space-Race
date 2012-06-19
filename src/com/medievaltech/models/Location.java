package com.medievaltech.models;

import java.util.Vector;

import android.graphics.Canvas;

import com.medievaltech.utils.DoublePoint;
import java.util.*;

public abstract class Location {
	private DoublePoint coordinates;
	protected Vector<Ship> ships;
	
	public Location(DoublePoint coordinates) {
		this.coordinates = coordinates;
		ships = new Vector<Ship> ();
	}
	
	public Location(DoublePoint coordinates, Vector<Ship> ships) {
		this.coordinates = coordinates;
		this.ships = ships;
	}
	
	public int getCollisionRadius() {
		return 0;
	}
	
	public void draw(Canvas c) {
	}
	
	public double x() {
		return coordinates.x();
	}
	
	public double y() {
		return coordinates.y();
	}
	
	public DoublePoint getCoordinates() {
		return this.coordinates;
	}
	
	public boolean hasShip(Ship ship) {
		return ships.contains(ship);
	}
	
	public void setCoordinates(DoublePoint coordinates) {
		this.coordinates = coordinates;
	}
	
	public void dockShip(Ship ship) {
		ships.add(ship);
	}
	
	public void undockShip(Ship ship) {
		ships.remove(ship);
	}
	
	abstract public void destroy();
	abstract public boolean canDock(Ship ship);	
}
