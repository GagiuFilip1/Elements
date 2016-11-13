package com.pesna.environmentbuild;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pesna.abstracts.AIObjects;
import com.pesna.abstracts.ObjectsInGame;
import com.pesna.gameobjects.Brick;
import com.pesna.gameobjects.Player;
import com.pesna.gameobjects.AI_Objects.AEntity1;

public class LevelRender 
{
	private int H , W , alg = 0;
	private int[][] Map;
	private ArrayList<ObjectsInGame> objectsList = new ArrayList<ObjectsInGame>();
	private ArrayList<AIObjects> enemyList = new ArrayList<AIObjects>();
	private String[] Elements;
	private boolean stop = false;
	public static Sprite background;
	public void GetLevelData(int level)
	{
			GetLevelBorders();
			Map = new int[H][W];
			Elements=  new String[H *W];
			FileHandle file = Gdx.files.internal(ChooseLevel(level));
			StringTokenizer tokens = new StringTokenizer(file.readString());
			
			while(tokens.hasMoreTokens())
			{
				for(int i = 0 ;i < H *W ;i++)
				{
					String type = tokens.nextToken();
					Elements[i] = type;
				}
				for(int i = 0 ;i < H ;i++)
				{
					for(int j = 0;j < W-1;j++)
					{						
							if(!Elements[alg].equals("|"))
							{
								Map[i][j] = Integer.parseInt(Elements[alg]);
							}
						alg++;							
						} 					
					alg++;
				}
			}			
	}
	public void Build(SpriteBatch batch)
	{
		int width = -1000 , height = 0;
			for(int i = 0 ;i < H;i++)
			{
				for(int j = 0;j <= W-1;j++)
				{
					if(Map[i][j] == 1)
					{
						objectsList.add(new Brick(width, height));
					}
					width += 180;
				}
				width = 0;
				height += 64;
			}
			width = 0;height = 0;
			for(ObjectsInGame t : objectsList )
				{
				t.Draw(batch);
				}
			
			//batch.draw(region,0,0);
	}
	public void Enteties(SpriteBatch batch)
	{
		
		int width = 0 , height = 0;
		for(int i = 0 ;i < H;i++)
		{
			for(int j = 0;j <= W-1;j++)
			{
				if(Map[i][j] == 3)
				{					
					enemyList.add(new AEntity1(width, height));
				}
				width += 180;
			}
			width = 0;
			height += 64;
		}
		for(AIObjects t : enemyList )
			{			
			t.Draw(batch);
			}
	}
	private String ChooseLevel(int x)
	{
		String path = "LevelStructure/Level" +x;
		return path;
	}
	
	private void GetLevelBorders()
	{
		FileHandle file = Gdx.files.internal("LevelStructure/Level1");
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
	}
	public ArrayList<ObjectsInGame> GetObjectsList()
	{
		return objectsList;
	}
	public ArrayList<AIObjects> GetEnemyList()
	{	
		return enemyList;
	}
	
	public void SetBackground(SpriteBatch batch, Player player)
	{
		Texture texture = new Texture(Gdx.files.internal("Backgrounds/1.png"));
		background = new Sprite(texture);
		background.setPosition(player.GetPosition(1)-683, 0);
	}
	
	
	
	/*
	//ScreenManager
	//Screen
	//GuiHandler
	//IGuiObject -> Obiecte GUI
	//
	// Dialogues
	// assets/dialogues/0.txt ; assets/dialogues/1.txt; assets/dialogues/2.txt
	// 
	//
	//
	 * ResourceManager
	 * /\
	 * /\
	 * Loading Screen
	 * 
	 * Main Menu
	 * Butoane
	 * GUI Elements - Buttons, Labels, ...
	 * 
	 * 
	 * Animation, AnimationProvider
	 * LogHelper, - log files..
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
}
