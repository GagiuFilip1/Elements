package com.pesna.init;

import com.badlogic.gdx.assets.AssetManager;
import com.pesna.Main;

public class GameRegistry {
	private final Main reference;
	public ItemManager itemManager;
	public AnimationManager animationManager;
	public LevelManager levelManager;
	
	public GameRegistry( Main _reference )
	{
		reference = _reference;
		itemManager = new ItemManager( reference );
		animationManager = new AnimationManager( reference );
		levelManager = new LevelManager( reference );
	}
	
	public void onAssetsLoaded( AssetManager assetManager )
	{
		itemManager.assignTextures( assetManager ); // assign textures to each item
		animationManager.assignTextures( assetManager );
		levelManager.assignTextures( assetManager );
	}
	
	
}
