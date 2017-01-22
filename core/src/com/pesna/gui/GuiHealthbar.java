package com.pesna.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.pesna.Main;

public class GuiHealthbar extends GuiObject{
	
	//Here you get the function that gets called each frame
	public void draw( Main _reference )
	{
		_reference.shapeRenderer.begin(ShapeType.Filled);
		
		//Vector3 pos = _reference.camera.position;
		
		
		_reference.shapeRenderer.setColor( 0.9f, 0.8f, 0.2f, 1f);
		//_reference.shapeRenderer.rect( 0, 0, 100, 10 );
		
		
		_reference.shapeRenderer.setColor( 0.5f, 0.8f, 0.2f, 1f);
		//_reference.shapeRenderer.rect( pos.x - Gdx.graphics.getWidth()/2, 0, 50, 50 );
		_reference.shapeRenderer.rect( -Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, 50, 50 );
		
		_reference.shapeRenderer.end();
		//_reference.shapeRenderer.setProjectionMatrix(_reference.camera.combined);
	}
	public void update( Main _reference )
	{
		
	}
}
