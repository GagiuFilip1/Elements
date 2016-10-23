package com.mygdx.game.GameMain;
/**
 * Created by Filip
 */
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import com.mygdx.game.Abstracts.AIObjects;
import com.mygdx.game.Abstracts.ObjectsInGame;
import com.mygdx.game.GameObjects.Player;
import com.mygdx.game.GameObjects.AIObjects.Enemy1;
import com.mygdx.game.GameObjects.AIObjects.AI_Logic;
import com.mygdx.game.EnvironmentBuild.LevelRender;

public class MainClass extends ApplicationAdapter {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Player player;
	private Logic Run;
	private DialogueHandler Dialog;
	private Control KeyBindings;
	private AI_Logic AI_log = new AI_Logic();
	private LevelRender Render;
	private ArrayList<ObjectsInGame> objectsList = new ArrayList<ObjectsInGame>();
	private ArrayList<AIObjects> enemyList = new ArrayList<AIObjects>();
	private void AI_Placer(int x)
	{

		int H =0 , W = 0 , alg = 0 , width = 0, height = 0; boolean stop = false;

        String path = "desktop/assets/LevelStructure/Level" +x;

		///GET BORDERS
		FileHandle file = Gdx.files.internal(path);
		StringTokenizer tokens = new StringTokenizer(file.readString());
		while(tokens.hasMoreTokens())
		{
			String type = tokens.nextToken();
			if(!stop)
			{
				W++;
			}
			if(type.equals("|"))
			{

				H++;	stop = true;
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
					enemyList.add(new Enemy1(width , height));
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
	public void create ()
	{
		batch = new  SpriteBatch();
		Render = new LevelRender();
		Render.GetLevelData(1);
		Dialog = new DialogueHandler();
		Dialog.GetDialogueLines(3,8,11,12);
		Dialog.BuildDialogue("Hero" , "Npc1");
		objectsList = Render.GetObjectsList();
		KeyBindings = new Control();
		Run = new Logic();
		player = new Player(0,100);
		camera = new OrthographicCamera();		camera.setToOrtho(false,1366,768);
		AI_Placer(1);
	}
	@Override
	public void dispose ()
	{
		batch.dispose();
	}
	private void drawSprites()
	{

		batch.begin();

		Render = new LevelRender();
		Render.SetBackground(player);
		batch.draw(LevelRender.sprite,player.GetPosition(1) - 684,0);
		Render.Build(batch , objectsList);
		player.AnimationDraw(batch, (int)player.GetPosition(1), (int)player.GetPosition(2) , KeyBindings.AnimationID());
		batch.end();
	}
	@Override
	public void render () {

		Gdx.gl30.glClearColor(1, 1, 1, 1);
		Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
		drawSprites();
		Run.RunGameLogic(player, KeyBindings, AI_log, objectsList, enemyList, batch, camera);
		player.CreateHealthBar(camera , (int)player.GetPosition(1) - 680 , 745);
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
		{
			player.Jump();
		}
	}
}