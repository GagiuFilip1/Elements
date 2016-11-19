package com.pesna.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.pesna.Main;
import com.pesna.init.LevelManager;
import com.pesna.player.Player;
import com.pesna.screens.GameScreen;

//THIS CODE IS A DEBUGGING CODE - IT'S NOT SUPPOSED TO BE CLEAN.

public class LevelRenderer extends ScreenObject {
	Player player;
	
	public LevelRenderer( Main _reference )
	{
	}
	
	public void draw( Main _reference )
	{
		LevelManager levelManager = _reference.gameRegistry.levelManager;
		player = (Player)((GameScreen)_reference.screenManager.gameScreen).player;
		int p_width = _reference.gameRegistry.levelManager.platform.getWidth();
		int b_width = _reference.gameRegistry.levelManager.background.getWidth();

		int px = (player.x/p_width)*p_width;
		if ( player.x < 0 )
			px-=p_width;
		
		_reference.batch.begin();
		for ( int i = -2; i<=2; i++ ){
			_reference.batch.draw(
					levelManager.platform,
					
					px+i*p_width , 
					
					-levelManager.platform.getHeight() );
		}
		
		Vector3 pos = _reference.camera.position;
		int factor = (int)(pos.x*0.9);
		
		int bx = (player.x/b_width)*b_width;
		/*
		if ( player.x < 0 )
			bx-=b_width;
		*/
		
		if ( Math.abs(player.x-bx) > Math.abs(player.x-(bx+6*b_width) ) )
		{
			bx+=b_width;
		}
		else
		{
			bx-=b_width;
		}
		
		for ( int i = -5; i<6; i++ ){
			_reference.batch.draw(
					_reference.gameRegistry.levelManager.background,
					
					bx+i*b_width + factor , 
					
					0 );
		}
		_reference.batch.end();
		
		_reference.shapeRenderer.begin(ShapeType.Filled);
		_reference.shapeRenderer.setColor(0f, 0f, 0.9f, 0f);
		_reference.shapeRenderer.circle( bx, 0, 10f );
		_reference.shapeRenderer.circle( bx + b_width, 0, 10f );
		
		_reference.shapeRenderer.setColor(0.8f, 0.8f, 0f, 0f);
		_reference.shapeRenderer.circle( px, 0, 5f );
		_reference.shapeRenderer.circle( px + p_width, 0, 5f );
		
		_reference.shapeRenderer.end();
	}
	public void update( Main _reference )
	{
		
	}
}
