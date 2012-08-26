package com.medievaltech;

import android.view.MotionEvent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.medievaltech.utils.*;

public class SpaceActivity extends GameActivity {

	private static final int REQUEST_CODE = 10;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == 1) {	// up action
			Intent i = new Intent(this, ShipDesignerActivity.class);
			i.putExtra("Value1", "This value one for ActivityTwo ");
			i.putExtra("Value2", "This value two for ActivityTwo");
			
			// Set the request code to any code you like, you can identify the
			// callback via this code
			startActivityForResult(i, REQUEST_CODE);
			
			return true;
		}
		else
			return false;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		Log.d("SpaceRace","onActivityResult and resultCode = "+resultCode);
	}	
}
