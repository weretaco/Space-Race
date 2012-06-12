package com.medievaltech.triggers;

import java.util.ArrayList;
import java.util.Hashtable;

import com.medievaltech.actions.Action;
import com.medievaltech.utils.Utils;

public class ShipTrigger extends Trigger  {

	public ShipTrigger(ArrayList<Action> actions, Hashtable<String, Object> triggerHelper) {
		super(actions, triggerHelper);
	}

	protected boolean shouldActivate() {
		int r = Utils.randomInt(100) + 1;
		return r < (Double.parseDouble(this.triggerHelper.get("randomShipChance").toString()))*100;
	}
}