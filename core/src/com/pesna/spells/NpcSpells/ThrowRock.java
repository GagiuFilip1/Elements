package com.pesna.spells.NpcSpells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pesna.Main;
import com.pesna.abstracts.SpellObject;

/**
 * Created by Filip on 11/22/2016.
 */
public class ThrowRock implements SpellObject
{
	Main reference;
    private Texture texture;
    private Sprite sprite;
    private boolean Should_Draw = true , Set_Target = false , Set_Point = false , In_Range = false,Set_Pos =false;
    private float x , y , ENEMY_Y ,ENEMY_X , StartPoint , FallPoint , rotate = 0;
    public ThrowRock( Main _reference )
    {
    	reference = _reference;
    	texture = reference.assetManager.get("ItemsSprites/rock.png", Texture.class );
        sprite = new Sprite(texture,70,80);
    }
    @Override
    public void Draw(SpriteBatch batch, int posX, int posY)
    {
        Set_Caster(posX ,posY);
        if(Should_Draw)
        {
            batch.begin();
            sprite.draw(batch);
            batch.end();
        }
    }

    @Override
    public void MoveTo(int TargetX, int TargetY)
    {
        IS_IN_Range(TargetX);
        Find_Target(TargetX,TargetY);
        x = sprite.getX();
        y = sprite.getY();
        float Half_Distance = (Math.abs(ENEMY_X - StartPoint) / 2) + ENEMY_X;
        float Half_Distance2 = (Math.abs(ENEMY_X - StartPoint) / 2) + StartPoint;
        Set_Fall_Point(TargetX);
        if(In_Range && Should_Draw)
        {

            rotate += (sprite.getRotation() - 40) * Gdx.graphics.getDeltaTime();
            if(Math.abs(rotate) > 10)
            {
                rotate = -10;
            }
            sprite.rotate(rotate);
            if(x < FallPoint && Should_Draw)
            {
                x += 250 * Gdx.graphics.getDeltaTime();
                if(x > Half_Distance2)
                {
                    System.out.println("scade");
                    y -= 200 * Gdx.graphics.getDeltaTime();
                }
                else
                {
                    System.out.println("urca");
                    y += 250 * Gdx.graphics.getDeltaTime();
                }
                sprite.setPosition(x,y);
            }
            else
            {
                x -= 250 * Gdx.graphics.getDeltaTime();
                if(x < Half_Distance)
                {
                    y -= 300 * Gdx.graphics.getDeltaTime();
                }
                else
                {
                    y += 250 * Gdx.graphics.getDeltaTime();
                }
                sprite.setPosition(x,y);
            }
            Destroy(TargetX);
        }
    }
    @Override
    public boolean Destroy(int TargetX)
    {
        float hit = x - FallPoint;
            if(Math.abs(hit) < 20 || y <= 60)
            {
                Should_Draw = false;
                if(Math.abs(reference.player.x - FallPoint) < 20) {
                    reference.player.TakeDamage(5);
                }
                return true;
            }
            else
            {
                return false;
            }
    }
    private void IS_IN_Range(int TargetX)
    {
        if(!In_Range)
        {
            float range = StartPoint - TargetX;
            if(Math.abs(range) < 700) {
                In_Range = true;
            }
        }
    }
    private void Find_Target(int TargetX,int TargetY)
    {
        if(!Set_Target)
        {
            ENEMY_Y = TargetY;
            ENEMY_X = TargetX;
            Set_Target = true;
        }
    }
    private void Set_Fall_Point(int TargetX)
    {
        if (In_Range && !Set_Point)
        {
            FallPoint = ENEMY_X;
            Set_Point = true;
        }
    }
    private void Set_Caster(int x, int y)
    {
        if(!Set_Pos)
        {
            sprite.setPosition(x, y);
            StartPoint = x;
            Set_Pos = true;
        }
    }
}
