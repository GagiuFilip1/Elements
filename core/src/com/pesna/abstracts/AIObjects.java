package com.pesna.abstracts;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.pesna.gameobjects.Player;

public abstract class AIObjects 
{
	public abstract int hits(Rectangle r);
	   public abstract void action(int type , float x , float y);
	   public abstract void update(SpriteBatch batch);
	   public abstract void setPosition(float x , float y);
	   public abstract void moveLeft(float delta);
	   public abstract void moveRight(float delta);
	   public abstract void Draw(SpriteBatch batch);
	   public abstract void Jump();
	   public abstract void Fallow(float x, SpriteBatch batch);
	   public abstract boolean Attack(float player , SpriteBatch batch);
	   public abstract void AnimationsDraw(SpriteBatch batch, int x , int y ,int id);
	   public abstract float GetPosition(int x);
	   public abstract Rectangle getHitBox();
	   public abstract boolean Hited(float player);
	   public abstract boolean IsInRange(float x , float y);
	   public abstract boolean ShouldStart();
	   public abstract void Fall(Player player,int id ,SpriteBatch batch);
	   public abstract int Dead();
	   public abstract boolean IsInFallow(float x, float y); 
	   public abstract void TakeDamange(int x , float player);
	   public abstract void createHealthBar(Camera camera);
	   public abstract int DealDamange();
	   public abstract float AttackSpeed();
}
