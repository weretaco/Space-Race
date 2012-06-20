package com.medievaltech.triggers;

import java.util.ArrayList;
import java.util.Hashtable;

import com.medievaltech.actions.Action;
import com.medievaltech.utils.Utils;
import com.medievaltech.actions.*;

public class ShipTrigger extends Trigger  {

	public ShipTrigger(Hashtable<String, Object> triggerHelper) {
		super(getAssociatedActions(triggerHelper), triggerHelper);
	}

	public static ArrayList<Action> getAssociatedActions(Hashtable<String, Object> actionHelper) {
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new SendShipAction(actionHelper));
		return actions;
	}
	
	protected boolean shouldActivate() {
		int r = Utils.randomInt(100) + 1;
		return r <= (Double.parseDouble(this.triggerHelper.get("randomShipChance").toString()))*100;
	}
}
