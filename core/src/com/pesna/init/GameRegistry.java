package com.pesna.init;

import com.badlogic.gdx.graphics.Texture;
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
		
		
		
		reference.assetManager.load("ItemsSprites/rock.png", Texture.class );
	}
	
	public void onAssetsLoaded( Main reference )
	{
		//Replace those assignTextures with the constructor.. for some cases TODO
		itemManager.assignTextures( reference.assetManager ); // assign textures to each item
		animationManager.assignTextures( reference.assetManager );
		levelManager.assignTextures( reference.assetManager );
		
		//At last
		reference.screenManager.onAssetsLoaded( reference );
	}
	
	
}
