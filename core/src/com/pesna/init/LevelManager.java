package com.pesna.init;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.pesna.Main;

public class LevelManager {
	public Texture platform,background;
	
	public LevelManager ( Main _reference )
	{
		//Load map + parallaxing factors + loops... bla bla bla
		this.registerTextures( _reference.assetManager );
	}
	
	private void registerTextures( AssetManager assetManager )
	{
		assetManager.load("level/platform.png", Texture.class);
		assetManager.load("level/bckgr.png", Texture.class);
	}
	
	public void assignTextures( AssetManager assetManager )
	{
		platform = assetManager.get("level/platform.png");
		background = assetManager.get("level/bckgr.png");
	}
}