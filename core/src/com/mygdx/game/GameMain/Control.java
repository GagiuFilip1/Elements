package com.mygdx.game.GameMain;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.mygdx.game.Abstracts.AIObjects;
import com.mygdx.game.Abstracts.PasiveNpc;
import com.mygdx.game.GameObjects.AIObjects.PasiveNpc1;
import com.mygdx.game.GameObjects.Player;
import com.mygdx.game.GameObjects.AIObjects.Enemy1;
/**
 * Created by Filip
 */
public class Control
{
    private int animationID = 0 , playerPosition = 0;
    public boolean jumpID = false , start = false;
    /**
     *
     * @param player (our player)
     * @param enemyList (all AI's that can be found in game)
     * Set(player, enemyList) get the KeyBindings and how they influence player and AI's;
     */
    void Set(Player player, ArrayList<AIObjects> enemyList ,ArrayList<PasiveNpc> pasiveList)
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

            for (AIObjects anEnemyList : enemyList) {
                Enemy1 t = (Enemy1) anEnemyList;
                t.TakeDamange(40, player.GetPosition(1));
                if (t.IsInRange(player.GetPosition(1), t.GetPosition(1))) {
                    t.Hited(player.GetPosition(1));
                }
            }
            player.Attack();
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.F))
        {
            for (PasiveNpc aPasiveList : pasiveList) {
                PasiveNpc1 t = (PasiveNpc1) aPasiveList;
                if (t.IsInRange(player))
                {
                    start = true;
                    t.Start = start;
                    t.StartDialog(3,5,10,15);
                }
            }
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
