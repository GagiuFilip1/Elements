package com.pesna.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.pesna.abstracts.ObjectsInGame;

public class Brick extends ObjectsInGame
{
	private Rectangle hitBox; //,bot;
	private Sprite sprite;	
	private Texture texture;
	public Brick(int x ,int y)
	{
		hitBox = new Rectangle(0,0,170,60);
		//bot = new Rectangle (0,0,170,20);
		texture = new Texture(Gdx.files.internal("Sprite/images.jpg"));
		sprite = new Sprite(texture ,0,0,180,64);
		setPosition(x,y);
	}
	@Override
	public int hits(Rectangle r) {
		
		return 0;
	}

	@Override
	public void action(int type, float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPosition(float x, float y) {
		hitBox.x = x;
		hitBox.y = y;
		sprite.setPosition(x, y);
	}

	@Override
	public void moveLeft(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveRight(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Draw(SpriteBatch batch) {
		sprite.draw(batch);	
	}

	@Override
	public void Jump() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getHitBox() {		
		return hitBox;
	}

}
