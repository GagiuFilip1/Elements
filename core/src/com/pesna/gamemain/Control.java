package com.pesna.gamemain;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.pesna.abstracts.AIObjects;
import com.pesna.gameobjects.Player;
import com.pesna.gameobjects.AI_Objects.AEntity1;

public class Control
{
	 public int animationID = 0 , playerPosition = 0;
	 public boolean jumpID = false;
		public void Set(Player player , ArrayList<AIObjects> enemyList)
	{
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			player.moveLeft(Gdx.graphics.getDeltaTime());
			animationID = 2;	playerPosition = 0;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			player.moveRight(Gdx.graphics.getDeltaTime());
			animationID = 1;	playerPosition = 1;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
		{
			if(jumpID == true)
			{
			player.Jump();		jumpID = false;
			}
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.A))
		{
			if(playerPosition == 1)
			{
			animationID = 3;
			}
			else
			{
			animationID = 4;
			}

			for(Iterator<AIObjects> i = enemyList.iterator(); i.hasNext();)
			{ 
				AEntity1 t = (AEntity1) i.next();
				t.TakeDamange(40, player.GetPosition(1));
				if(t.IsInRange(player.GetPosition(1), t.GetPosition(1)) == true )
				{
						t.Hited(player.GetPosition(1));
				}
				
			}
			player.Attack();
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.T))
		{
		
		}
		else
		{
			animationID = 0;
		}
	}
		public int AnimationID()
		{
			return animationID;
		}
}
