package com.mygdx.game.GameMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import com.mygdx.game.Abstracts.ObjectsInGame;
import com.mygdx.game.GameObjects.Brick;
import com.mygdx.game.GameObjects.Player;
import com.mygdx.game.GameObjects.AIObjects.AI_Logic;

/**
 * Created by Filip
 */

class Logic
{
    /**
     *
     *All game logic , collision , camera Update ,AI_logic,and the KeyBindings
     */
    private  AI_Logic AI_log;

    Logic() {
        AI_log = new AI_Logic();
    }

    void RunGameLogic(MainClass Main , Control keyBindings)
    {
        keyBindings.Set(Main.player, Main.enemyList);
        Main.player.update(Gdx.graphics.getDeltaTime());
        Rectangle temp = new Rectangle(0,0,800,10);
        if(Main.player.hits(temp) != -1){
            Main.player.action(1,0, 10);
        }
        for (ObjectsInGame anObjectsList : Main.objectsList) {
            Brick t = (Brick) anObjectsList;
            switch (Main.player.hits(t.getHitBox())) {
                case 1:
                    Main.player.action(1, 0, t.getHitBox().y + t.getHitBox().height);
                    break;
                case 2:
                    Main.player.action(2, t.getHitBox().x + t.getHitBox().width - 1, 0);
                    break;
                case 3:
                    Main.player.action(3, t.getHitBox().x - t.getHitBox().width + 1, 0);
                    break;
                case 4:
                    Main.player.action(4, 0, t.getHitBox().y - t.getHitBox().height);
                    break;
            }
        }
        AI_log.Load(Main.enemyList, Main.batch, Main.player, Main.camera);
        UpdateCamera(Main.batch, Main.camera,Main.player);
    }
    private void UpdateCamera(SpriteBatch batch, OrthographicCamera camera, Player player)
    {
        camera.position.x = player.GetPosition(1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }
}