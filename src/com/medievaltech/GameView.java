package com.medievaltech;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.medievaltech.utils.*;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	/** Handle to the surface manager object we interact with */
	private SurfaceHolder mSurfaceHolder;
	
	/** The thread that actually updates and draws the graphics */
	private GameThread thread;
	
	public class GameThread extends Thread {
		
		public boolean suspended;
		
		public GameThread() {
			suspended = false;
		}
		
		/**
		 * The actual game loop!
		 */
		@Override
		public void run() {
			GameActivity activity = (GameActivity)(GameView.this.getContext()); 
			
			while (!activity.suspended) {
				Canvas c = null;
				try {
					synchronized(this) {
						while (suspended)
							wait();
					}
					
					c = mSurfaceHolder.lockCanvas(null);
					if(c != null) {
						synchronized (mSurfaceHolder) {
							runFrame(c);
						}
					}
				}catch (InterruptedException e) {
				}finally {
					if (c != null) {
						mSurfaceHolder.unlockCanvasAndPost(c);
					}
				}
			}
		}
	}
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		// register our interest in hearing about changes to our surface
		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
		
		thread = null;
	}
	
	protected void runFrame(Canvas c) {
	}
	
	/**Callback invoked when the Surface has been created and is ready to be
	 * used.
	 */
	public void surfaceCreated(SurfaceHolder holder) {
		Log.i(Settings.APP_NAME, this.getClass().getName()+".surfaceCreated");
		
		if(thread != null)
			thread.interrupt();
		thread = new GameThread();
		thread.start();
	}
	
	/**
	 * Callback invoked when the surface dimensions change.� 
	 */
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
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
		while (retry) {
			try {
				if(thread != null)
					thread.join();
				retry = false;
			} catch (InterruptedException e) {
			
			}
		}
	}
}
