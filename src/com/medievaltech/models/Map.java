package com.medievaltech.models;

import java.util.LinkedList;

import android.graphics.Canvas;

public class Map {
	LinkedList<Location> locations = new LinkedList<Location>();
	LinkedList<Ship> ships = new LinkedList<Ship>();
	int dimX, dimY;
	
	public Map(int dimX, int dimY) {
		this.dimX = dimX;
		this.dimY = dimY;
	}
	
	public void addLocation(Location l) {
		locations.add(l);
	}
	
	public void addShip(Ship s) {
		ships.add(s);
	}
	
	public void draw(Canvas c) {
		for(Location l : locations) {
			l.draw(c);
		}
		
		for(Ship s : ships) {
			s.draw(c);
		}
	}
	
	public void update(long lastUpdatedAt) {
		for(Ship s : ships) {
        	s.update(lastUpdatedAt);
        }
	}
	
	public void createPlanets(int x) {

	}
}