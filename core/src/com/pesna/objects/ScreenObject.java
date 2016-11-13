package com.pesna.objects;

import com.pesna.Main;

public abstract class ScreenObject {
	boolean isVisible, isInteractive;
	
	public abstract void draw( Main _reference );
	public abstract void update( Main _reference );

}
