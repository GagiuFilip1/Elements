package com.pesna.objects;

import com.pesna.Main;
import com.pesna.init.LevelManager;
import com.pesna.player.Player;

public class LevelRenderer extends ScreenObject {
	Player player;
	LevelManager levelManager;
	ParallaxLoop platformLoop,backgroundLoop;
	
	public LevelRenderer( Main _reference )
	{
		levelManager = _reference.gameRegistry.levelManager;
	}
	
	public void onAssetsLoaded()
	{
		platformLoop = new ParallaxLoop(0,-levelManager.platform.getHeight(),0f,levelManager.platform);
		backgroundLoop = new ParallaxLoop(0,0,0.9f,levelManager.background);
	}
	
	public void draw( Main _reference )
	{
		_reference.batch.begin();
		platformLoop.draw(_reference.camera.position, _reference.batch);
		backgroundLoop.draw(_reference.camera.position, _reference.batch);
		_reference.batch.end();
	}
	public void update( Main _reference )
	{
		
	}
}
