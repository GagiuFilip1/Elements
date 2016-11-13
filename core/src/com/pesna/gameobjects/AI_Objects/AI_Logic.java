package com.pesna.gameobjects.AI_Objects;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pesna.abstracts.AIObjects;
import com.pesna.gameobjects.Player;

public class AI_Logic 
{
	private float elapsedTime = 2 , time = 0 , time2 = 0;
	private boolean over = false;
	private void CountTime(float x, ArrayList<AIObjects> enemyList , Player player ,int id)
	{
		over = false;
		if(over == false && id ==1)
		{
			time += Gdx.graphics.getDeltaTime();	
			if(time >= x)
			{
				for(Iterator<AIObjects> i = enemyList.iterator(); i.hasNext();)
				{ 
						AEntity1 t = (AEntity1) i.next();
						t.Hited(player.GetPosition(1));
				}
				time = 0;
				over = true;
			}
		}
		time2 += Gdx.graphics.getDeltaTime();
		for(Iterator<AIObjects> i = enemyList.iterator(); i.hasNext();)
		{ 
				AEntity1 t = (AEntity1) i.next();
		if(time2 >= x && id == 2)
			{
			player.TakeDamange(t.DealDamange());
			time2 =0;
			}
		}
	}
	public void Load(ArrayList<AIObjects> enemyList , SpriteBatch batch , Player player , Camera camera)
	{
		
		for(Iterator<AIObjects> i = enemyList.iterator(); i.hasNext();)
		{ 
			AEntity1 t = (AEntity1) i.next();
		 if(t.ShouldStart()==false)
		 {
			if(t.IsInFallow(player.GetPosition(1), t.GetPosition(1))==true)
			{
			 t.Fallow(player.GetPosition(1), batch);
			}
			else{
				t.Draw(batch);
			}
			 t.Attack(player.GetPosition(1), batch);
			 if( t.Attack(player.GetPosition(1), batch))
			 {
				 CountTime(t.AttackSpeed(), enemyList ,player,2);
			 }
			 t.createHealthBar(camera);
			 if(t.Dead() == 0)
			 {
				i.remove();
			 }
			 
		 }
		 else if(t.ShouldStart()==true)
		 {
		int v = 0;
			 t.createHealthBar(camera);
			 t.Fall(player, v, batch);
			 CountTime(elapsedTime ,enemyList, player,1);
		 }	
	  }			
	}
}
