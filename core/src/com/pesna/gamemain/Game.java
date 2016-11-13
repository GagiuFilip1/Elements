package com.pesna.gamemain;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.pesna.abstracts.AIObjects;
import com.pesna.abstracts.ObjectsInGame;
import com.pesna.gameobjects.Brick;
import com.pesna.gameobjects.Player;
import com.pesna.gameobjects.AI_Objects.AI_Logic;

public class Game 
{
	public void RunGameLogic
			(
					Player player, Control keyBindings, AI_Logic AI_log, 
					ArrayList<ObjectsInGame> objectsList,ArrayList<AIObjects> enemyList, 
					SpriteBatch batch, OrthographicCamera camera
			)
	{
		
		keyBindings.Set(player, enemyList);
		player.update(Gdx.graphics.getDeltaTime());
		Rectangle temp = new Rectangle(0,0,800,10);
		if(player.hits(temp) != -1){
			player.action(1,0, 10);
		}		
		for(Iterator<ObjectsInGame> i = objectsList.iterator(); i.hasNext();)
		{
			Brick t = (Brick) i.next();
			switch (player.hits(t.getHitBox()))
			{
				case 1 :
				  player.action(1 ,0 ,t.getHitBox().y + t.getHitBox().height);
				break;
				case 2 :
					player.action(2 ,t.getHitBox().x + t.getHitBox().width -1 ,0);
				break;
				case 3 :
					player.action(3 ,t.getHitBox().x - t.getHitBox().width + 1 ,0);
				break;
				case 4 :
					  player.action(4 ,0 ,t.getHitBox().y - t.getHitBox().height);
					break;				
			}
		}		
	AI_log.Load(enemyList, batch, player, camera);			
	UpdateCamera(batch, camera,player);
	}
	public void UpdateCamera(SpriteBatch batch, OrthographicCamera camera,Player player)
	{
		camera.position.x = player.GetPosition(1);
		camera.update();    
		batch.setProjectionMatrix(camera.combined);
	}
}
