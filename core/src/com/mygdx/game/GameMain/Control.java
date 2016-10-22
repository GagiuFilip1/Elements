package com.mygdx.game.GameMain;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.mygdx.game.Abstracts.AIObjects;
import com.mygdx.game.GameObjects.Player;
import com.mygdx.game.GameObjects.AIObjects.Enemy1;

class Control
{
    private int animationID = 0 , playerPosition = 0;
    private boolean jumpID = false;
    void Set(Player player, ArrayList<AIObjects> enemyList)
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
            if(jumpID)
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
                Enemy1 t = (Enemy1) i.next();
                t.TakeDamange(40, player.GetPosition(1));
                if(t.IsInRange(player.GetPosition(1), t.GetPosition(1)))
                {
                    t.Hited(player.GetPosition(1));
                }

            }
            player.Attack();
        }
        else
        {
            animationID = 0;
        }
    }
    int AnimationID()
    {
        return animationID;
    }
}
