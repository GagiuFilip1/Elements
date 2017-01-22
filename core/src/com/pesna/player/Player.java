package com.pesna.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.pesna.Main;
import com.pesna.objects.ScreenObject;

public class Player implements ScreenObject {
	boolean isVisible, isInteractive;
	
	public int x,y,hp , range;
	float delta;
	boolean flip;
	public Animation animation;
	Main reference;
	
	public final float gravity = 0.9f , speed = 300;
	private float vspeed = 0;
	
	public Player( Main _reference )
	{
		reference = _reference;
		x = 0;
		y = 0;
		delta = 0;
		flip = false;
		hp = 100;
		range = 800;
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
		animation = reference.gameRegistry.animationManager.hstay;
		
		if ( Gdx.input.isKeyPressed(Keys.A))
		{
			x -= speed * Gdx.graphics.getDeltaTime();
			flip = true;
			animation = reference.gameRegistry.animationManager.hwalk;
		}

		else if ( Gdx.input.isKeyPressed(Keys.D))
		{
			x += speed * Gdx.graphics.getDeltaTime();
			flip = false;
			animation = reference.gameRegistry.animationManager.hwalk;
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
	
	public void TakeDamage( int damage )
	{
		hp -= damage;
		//In case the player dies..
		/*
		 * if hp < 0 then
		 *  player.dies()
		 * end
		 * 
		 */
	}
}
















/* Code Cleaning :
 * -Implement a global way to call onAssetsLoaded
 * -LogHelper -> Creates log files and logs debug data into console
 * 
 * -Reformat the LoadingScreen + GameRegistry :
 * The loadingScreen will be instatied independently from the screen manager constructor
 * gameRegistry will ask the assetManager to .load() all the textures needed in the game
 * the loadingScreen will load everything needed in the AssetManager then
 * proceed to the next step -> create gamescreens, items, bla bla bla
 * everything that needs a texture
 * 
 * GUIHandler in the ScreenManager ( InputProcessorMultiplexer )
 * 
 * GUI objects:
 * -HealthMeter / HealthBar / HealthIndicator
 * -CoinsIndicator
 * -GUIInventory
 * -PauseMenu
 * -Buttons
 * -Labels ( child of button )
 * -GUIDialogue
 * -Stats? .. combine it with inventory?
 * 
 * Visuals :
 * -Day&Night ? ( Need GLSL's for this one )
 * -Cutscene animations ( is the designer busy? )
 * -16:9 film cutting black bars?
 * 
 * 
 * Sounds:
 * -Attack sounds ( Player + Monsters )
 * -Narated Dialogue?
 * 
 * Logic :
 * -SaveHandler / SaveManager ( MTA:SA style? )
 * -Obstacles / Pickups / Interiors??
 * 
 * MainMenu
 * -Play -> screenManager.queueScreen(screenManager.gameScreen);
 * -Options -> Difficulty? | SFX / Music enabled | Day&Night Cycle |
 * -Credits -> This is just sexy to think about.
 * -Exit
 * 
 * 
 * 
 */
