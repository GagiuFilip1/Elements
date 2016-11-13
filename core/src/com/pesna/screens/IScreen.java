package com.pesna.screens;

import com.pesna.Main;

public interface IScreen {
    /**
     * Called in the screen manager ( via main loop ) for rendering.
     */
	public void draw( Main _reference );
	
    /**
     * Called in the screen manager ( via main loop ) for update.
     */
	public void update( Main _reference );
}
