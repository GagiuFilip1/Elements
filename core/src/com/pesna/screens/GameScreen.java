package com.pesna.screens;

import java.util.ArrayList;

import com.pesna.Main;
import com.pesna.entities.EnemyObject;
import com.pesna.gui.GuiHealthbar;
import com.pesna.gui.GuiObject;
import com.pesna.objects.LevelRenderer;
import com.pesna.objects.ScreenObject;

public class GameScreen implements IScreen {
	public ArrayList<ScreenObject> objects = new ArrayList<ScreenObject>();
	public ArrayList<GuiObject> guiObjects = new ArrayList<GuiObject>();
	
	//TODO : modify the class so the ProjectionMatrix is set only twice per rendering cycle
	// once in the player's class update
	// and once in the draw() function of this class, set it to the camera.projection matrix.
	
	public GuiObject healthBar;
	public ScreenObject levelRenderer, player;
	
	public GameScreen( Main _reference )
	{
		healthBar = new GuiHealthbar();
		player = _reference.player;
		levelRenderer = new LevelRenderer( _reference );
		
		guiObjects.add( healthBar );
		objects.add(levelRenderer);
		objects.add(player);
		
		objects.add( new EnemyObject ( _reference, 300, 0 ) );
	}
	
	public void onAssetsLoaded()
	{
		((LevelRenderer)levelRenderer).onAssetsLoaded();
	}
	
	public void update( Main _reference )
	{
		//TODO : Which is the most optimized way to cycle in an ArrayList?
		for ( ScreenObject object : objects )
		{
			object.update( _reference );
		}
		for ( GuiObject object : guiObjects )
		{
			object.update( _reference );
		}
	}
	
	public void draw( Main _reference )
	{	
		//Projection Matrixes are set in the "Player" class, in void update(), because the camera is watching it.
		
		//TODO : Which is the most optimized way to cycle in an ArrayList?
		//_reference.shapeRenderer.setProjectionMatrix(_reference.camera.combined);
		
		//TODO add a _reference.batch.begin() in here? so you don't have to begin() it for every object?
		//Leave those todo's to Tony
		
		for ( ScreenObject object : objects )
		{
			object.draw( _reference );
		}
		
		_reference.shapeRenderer.setProjectionMatrix(_reference.camera.projection);
		_reference.shapeRenderer.setProjectionMatrix(_reference.camera.projection);
		for ( GuiObject object : guiObjects )
		{
			object.draw( _reference );
		}
	}
}
