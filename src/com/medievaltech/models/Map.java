package com.medievaltech.models;

import com.medievaltech.utils.*;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.LinkedList;

public class Map {
	LinkedList<Location> locations = new LinkedList<Location>();
	LinkedList<Ship> ships = new LinkedList<Ship>();
	int dimX, dimY;
	
	public Map(int dimX, int dimY) {
		this.dimX = dimX;
		this.dimY = dimY;
	}
	
	public Map(int dimX, int dimY, int numPlanets) {
		this.dimX = dimX;
		this.dimY = dimY;
		
		for(int x=0; x<numPlanets; x++)
			generateRandomPlanet();
	}
	
	public Ship getRandomShip() {
		return ships.get(Utils.randomInt(ships.size()));
	}
	
	public Ship getRandomDockedShip() {
		LinkedList<Ship> dockedShips = new LinkedList<Ship> ();
		for(Ship s: this.ships)
			if(s.isDocked())
				dockedShips.add(s);
		return dockedShips.get(Utils.randomInt(dockedShips.size()));
	}
	
	public Planet getRandomPlanet() {
		Location location;
		do {
			location = locations.get(Utils.randomInt(locations.size()));
		} while(!location.getClass().toString().equals("Planet"));
		return (Planet)location;
	}
	
	public void addLocation(Location l) {
		locations.add(l);
	}
	
	public void addShip(Ship s) {
		ships.add(s);
	}
	
	public void generateRandomPlanet() {
		Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.rgb(Utils.randomInt(256), Utils.randomInt(256), Utils.randomInt(256)));
    	
        int radius, x, y;
        boolean collision;
        
        do {
        	radius = Utils.randomInt(10, 50);
            x = Utils.randomInt(dimX-radius*2)+radius;
            y = Utils.randomInt(dimY-radius*2)+radius;
            collision = false;
            
            for(Location l : locations) {
            	if(l.getCoordinates().distanceTo(new DoublePoint(x, y))-l.getCollisionRadius()-radius <= 0) {
            		collision = true;
            		break;
            	}
            }
        } while(collision);
        
        this.addLocation(new Planet(x, y, radius, p));
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
		Log.i("SpaceRace", "Inside map.update");
		
		for(Ship s : ships) {
        	s.update(lastUpdatedAt);
        }
	}
	
	public void createPlanets(int x) {

	}
}