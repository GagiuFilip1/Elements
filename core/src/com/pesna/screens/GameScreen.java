package com.pesna.screens;

import java.util.ArrayList;

import com.pesna.Main;
import com.pesna.gui.GuiHealthbar;
import com.pesna.gui.GuiObject;
import com.pesna.objects.ScreenObject;

public class GameScreen implements IScreen {
	public ArrayList<ScreenObject> objects = new ArrayList<ScreenObject>();
	public ArrayList<GuiObject> guiObjects = new ArrayList<GuiObject>();
	
	public GuiObject healthBar;
	
	public GameScreen()
	{
		healthBar = new GuiHealthbar();
		guiObjects.add( healthBar );
	}
	
	public void draw( Main _reference )
	{
		//TODO : Which is the most optimized way to cycle in an ArrayList?
		for ( ScreenObject object : objects )
		{
			object.draw( _reference );
		}
		for ( GuiObject object : guiObjects )
		{
			object.draw( _reference );
		}
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

}
