package com.mygdx.game.GameMain;


import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import com.mygdx.game.Abstracts.AIObjects;
import com.mygdx.game.Abstracts.ObjectsInGame;
import com.mygdx.game.GameObjects.Brick;
import com.mygdx.game.GameObjects.Player;
import com.mygdx.game.GameObjects.AIObjects.AI_Logic;

class Logic
{
    void RunGameLogic
            (
                    Player player, Control keyBindings, AI_Logic AI_log,
                    ArrayList<ObjectsInGame> objectsList, ArrayList<AIObjects> enemyList,
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
    private void UpdateCamera(SpriteBatch batch, OrthographicCamera camera, Player player)
    {
        camera.position.x = player.GetPosition(1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }
}