package com.pesna.gui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.pesna.Main;

public class GuiHealthbar extends GuiObject{
	
	//Here you get the function that gets called each frame
	public void draw( Main _reference )
	{
		_reference.shapeRenderer.begin(ShapeType.Filled);
		
		Vector3 pos = _reference.camera.position;
		
		_reference.shapeRenderer.setColor( 0.9f, 0.8f, 0.2f, 1f);
		_reference.shapeRenderer.rect( 0, 30, 100, 100 );
		
		_reference.shapeRenderer.setColor( 0.5f, 0.8f, 0.2f, 1f);
		_reference.shapeRenderer.rect( pos.x/2, 30, 50, 50 );
		

		_reference.shapeRenderer.end();
	}
	public void update( Main _reference )
	{
		
	}
}
