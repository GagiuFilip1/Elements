package com.pesna.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pesna.Main;
import com.pesna.abstracts.EnemyStructure;
import com.pesna.player.Player;

/**
 * Created by Filip on 11/23/2016.
 */
public class EnemyObject extends EnemyStructure
{
	private Main reference;
	boolean flip;
    private AITactics thisLogic;
	public Animation animation;
    public int ATTACK_SPEED,SPEED;
    public float x ,y , auxX;
    private float delta = 0.0f , FollowRange = 800, AttackRange = 25, HP ,ATTACK_DAMANGE;
    private boolean pause = false;
    private float counter = 0;
    public EnemyObject( Main _reference, int posX , int posY)
    {
    	reference = _reference;
        x = posX;
        y = posY;
        HP = 100;
        ATTACK_DAMANGE = 30;
        ATTACK_SPEED = 3;
        SPEED = 200;
        thisLogic = new AITactics(reference);
        animation = reference.gameRegistry.animationManager.stay;
    }
    public void draw(Main main)
    {
		SpriteBatch batch = main.batch;
		delta += Gdx.graphics.getDeltaTime();
		TextureRegion keyFrame = animation.getKeyFrame(delta,true);
		batch.begin();
		if ( flip )
			batch.draw( keyFrame, x+keyFrame.getRegionWidth()/2, y, -keyFrame.getRegionWidth(), keyFrame.getRegionHeight());
		else
			batch.draw( keyFrame, x-keyFrame.getRegionWidth()/2, y, keyFrame.getRegionWidth(), keyFrame.getRegionHeight());
		batch.end();
    }
    public void update(Main main)
    {
        thisLogic.SetDefensive(1 , this);
    }
    @Override
    public int GetPosition(int _x) {
        switch (_x) {
            case 1:
                return (int)x;
            case 2:
                return (int)y;
            default:
                return 0;
        }
    }
    @Override
    public void Attack(Player player)
    {
        if(IS_IN_RANGE(player, AttackRange) && !pause)
        {
            if(Timer(ATTACK_SPEED))
            {
        		player.TakeDamage( (int) ATTACK_DAMANGE );
            }
           flip = !(x - player.x <= 0);
            animation = reference.gameRegistry.animationManager.attack;
        }
    }

    @Override
    public void Follow(Player player)
    {
        if(IS_IN_RANGE(player, FollowRange) && !pause && !IS_IN_RANGE(player, AttackRange))
        {
            if( player.x - x > 10 )
            {

                x += SPEED * Gdx.graphics.getDeltaTime();
                auxX = x;
            }
            else if(player.x - x < -10)
            {

                x -= SPEED * Gdx.graphics.getDeltaTime();
                auxX = x;
            }
           flip = !(x - player.x <= 0);
            animation = reference.gameRegistry.animationManager.walk;
        }
        else if (pause)
        {
            fall(player);
        }
        else
        {
            animation = reference.gameRegistry.animationManager.stay;
        }
    }
    @Override
    public void Run(Player player)
    {
        if(IS_IN_RANGE(player, FollowRange+150) && !pause)
        {
            if(player.x - x > 10)
            {
                x -= SPEED * Gdx.graphics.getDeltaTime();
                auxX = x;
            }
            else if(player.x - x < -10)
            {
                x += SPEED * Gdx.graphics.getDeltaTime();
                auxX = x;
            }
           flip = !(x - player.x >= 0);
            animation = reference.gameRegistry.animationManager.walk;
        }
        else if (pause)
        {
            fall(player);
        }
    }

    @Override
    public int GetAtkSpeed() {
        return ATTACK_SPEED;
    }

    private boolean IS_IN_RANGE(Player player, float range)
    {
        return Math.abs(Math.abs(player.x) - x) <= range;
    }
    
    private boolean Timer(int time)
    {
        counter += Gdx.graphics.getDeltaTime();
        return counter > time;
    }
    public void TakeDamange(Player player)
    {
            if(IS_ATTACKED() && IS_IN_RANGE(player, AttackRange) && !pause)
            {
                pause = true;
            }
    }
    @Override
    public void fall(Player player)
    {
        TakeDamange(player);
        if(pause)
        {
            if(player.x - x > 0)
            {
                auxX -= 40 * Gdx.graphics.getDeltaTime();
                if(Math.abs(auxX - x) > 100)
                {
                    pause = false;
                    x = auxX;
                }
            }
            else if(player.x - x <0)
            {
                auxX += 100 * Gdx.graphics.getDeltaTime();
                if(Math.abs(auxX - x) > 100)
                {
                    pause = false;
                    x = auxX;
                }
            }
            animation = reference.gameRegistry.animationManager.fall;
        }
    }

    @Override
    public void SetAnimation(Animation _animation)
    {
        animation = _animation;
    }

    @Override
    public void SetBuff(String statName, float AddValue)
    {
        if(statName.equals("Speed"))
        {
            SPEED += AddValue;
        }
        else if(statName.equals("Attack"))
        {
            ATTACK_DAMANGE += AddValue;
        }
        else if(statName.equals("AttackSpeed"))
        {
            ATTACK_SPEED += AddValue;
        }
    }

    private boolean IS_ATTACKED()
    {
        return reference.player.animation == reference.gameRegistry.animationManager.attack;
    }
}
