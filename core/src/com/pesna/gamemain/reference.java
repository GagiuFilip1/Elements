package com.pesna.gamemain;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.pesna.abstracts.AIObjects;
import com.pesna.abstracts.ObjectsInGame;
import com.pesna.environmentbuild.LevelRender;
import com.pesna.gameobjects.Player;
import com.pesna.gameobjects.AI_Objects.AEntity1;
import com.pesna.gameobjects.AI_Objects.AI_Logic;

public class reference extends ApplicationAdapter {
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Player player;
	private Game Run;
	private Control KeyBindings;
	private AI_Logic AI_log = new AI_Logic();
	private LevelRender Render;
    private ArrayList<ObjectsInGame> objectsList = new ArrayList<ObjectsInGame>();
    private ArrayList<AIObjects> enemyList = new ArrayList<AIObjects>();
    boolean jumpid =  true , loaded = false , stuned = false ;
    public int playerPosition , animationID = 0, enemyAnimationId =0 , isInRange = 0 , attackPos = 0;
    Vector3 touchPoint=new Vector3();  
    private BitmapFont font;
    public void AI_Placer(int x)
	{
		
		int H =0 , W = 0 , alg = 0 , width = 0, height = 0; boolean stop = false;
		
		String path = "LevelStructure/Level" +x;
		
		///GET BORDERS
		FileHandle file = Gdx.files.internal(path);
		StringTokenizer tokens = new StringTokenizer(file.readString());
		while(tokens.hasMoreTokens())
		{
			String type = tokens.nextToken();			
			
			if(stop == false)
			{
				W++;
			}
			if(type.equals("|"))
			{
				
				H++;	stop= true;
			}	
		}		
		///BUILD AI'S 
		int [][] AI_Places = new int [H][W];
		String[] Elements = new String[H*W];			
		StringTokenizer tokens2 = new StringTokenizer(file.readString());		
		while(tokens2.hasMoreTokens())
		{	
			for(int i = 0 ;i < H *W ;i++)
			{
				String type = tokens2.nextToken();
				Elements[i] = type;
			}
			for(int i = 0 ;i < H ;i++)
			{
				for(int j = 0;j < W-1;j++)
				{						
						if(!Elements[alg].equals("|"))
						{
							AI_Places[i][j] = Integer.parseInt(Elements[alg]);
						}
					alg++;							
					} 					
				alg++;
			}
		}
		
		for(int i = 0 ;i < H;i++)
		{
			for(int j = 0;j <= W -1;j++)
			{
				if(AI_Places[i][j] == 3)
				{
					enemyList.add(new AEntity1(width , height));
				}
				width += 180;
			}
			width = 0;
			height += 60;
		}
		for(AIObjects t : enemyList )
		{
		t.Draw(batch);
		}		
	}		
	///
	///Drawing
	///
	@Override
	public void create () {
		batch = new  SpriteBatch();
		Render = new LevelRender();
		KeyBindings = new Control();
		Run = new Game();
		//Render.SetBackground(batch);
		//SetBackground(batch);
		player = new Player(0,100);
		camera = new OrthographicCamera();		camera.setToOrtho(false,1920,1080);
		AI_Placer(1);
		Render.SetBackground(batch, player);
		
        font = new BitmapFont();
        font.setColor(Color.RED);
	}
	
	@Override
	public void resize (int width, int height) {
		camera.setToOrtho(false,width,height);
	}
	
	@Override
	public void dispose () 
	{
		batch.dispose();		
	}
	
	public void drawSprites()
	{
	
		batch.begin();
		//batch.setColor(1, 1, 0, 1);
		Render = new LevelRender();
		batch.draw( LevelRender.background , player.GetPosition(1) - 683 , 0 );	
		player.AnimationDraw(batch, (int)player.GetPosition(1), (int)player.GetPosition(2) , KeyBindings.AnimationID());
		objectsList = Render.GetObjectsList();
		Render.GetLevelData(1);
		Render.Build(batch);	
		
		
		
		font.draw(batch, "Hello World", Gdx.graphics.getWidth()/2,  Gdx.graphics.getHeight()/2);
		batch.end();
		loaded = true;
		
	}
	
	@Override
	public void render () {
	   
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		drawSprites();
		Run.RunGameLogic(player, KeyBindings, AI_log, objectsList, enemyList, batch, camera);	
		player.CreateHealthBar(camera , (int)player.GetPosition(1) - 680 , 745);
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
		{
			player.Jump();
		}				
	}	
}
