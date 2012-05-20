package com.medievaltech.models;

import java.util.ArrayList;

import com.medievaltech.utils.DoublePoint;

public abstract class Location {
	private DoublePoint coordinates;
	private ArrayList<Ship> ships;
	
	public Location(DoublePoint coordinates) {
		this.coordinates = coordinates;
		ships = new ArrayList<Ship> ();
	}
	
	public Location(DoublePoint coordinates, ArrayList<Ship> ships) {
		this.coordinates = coordinates;
		this.ships = ships;
	}
	
	public double x() {
		return coordinates.x();
	}
	
	public double y() {
		return coordinates.y();
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
	
	abstract void destroy();
}
