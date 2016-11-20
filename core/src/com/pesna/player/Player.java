package com.pesna.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.pesna.Main;
import com.pesna.objects.ScreenObject;

public class Player extends ScreenObject {
	boolean isVisible, isInteractive;
	
	public int x,y;
	float delta;
	boolean flip;
	Animation animation;
	Main reference;
	
	private final float gravity = 0.9f;
	private float vspeed = 0;
	
	public Player( Main _reference )
	{
		reference = _reference;
		x = 0;
		y = 0;
		delta = 0;
		flip = false;
	}
	
	public void update( Main _reference )
	{
		//Control
		control();
		
		//Make the camera chase the player.
		//This shouldn't care much about the order ScreenObjects get updated in the ArrayList
		//Because update works before drawing.
		moveCamera( _reference );
	}
	
	private void control()
	{
		animation = reference.gameRegistry.animationManager.stay;
		
		if ( Gdx.input.isKeyPressed(Keys.A))
		{
			x -= 3;
			flip = true;
			animation = reference.gameRegistry.animationManager.walk;
		}
		else if ( Gdx.input.isKeyPressed(Keys.D))
		{
			x += 3;
			flip = false;
			animation = reference.gameRegistry.animationManager.walk;
		}
		
		
		if ( Gdx.input.isKeyJustPressed(Keys.SPACE) && y == 0 )
		{
			vspeed = +15;
		}
		
		if ( y+vspeed >= 0 )
		{
			y+=vspeed;
			vspeed = vspeed-gravity;
		}
		else
		{
			y=0;
		}
	}
	
	private void moveCamera( Main _reference )
	{
		int height = _reference.gameRegistry.levelManager.platform.getHeight();
		
		Vector3 pos = _reference.camera.position;
		_reference.camera.translate( x-pos.x, -pos.y+(-height+Gdx.graphics.getHeight()/2) );
		_reference.camera.update();
		_reference.batch.setProjectionMatrix(_reference.camera.combined);
		_reference.shapeRenderer.setProjectionMatrix(_reference.camera.combined);
	}
	
	public void draw( Main _reference )
	{
		SpriteBatch batch = _reference.batch;

		delta+= Gdx.graphics.getDeltaTime();
		TextureRegion keyFrame = animation.getKeyFrame(delta,true);
		batch.begin();
		
		if ( flip )
			batch.draw( keyFrame, x+keyFrame.getRegionWidth()/2, y, -keyFrame.getRegionWidth(), keyFrame.getRegionHeight());
		else
			batch.draw( keyFrame, x-keyFrame.getRegionWidth()/2, y, keyFrame.getRegionWidth(), keyFrame.getRegionHeight());
		//batch.draw( region, x, y);
		batch.end();
	}
}
/*
//GuiHandler
//IGuiObject -> Obiecte GUI
//
// Dialogues
// assets/dialogues/0.txt ; assets/dialogues/1.txt; assets/dialogues/2.txt
// 
 * 
 * Main Menu
 * Butoane
 * GUI Elements - Buttons, Labels, ...
 * 
 * LogHelper, - log files..
 * 
 * 
 * 
 * 
 * 
 */
