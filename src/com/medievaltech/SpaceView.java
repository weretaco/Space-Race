package com.medievaltech;

import android.content.*;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;

import com.medievaltech.models.Map;
import com.medievaltech.utils.*;

class SpaceView extends GameView {
	
	/** Used to figure out elapsed time between frames */
	private long mLastUpdated;
	
	/** Variables for the counter */
	private int frameSamplesCollected = 0;
	private int frameSampleTime = 0;
	private int fps = 0;
	
	private Map map;
	
	/** How to display the text */
	private Paint textPaint;
	
	public SpaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		textPaint = new Paint();
		textPaint.setARGB(255,255,255,255);
		textPaint.setTextSize(32);
		
		// getWidth and getHeight both return 0 now, so we can't use them to initialize the map
		map = new Map(400, 600, 9, 5);
	}
	
	public void runFrame(Canvas c) {
		Log.i(Settings.APP_NAME, "Running inherited runFrame of "+getClass().getName());
		
		updatePhysics();
		doDraw(c);
	}
	
	private void updatePhysics() {
		long now = System.currentTimeMillis();
		long timeElapsed = now - mLastUpdated;
		mLastUpdated = now;
		
		map.update(timeElapsed);
		
		//Time difference between now and last time we were here
		frameSampleTime += timeElapsed;
		frameSamplesCollected++;
		
		//After 10 frames
		if (frameSamplesCollected == 10) {
			
			//Update the fps variable
			fps = (int) (10000 / frameSampleTime);
			
			//Reset the sampletime + frames collected
			frameSampleTime = 0;
			frameSamplesCollected = 0;
        }
	}
	
	private void doDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		
		// Draw fps
		canvas.drawText(fps + " fps", getWidth()-120, getHeight() - 32, textPaint);
		
		//Draw screen dimensions
		canvas.drawText(getWidth()+"x"+getHeight(), getWidth()-120, getHeight(), textPaint);
	
		map.draw(canvas);
	}
}
