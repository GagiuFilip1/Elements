package com.pesna.init;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.pesna.Main;

public class AnimationManager {
	
	class AnimationLoader{
		public String filename;
		public String atlas;
		public int frames;
		public float speed;
		public AnimationLoader( String _filename, String _atlas, int _frames, float _speed)
		{ filename = _filename; frames = _frames; atlas = _atlas; speed = _speed;}
		static final String subpath = "animations/";
	}
	
	public ArrayList<AnimationLoader> loaders = new ArrayList<AnimationLoader>();
	public HashMap <String,Animation> animations = new HashMap<String,Animation>();

	public Animation stay,attack,fall,walk;
	//public Animation def;
	
	public AnimationManager(Main mainClass)
	{
		this.init();
		this.registerTextures( mainClass.assetManager );
	}
	
	private void init()
	{
		/*
		Pixmap pixmap = new Pixmap(256,128, Pixmap.Format.RGBA8888);
        
        //Fill it red
        pixmap.setColor(Color.RED);
        pixmap.fill();
        
        //Draw two lines forming an X
        pixmap.setColor(Color.BLACK);
        pixmap.drawLine(0, 0, pixmap.getWidth()-1, pixmap.getHeight()-1);
        pixmap.drawLine(0, pixmap.getHeight()-1, pixmap.getWidth()-1, 0);
        
        //Draw a circle about the middle
        pixmap.setColor(Color.YELLOW);
        pixmap.drawCircle(pixmap.getWidth()/2, pixmap.getHeight()/2, pixmap.getHeight()/2 - 1);
        
        Texture tex = new Texture(pixmap);
        
        TextureRegion region = new TextureRegion(tex);
        
        def = new Animation ( 1f, region );
		
		*/
		//AnimationLoader loader = new AnimationLoader ( attack, "attack", 1, 1f, "player" );
		//loaders.add(loader);
		loaders.add( new AnimationLoader ( "attack", "player", 3, 0.2f) );
		loaders.add( new AnimationLoader ( "fall", "player", 4, 1f ) );
		loaders.add( new AnimationLoader ( "walk", "player", 5, 0.125f ) );
		loaders.add( new AnimationLoader ( "stay", "player", 1, 1f ) );
	}
	
	private void registerTextures( AssetManager assetManager )
	{
		assetManager.load( "animations/player.pack", TextureAtlas.class );
	}
	
	public void assignTextures ( AssetManager assetManager )
	{
		for ( AnimationLoader loader : loaders )
		{
			int frames = loader.frames;
			TextureAtlas atlas = assetManager.get( AnimationLoader.subpath + loader.atlas + ".pack", TextureAtlas.class );
			
			AtlasRegion[] regions = new AtlasRegion[frames];
			
			for ( int i = 0; i < frames; i++ )
				regions[i] = atlas.findRegion( loader.filename + Integer.toString(i) ); //attack0, attack4
			
			animations.put( loader.filename, new Animation( loader.speed, regions ) );
			//System.out.println( "Loaded : " + loader.filename + " " + regions.length);
		}
		this.registerVariables();
	}
	
	private void registerVariables()
	{
		stay = animations.get( "stay" );
		attack = animations.get( "attack" );
		fall = animations.get( "fall" );
		walk = animations.get( "walk" );
	}
}
