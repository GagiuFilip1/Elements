package com.pesna.screens;

import com.pesna.Main;
import com.pesna.abstracts.SpellObject;
import com.pesna.objects.ScreenObject;

public interface IScreen {
    /**
     * Called in the screen manager ( via main loop ) for rendering.
     */
	 void draw( Main _reference );
	
    /**
     * Called in the screen manager ( via main loop ) for update.
     */
	 void update( Main _reference );

     void ForceAdd(ScreenObject newObject);

}
