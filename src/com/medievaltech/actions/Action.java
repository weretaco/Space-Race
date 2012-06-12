package com.medievaltech.actions;

import java.util.Hashtable;

public abstract class Action extends Thread{
	protected Hashtable<String, Object> actionHelpers;
	
	public Action(Hashtable<String, Object> actionHelpers) {
		this.actionHelpers = actionHelpers;
	}
	
	abstract public void run();
}
