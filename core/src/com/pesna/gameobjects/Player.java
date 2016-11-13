package com.pesna.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.pesna.abstracts.PlayerObjects;

public class Player extends PlayerObjects
{
		public Rectangle bottom , left,right,top,full;
		private Sprite sprite;	
		private Texture texture;
		private float time , stunTime, velocityY;
		private int speed = 300 , Health = 100;
	    private boolean jumpid = true;
	    private ShapeRenderer PlayerHealthBar;
	    private Animation animation;
		
		public Player(int x , int y)
		{
			PlayerHealthBar = new ShapeRenderer();
			full= new Rectangle(0.0f,0.0f,128.0f,128.0f);
			
			bottom= new Rectangle(0.0f,0.0f,128.0f,16.0f);
			
			left= new Rectangle(0.0f,0.0f,64.0f,128.0f);
			
			right= new Rectangle(64.0f,0.0f,64.0f,128.0f);
			
			top =  new Rectangle(0.0f,112.0f,128.0f,16.0f);
			
			texture = new Texture(Gdx.files.internal("Sprite/stay.png"));
			sprite = new Sprite(texture,0,0,128,128);
			velocityY = 0;
			this.setPosition(x, y);
		}
		public void AnimationDraw(SpriteBatch batch ,int x ,int y , int id)
		{
			if(id ==1)
			{
			animation = new Animation
					(
					0.08f ,
					new TextureRegion(new Texture("Sprite/1.png")) ,
					new TextureRegion(new Texture("Sprite/2.png")) ,
					new TextureRegion(new Texture("Sprite/3.png")) ,
					new TextureRegion(new Texture("Sprite/6.png")) ,
					new TextureRegion(new Texture("Sprite/4.png"))
					);
			
			animation.setPlayMode(Animation.PlayMode.LOOP);
			batch.draw(animation.getKeyFrame(time += Gdx.graphics.getDeltaTime()),x,y);
			}
			else if(id == 2)
			{
				animation = new Animation
						(
						0.08f ,
						new TextureRegion(new Texture("Sprite/1s.png")) ,
						new TextureRegion(new Texture("Sprite/2s.png")) ,
						new TextureRegion(new Texture("Sprite/3s.png")) ,
						new TextureRegion(new Texture("Sprite/6s.png")) ,
						new TextureRegion(new Texture("Sprite/4s.png"))
						);
				
				animation.setPlayMode(Animation.PlayMode.LOOP);
				batch.draw(animation.getKeyFrame(time += Gdx.graphics.getDeltaTime()),x,y);
			}
			else if(id == 3)
			{
				animation = new Animation
						(
						0.5f ,
						new TextureRegion(new Texture("Sprite/at1.png")) ,
						new TextureRegion(new Texture("Sprite/at3.png")) ,
						new TextureRegion(new Texture("Sprite/at2.png")) 
						);
				
				animation.setPlayMode(Animation.PlayMode.LOOP);
				batch.draw(animation.getKeyFrame(time += Gdx.graphics.getDeltaTime()),x,y);
			}
			else if(id == 4)
			{
				animation = new Animation
						(
						0.5f ,
						new TextureRegion(new Texture("Sprite/at1s.png")) ,
						new TextureRegion(new Texture("Sprite/at3s.png")) ,
						new TextureRegion(new Texture("Sprite/at2s.png")) 
						);
				
				animation.setPlayMode(Animation.PlayMode.LOOP);
				batch.draw(animation.getKeyFrame(time += Gdx.graphics.getDeltaTime()),x,y);
			}
			else if(id == 5)
			{
				animation = new Animation
						(
						0.5f ,
						new TextureRegion(new Texture("Sprite/f2.png")) ,
						new TextureRegion(new Texture("Sprite/f3.png")) ,
						new TextureRegion(new Texture("Sprite/f4.png")) ,
						new TextureRegion(new Texture("Sprite/f4.png")) ,
						new TextureRegion(new Texture("Sprite/f4.png")) ,
						new TextureRegion(new Texture("Sprite/f4.png")) ,
						new TextureRegion(new Texture("Sprite/f4.png")) ,
						new TextureRegion(new Texture("Sprite/f4.png")) ,
						new TextureRegion(new Texture("Sprite/f4.png")) ,
						new TextureRegion(new Texture("Sprite/f4.png")) 
						);	
				animation.setPlayMode(Animation.PlayMode.NORMAL);
				batch.draw(animation.getKeyFrame(time += Gdx.graphics.getDeltaTime()),x,y);
				if(animation.isAnimationFinished(stunTime += Gdx.graphics.getDeltaTime()))
				{
				
				}
			}
			else
			{
			this.Draw(batch);
			}
		}
		public int hits(Rectangle r)
		{
		   if(bottom.overlaps(r))
	        {
	         return 1;
	         }	
			if(left.overlaps(r))
			{
				return 2;
			}
			if(right.overlaps(r))
			{
				return 3;
			}
			if(top.overlaps(r))
			{
				return 4;
			}         	 	
        return -1;			
		}
		public void action(int type , float x , float y)
		{
			if(type == 4)
			{
				velocityY = 0;
				setPosition(bottom.x,bottom.y);
				jumpid =  false;
			}
			if(type == 1)
			{
				velocityY = 0;
				setPosition(bottom.x,y);
				jumpid =  true;
			}
			if(type == 2)
			{
				velocityY = 0;
				setPosition(x,left.y);
			}
			if(type ==3)
			{
			velocityY = 0;
			setPosition(x,right.y);
				
			}
		}
		public void update(float delta)
		{
			velocityY -= 30 * delta;
			bottom.y += velocityY;
			sprite.setPosition(bottom.x, bottom.y);
		}
      public void setPosition(float x , float y)
      {
    	  full.x = x;
    	  full.y = y;
    	  
    	  bottom.x = x;
    	  bottom.y = y;
    	
    	  left.x = x;
    	  left.y = y + 16;
    	  
    	  right.x = x + 64;
    	  right.y = y + 16;
    	
    	  top.x = x;
    	  top.y = y + 112;
          sprite.setPosition(x,y);
          
      }
      @Override
      public void CreateHealthBar(Camera camera , int x , int y)
      {
    	  PlayerHealthBar.begin(ShapeType.Filled);
 		  PlayerHealthBar.setProjectionMatrix(camera.combined);
 	      PlayerHealthBar.setColor(Color.GREEN);
 	      PlayerHealthBar.rect(x,y, Health * 5, 15);
 	      PlayerHealthBar.end();
      }
      
      public float GetPosition(int x)
      {
    	   if(x==1)
    	   {
    	   return bottom.x;
    	   }
    	   if(x==2)
    	   {
    	   return bottom.y;
    	   }
    	   return 0;
      }
      public void moveLeft(float delta)
      {
    	  
    	  bottom.x -= (speed * delta);
    	  sprite.setPosition(bottom.x, bottom.y);
      }
      public void moveRight(float delta)
      {
    	  
    	  bottom.x += (speed * delta);
    	  sprite.setPosition(bottom.x, bottom.y);
      }
      int jumpsnr = 1;
      public void Jump()
      {
    	 if(jumpid == true){
          velocityY = 10;
          jumpsnr += 1;  
    	 }
         if(jumpsnr >=10)
         {
    	 jumpid = false;
    	 jumpsnr = 1;
         }
      
      }
      public void Draw(SpriteBatch batch)
      {
    	  sprite.draw(batch);
      }
	@Override
	public Rectangle getHitBox() {
		
		return full;
	}
	public void SetCamera(OrthographicCamera r)
	{
	    r.position.set(bottom.getX(),bottom.getY(),0);
	    r.update();
	}
	public void Attack()
	{
		
	}
	@Override
	public void TakeDamange(int x) {
		Health -= x;		
	}
	@Override
	public boolean Die() {
		if(Health <= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
}
