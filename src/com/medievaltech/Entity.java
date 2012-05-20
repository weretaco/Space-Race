package com.medievaltech;

import android.graphics.Canvas;

public interface Entity {
	public void draw(Canvas c);
	public void update(long lastUpdatedAt);
}
