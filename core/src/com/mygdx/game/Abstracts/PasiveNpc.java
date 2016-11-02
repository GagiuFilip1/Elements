package com.mygdx.game.Abstracts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameObjects.Player;

/**
 * Created by Filip on 10/29/2016.
 **/
public abstract class PasiveNpc
{
    public abstract void setPosition(float x , float y);
    public abstract void Draw(SpriteBatch batch);
    public abstract void AnimationsDraw(SpriteBatch batch, int x , int y ,int id);
    public abstract void GiveQuest();
    public abstract void SetName(String name);
    public abstract void StartDialog(int a, int b ,int c ,int d);
    public abstract float GetPosition(int x);
    public abstract boolean IsInRange(Player x);
}
