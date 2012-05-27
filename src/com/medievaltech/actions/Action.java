package com.medievaltech.actions;

import java.util.HashMap;

public abstract class Action extends Thread{
	abstract public void perform(HashMap<String,Object> args);
	
	public void run(HashMap<String,Object> args) {
		perform(args);
	}
}
