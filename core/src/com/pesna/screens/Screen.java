package com.pesna.screens;

import java.util.Iterator;

import com.badlogic.gdx.utils.Array;
import com.pesna.Main;
import com.pesna.objects.ScreenObject;

public class Screen implements IScreen {
	private Array<ScreenObject> objects = new Array<ScreenObject>();
	
	public void draw( Main _reference )
	{
		Iterator<ScreenObject> it = objects.iterator();
		while ( it.hasNext() )
		{
			it.next().draw( _reference );
		}
		it = null;
	}
	
	public void update( Main _reference )
	{
		Iterator<ScreenObject> it = objects.iterator();
		while ( it.hasNext() )
		{
			it.next().update( _reference );
		}
		it = null;
	}
	
	//AssetManager
}
