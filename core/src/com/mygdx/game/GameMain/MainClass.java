package com.mygdx.game.GameMain;
import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Abstracts.AIObjects;
import com.mygdx.game.Abstracts.ObjectsInGame;
import com.mygdx.game.Abstracts.PasiveNpc;
import com.mygdx.game.GUI.DialogueInterface;
import com.mygdx.game.GUI.GUI_Handler;
import com.mygdx.game.GameObjects.Player;
import com.mygdx.game.EnvironmentBuild.LevelRender;

/**
 * Created by Filip
 */
public class MainClass extends ApplicationAdapter {

	 public SpriteBatch batch;
     public OrthographicCamera camera;
	 Player player;
	 private Logic Run;
	 private Control KeyBindings;
	 private LevelRender Render;
	 private GUI_Handler GUI;
	private DialogueInterface Inter;
	 ArrayList<ObjectsInGame> objectsList = new ArrayList<ObjectsInGame>();
	 ArrayList<AIObjects> enemyList = new ArrayList<AIObjects>();
     ArrayList<PasiveNpc> pasiveList = new ArrayList<PasiveNpc>();
	@Override
	public void create ()
	{
		Inter = new DialogueInterface();
		GUI = new GUI_Handler();
		batch = new  SpriteBatch();
		Render = new LevelRender();         Render.GetLevelData(1);     Render.SetEnemy(batch, enemyList);
        Render.SetPasives(batch, pasiveList);
		objectsList = Render.GetObjectsList();
		KeyBindings = new Control();
		Run = new Logic();
		player = new Player(0,100);
		camera = new OrthographicCamera();		camera.setToOrtho(false,1366,768);
	}
	@Override
	public void dispose ()
	{
		batch.dispose();
	}
	private void drawSprites()
	{
		batch.begin();
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
		Run.RunGameLogic(this, KeyBindings);
		player.CreateHealthBar(camera , (int)player.GetPosition(1) - 680 , 745);
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
		{
			player.Jump();
		}
		GUI.Dialog();
      //  System.out.print("---------------- " + Inter.Destroy()+"\n");

	}
}