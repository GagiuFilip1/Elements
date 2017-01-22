package com.pesna.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.pesna.Main;
import com.pesna.abstracts.EnemyStructure;
import com.pesna.objects.SimpleTimer;
import com.pesna.objects.SoundManager;
import com.pesna.player.Player;
import com.pesna.talentInterface.TalentBody;

/**
 * Created by Filip on 11/23/2016.
 */
public class EnemyObject extends EnemyStructure
{
	private Main reference;
	private SimpleTimer bleedTimer , slowTimer;

  	boolean flip;
	  private SimpleTimer atackTimer , AuxiliarTimer;
    private AITactics thisLogic;
	  public Animation animation;
    public float ATTACK_SPEED,SPEED;
    public float x ,y , auxX;
    private float delta = 0.0f , FollowRange = 800, AttackRange = 100, HP ,ATTACK_DAMANGE;
    private boolean pause = false;
    private float counter = 0;
    public boolean IsBleeding = false , IsRunning = false , isHited = false;
    private SoundManager runSound , attackSound;

    public EnemyObject( Main _reference, int posX , int posY)
    {
        runSound = new SoundManager("sounds/walk.mp3");
        runSound.LoopSound(true);
        bleedTimer = new SimpleTimer(2.0f);
    	reference = _reference;
        x = posX;
        slowTimer = new SimpleTimer(7.0f);
        y = posY;
        HP = 700;
        ATTACK_DAMANGE = 30;
        ATTACK_SPEED = 2.0f;
        SPEED = 200;
        thisLogic = new AITactics(reference);
        animation = reference.gameRegistry.animationManager.eidle;
        atackTimer = new SimpleTimer(ATTACK_SPEED);
        AuxiliarTimer = new SimpleTimer(1.0f);
//        attackSound.PLay();
    }
    public void draw(Main main) {
      if (!IS_Dead()) {
        drawHealth();
        SpriteBatch batch = main.batch;
        delta += Gdx.graphics.getDeltaTime();
        TextureRegion keyFrame = animation.getKeyFrame(delta, true);
        batch.begin();
        if (flip)
          batch.draw(keyFrame, x + keyFrame.getRegionWidth() / 2, y, -keyFrame.getRegionWidth(),
              keyFrame.getRegionHeight());
        else
          batch.draw(keyFrame, x - keyFrame.getRegionWidth() / 2, y, keyFrame.getRegionWidth(),
              keyFrame.getRegionHeight());
        batch.end();
      }
    }
    public void update(Main main)
    {
      if(!IS_Dead()) {
        if(animation == reference.gameRegistry.animationManager.eidle)
        {
          if ( y > - 30)
          {
            y -= 30;
          }
        }
        else
        {
          if( y < 0)
          {
            y = 0;
          }
        }
        thisLogic.SetOffensive(1, this);
        IsBleeding = reference.player.doBleed;
        bleed();
        WasAttacked();
        slowed();
      }
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
    private boolean playattack = false;
    @Override
    public void Attack(Player player)
    {
        //runSound.Pause();
       // IsRunning = false;
        if(IS_IN_RANGE(player, AttackRange) && !pause)
        {
            if(atackTimer.TimeElapsed())
            {

                animation = reference.gameRegistry.animationManager.eattack;
                System.out.println("attack me");
                playattack = true;
                player.TakeDamage( (int) ATTACK_DAMANGE );
        		    reference.player.isAttacked = true;
            }
            else if( animation == reference.gameRegistry.animationManager.eattack)
            {
                if(AuxiliarTimer.TimeElapsed())
                {

                    animation = reference.gameRegistry.animationManager.eidle;
                }
            }
            else
            {
                animation = reference.gameRegistry.animationManager.eidle;
            }

            reference.player.isAttacked = false;
           flip = !(x - player.x >= 0);
        }
        else
        {
            playattack = false;
        }
    }

    @Override
    public void Follow(Player player)
    {
        if(IS_IN_RANGE(player, FollowRange) && !pause && !IS_IN_RANGE(player, AttackRange) && !IS_IN_RANGE(player , 30))
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
            flip = !(x - player.x >= 0);
            animation = reference.gameRegistry.animationManager.erun;
        }
        else if (pause)
        {
            fall(player);
        }
        else
        {
            if(!IS_IN_RANGE(player , 100))
            animation = reference.gameRegistry.animationManager.eidle;
        }
    }

    @Override
    public void Run(Player player)
    {
        if(IS_IN_RANGE(player, FollowRange+150) && !pause && !IS_IN_RANGE(player , 100))
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
           flip = !(x - player.x <= 0);
            animation = reference.gameRegistry.animationManager.erun;
        }
        else if (pause)
        {
            fall(player);
        }
    }

    @Override
    public float GetAtkSpeed() {
        return ATTACK_SPEED;
    }

    public boolean IS_IN_RANGE(Player player, float range)
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
            if(IS_IN_RANGE(player, AttackRange) && player.autoattackTimer.TimeElapsed() && Gdx.input.isKeyPressed(Keys.Q) )
            {
                HP -= player.DAMAGE;
                System.out.println(HP + player.DAMAGE +" - " + player.DAMAGE + " 8" + "||" + HP);
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

    public void TakeDamage(float value)
    {
        HP -= value;
        isHited = true;
      System.out.println("||||||||||||||||||||||||||||||" + isHited);
    }

    public void DealDamage(Player player)
    {

    }

    private boolean IS_ATTACKED()
    {
        return reference.player.animation == reference.gameRegistry.animationManager.hattack;
    }

    private void bleed()
    {
      System.out.println(isHited);
        if(IsBleeding && bleedTimer.TimeElapsed())
        {
          if(IS_IN_RANGE(reference.player, 105))
            TakeDamage(10);
            isHited = false;
        }
    }
    private boolean aux;
    private void slowed()
    {
        if(reference.player.used)
        {
            SPEED -= 80;
            reference.player.used = false;
            aux = true;
        }
        if(aux) {
            if (slowTimer.TimeElapsed()) {
                reference.player.used = false;
                SPEED = 200;
                aux = false;
            }
        }
    }
    private void WasAttacked()
    {
        if(IS_IN_RANGE(reference.player, 30))
        {
            if(Gdx.input.isKeyPressed(Input.Keys.A) && reference.player.ultimateUsed)
            {
                reference.player.DAMAGE -= 100;
                reference.player.ultimateUsed = false;
            }
        }
    }

    private boolean expAdded = false;
    @Override
    public boolean IS_Dead()
    {
        if(HP <= 0)
        {
          if(!expAdded) {
            reference.player.Experience += 300;
          expAdded = true;
          }
            return true;
        }
        else
        {
            return false;
        }
    }
    private void drawHealth()
    {
      float procent;
      procent = 100 * HP / 500;
      reference.shapeRenderer.begin(ShapeType.Filled);
      reference.shapeRenderer.setColor(Color.FIREBRICK);
      reference.shapeRenderer.rect(x - 40, y +400,procent,20);
      reference.shapeRenderer.end();
    }
}
