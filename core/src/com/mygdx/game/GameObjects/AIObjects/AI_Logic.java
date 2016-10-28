package com.mygdx.game.GameObjects.AIObjects;


import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.Abstracts.AIObjects;
import com.mygdx.game.GameObjects.Player;

/**
 * Universal AI_logic
 */
public class AI_Logic
{
    private float time = 0;
    private float time2 = 0;

    private void CountTime(float x, ArrayList<AIObjects> enemyList , Player player ,int id)
    {
        if(id ==1)
        {
            time += Gdx.graphics.getDeltaTime();
            if(time >= x)
            {
                for(Iterator<AIObjects> i = enemyList.iterator(); i.hasNext();)
                {
                    Enemy1 t = (Enemy1) i.next();
                    t.Hited(player.GetPosition(1));
                }
                time = 0;
            }
        }
        time2 += Gdx.graphics.getDeltaTime();
        for(Iterator<AIObjects> i = enemyList.iterator(); i.hasNext();)
        {
            Enemy1 t = (Enemy1) i.next();
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
            Enemy1 t = (Enemy1) i.next();
            if(!t.ShouldStart())
            {
                if(t.IsInFallow(player.GetPosition(1), t.GetPosition(1)))
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
                if(t.Dead())
                {
                    i.remove();
                }

            }
            else if(t.ShouldStart())
            {
                int v = 0;
                t.createHealthBar(camera);
                t.Fall(player, v, batch);
                float elapsedTime = 2;
                CountTime(elapsedTime,enemyList, player,1);
            }
        }
    }
}