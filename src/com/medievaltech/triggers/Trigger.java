package com.medievaltech.triggers;

import com.medievaltech.actions.Action;
import java.util.ArrayList;
import java.util.Hashtable;

abstract public class Trigger {
	protected ArrayList<Action> actions;
	protected Hashtable<String,Object> triggerHelper;
	
	public Trigger(ArrayList<Action> actions, Hashtable<String,Object> triggerHelper) {
		this.actions = actions;
		this.triggerHelper = triggerHelper;
	}
	
	public void trigger()
	{
		if(shouldActivate())
			activate();
	}
	
	protected void activate() {
		for(Action action : this.actions) {
			action.run();
		}
	}
	
	abstract protected boolean shouldActivate();
}
