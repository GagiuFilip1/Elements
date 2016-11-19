package com.pesna.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.pesna.Main;

public class LoadingScreen implements IScreen {
	
	private final int lheight = 10; // 10 pixels for the line height
	private final float lborder = 0.1f; // the border will take 0.1f of the screen's width
	
	public void update( Main _reference )
	{
		if ( _reference.assetManager.update() )//If loading finishes
		{
			onLoaded( _reference );
		}
	}
	
	public void draw( Main _reference )
	{
		_reference.batch.setProjectionMatrix(_reference.camera.combined);
		_reference.shapeRenderer.setProjectionMatrix(_reference.camera.combined);
		
		float progress = _reference.assetManager.getProgress();
		int gwidth = Gdx.graphics.getWidth();
		int gheight = Gdx.graphics.getHeight();
		
		_reference.shapeRenderer.begin(ShapeType.Filled);
		_reference.shapeRenderer.setColor(35/255f, 200/255f, 0f, 1f);
		_reference.shapeRenderer.rect( (lborder*gwidth), (gheight-lheight)/2, progress*gwidth*(1f-2f*lborder), lheight );
		_reference.shapeRenderer.end();
		
	}
	
	/**
	 * Describes what should be done upon loading everything
	 * @param _reference Main Class Reference
	 */
	private void onLoaded( Main _reference )
	{
		_reference.gameRegistry.onAssetsLoaded( _reference.assetManager );
		//move to the next screen
		_reference.screenManager.queueScreen( _reference.screenManager.gameScreen );
	}
}
