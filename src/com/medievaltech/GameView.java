package com.medievaltech;

import com.medievaltech.models.Map;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class GameView extends SurfaceView implements SurfaceHolder.Callback {

	/** Are we running ? */
    private boolean mRun;
    
    /** Used to figure out elapsed time between frames */
    private long mLastTime;
    
    /** Variables for the counter */
    private int frameSamplesCollected = 0;
    private int frameSampleTime = 0;
    private int fps = 0;
    
    private Map map;
    
    /** Handle to the surface manager object we interact with */
    private SurfaceHolder mSurfaceHolder;

    /** How to display the text */
    private Paint textPaint;
    
    /** The thread that actually draws the animation */
    private GameThread thread;
	
	class GameThread extends Thread {
        public GameThread() {
        }
 
        /**
         * The actual game loop!
         */
        @Override
        public void run() {      	
            while (mRun) {
                Canvas c = null;
                try {
                    c = mSurfaceHolder.lockCanvas(null);
                    if(c != null) {
	                    synchronized (mSurfaceHolder) {
	                        updatePhysics();
	                        doDraw(c);
	                    }
                    }
                }finally {
                    if (c != null) {
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
 
        /**
         * Figures the gamestate based on the passage of
         * realtime. Called at the start of draw().
         * Only calculates the FPS for now.
         */
        private void updatePhysics() {
            long now = System.currentTimeMillis();
            
            if (mLastTime != 0) {
            	
            	map.update(now);
 
                //Time difference between now and last time we were here
                int time = (int) (now - mLastTime);
                frameSampleTime += time;
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
 
            mLastTime = now;
        }
 
        /**
         * Draws to the provided Canvas.
         */
        private void doDraw(Canvas canvas) {
 
            // Draw the background color. Operations on the Canvas accumulate
            // so this is like clearing the screen. In a real game you can
            // put in a background image of course
            canvas.drawColor(Color.BLACK);
 
            map.draw(canvas);
            
            // Draw fps
            canvas.drawText(fps + " fps", getWidth() / 2, getHeight() / 2, textPaint);
            
            //Draw screen dimensions
            canvas.drawText(getWidth()+"x"+getHeight(), getWidth() / 2, getHeight() / 2 + 50, textPaint);
            
            canvas.restore();
        }
 
        /**
         * So we can stop/pauze the game loop
         */
        public void setRunning(boolean b) {
            mRun = b;
        }     
 
    }
 
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        // register our interest in hearing about changes to our surface
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        
        /** Initiate the text painter */
        textPaint = new Paint();
        textPaint.setARGB(255,255,255,255);
        textPaint.setTextSize(32);
        
        map = null;
        
        thread = null;
    }
 
    /**
     * Obligatory method that belong to the:implements SurfaceHolder.Callback
     */
 
    /**
     * Callback invoked when the surface dimensions change.� 
     */
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
 
    }
 
    /**
     * Callback invoked when the Surface has been created and is ready to be
     * used.
     */
    public void surfaceCreated(SurfaceHolder holder) {
    	if(map == null)
    		map = new Map(getWidth(), getHeight(), 9, 5);
    	if(thread != null)
    		thread.interrupt();
    	thread = new GameThread();
        thread.setRunning(true);
        thread.start();
    }
 
    /**
     * Callback invoked when the Surface has been destroyed and must no longer
     * be touched. WARNING: after this method returns, the Surface/Canvas must
     * never be touched again!
     */
    public void surfaceDestroyed(SurfaceHolder holder) {
        // we have to tell thread to shut down & wait for it to finish, or else
        // it might touch the Surface after we return and explode
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }
}
