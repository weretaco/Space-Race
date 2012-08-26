package com.medievaltech;

import android.app.Activity;
import android.util.Log;
import com.medievaltech.utils.Settings;

public class GameActivity extends Activity {
	public boolean suspended;
	
	// maybe make a constructor here that takes a layoutResID
	
	@Override
    protected void onPause() {
    	super.onPause();
    	
    	Log.i(Settings.APP_NAME, "Trying to pause "+this.getClass().getName());
    	
    	suspended = true;
    		
    	Log.i(Settings.APP_NAME, "Paused "+this.getClass().getName());
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	Log.i(Settings.APP_NAME, "Trying to resume "+this.getClass().getName());
    	
    	suspended = false;
    	
    	Log.i(Settings.APP_NAME, "Resumed "+this.getClass().getName());
    }
}
