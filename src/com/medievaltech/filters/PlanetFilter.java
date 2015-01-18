package com.medievaltech.filters;

import com.medievaltech.filters.Filter.Comparator;
import com.medievaltech.models.Planet;

public class PlanetFilter extends Filter<Planet> {

	enum Attribute {
		RADIUS,
		DOCKED_SHIPS
	}
	
	protected Attribute attr;
	protected int value;
	
	public PlanetFilter(Attribute attr, Filter.Comparator comp, int value) {
		super();

		this.attr = attr;
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public boolean matches(Planet p) {
		int object_value = 0;
		boolean matches = false;

		switch(attr)
		{
		case RADIUS:
			object_value = p.radius;
			break;
		case DOCKED_SHIPS:
			object_value = p.getNumShips();
			break;
		default:
			return false;
		}
		
		switch(comp)
		{
		case EQUAL:
			matches = (object_value == value);
			break;
		case GREATER_THAN:
			matches = (object_value > value);
			break;
		case LESS_THAN:
			matches = (object_value < value);
			break;
		case GREATER_THAN_OR_EQUAL:
			matches = (object_value >= value);
			break;
		case LESS_THAN_OR_EQUAL:
			matches = (object_value <= value);
			break;
		}
		
		return matches;
	}

}
