package com.medievaltech.actions;

import java.util.Hashtable;

import com.medievaltech.models.Map;
import com.medievaltech.models.Planet;
import com.medievaltech.models.Ship;

public class SendShipAction extends Action {

	public SendShipAction(Hashtable<String, Object> actionHelpers) {
		super(actionHelpers);
	}
	
	public void run() {
		Map map = ((Map)this.actionHelpers.get("map"));
		Ship ship = map.getRandomDockedShip();
		Planet destination = null;

		destination = map.getRandomPlanetWithoutShip(ship);

		if(destination == null)
			return;
		else
			ship.flyTo(destination);
	}
}
