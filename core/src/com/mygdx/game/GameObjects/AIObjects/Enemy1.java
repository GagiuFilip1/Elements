package com.mygdx.game.GameObjects.AIObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Abstracts.AIObjects;
import com.mygdx.game.GameObjects.Player;

public class Enemy1 extends AIObjects
{
    private boolean touched, over = false;
    private int i1 ;
    private ShapeRenderer HealthBar;
    private float  random,time;
    private Rectangle hitBox;
    private Sprite sprite;
    private int PLAYER_AI_D;
    private int HitPoints = 100 , AttackDamange = 5 , AttackSpeed = 3 , Speed = 100, Range = 400;

    ///________________///
    //     Basics     //
    ///_______________///
    public Enemy1(int x ,int y)
    {
        hitBox = new Rectangle(0,0,128,128);
        Texture texture = new Texture(Gdx.files.internal("desktop/assets/Sprite/stay.png"));
        sprite = new Sprite(texture,0,0,128,128);
        HealthBar = new ShapeRenderer();
        setPosition(x, y);
        touched = false;
    }
    @Override
    public void setPosition(float x, float y)
    {
        sprite.setPosition(x, y);
        hitBox.x = x;
        hitBox.y = y;
    }
    @Override
    public void createHealthBar(Camera camera)
    {
        HealthBar.begin(ShapeType.Filled);
        HealthBar.setProjectionMatrix(camera.combined);
        HealthBar.setColor(Color.RED);
        HealthBar.rect(GetPosition(1), GetPosition(2)+135, HitPoints, 8);
        HealthBar.end();
    }
    @Override
    public void update(SpriteBatch batch)
    {
        //createHealthBar();
    }
    @Override
    public int hits(Rectangle r)
    {
        if(hitBox.overlaps(r))
        {
            return 1;
        }
        return 0;
    }
    @Override
    public void action(int type, float x, float y)
    {
        switch (type) {
            case 1:
                touched = true;
                break;
            case 2:
                touched = false;
                break;
        }
    }
    @Override
    public Rectangle getHitBox()
    {
        return hitBox;
    }

    @Override
    public float GetPosition(int x)
    {
        if(x == 1)
        {
            return hitBox.x;
        }
        if(x == 2)
        {
            return hitBox.y;
        }
        return 0;
    }
    @Override
    public int Dead()
    {
        return HitPoints <= 0 ? 0 : 1;
    }

    //_____________________//
    ///Movement + Attack///
    //____________________//
    public void Fallow(float x , SpriteBatch batch)
    {
        if(!ShouldStart())
        {
            if(GetPosition(1) < x - 40)
            {
                batch.begin();
                moveRight(Gdx.graphics.getDeltaTime());
                AnimationsDraw(batch ,(int)GetPosition(1), (int)GetPosition(2) , 1);
                batch.end();
            }
            else if(GetPosition(1) > x + 40)
            {
                batch.begin();
                moveLeft(Gdx.graphics.getDeltaTime());
                AnimationsDraw(batch ,(int)GetPosition(1), (int)GetPosition(2) , 2);
                batch.end();

            }
        }
    }

    public void moveLeft(float delta)
    {
        hitBox.x -= Speed * delta;
        sprite.setPosition(hitBox.x,hitBox.y);
    }

    @Override
    public void moveRight(float delta)
    {
        hitBox.x += Speed * delta;
        sprite.setPosition(hitBox.x,hitBox.y);
    }

    @Override
    public void Jump()
    {

    }
    @Override
    public boolean Attack(float player , SpriteBatch batch)
    {

        if(IsInRange(player, GetPosition(1)))
        {
            if(player < GetPosition(1))
            {
                batch.begin();
                AnimationsDraw(batch ,(int)GetPosition(1), (int)GetPosition(2) , 4);
                batch.end();
            }
            else
            {
                batch.begin();
                AnimationsDraw(batch ,(int)GetPosition(1), (int)GetPosition(2) , 3);
                batch.end();
            }
            return true;
        }
        else{
            return false;
        }
    }

    ///__________________///
    //		DRAWING     //
///__________________///
    @Override
    public void Draw(SpriteBatch batch)
    {
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }
    @Override
    public void AnimationsDraw(SpriteBatch batch, int x, int y, int id) {

        Animation animation;
        switch (id) {
            case 1:
                animation = new Animation
                        (
                                0.1f,
                                new TextureRegion(new Texture("desktop/assets/Sprite/1.png")),
                                new TextureRegion(new Texture("desktop/assets/Sprite/2.png")),
                                new TextureRegion(new Texture("desktop/assets/Sprite/3.png")),
                                new TextureRegion(new Texture("desktop/assets/Sprite/6.png")),
                                new TextureRegion(new Texture("desktop/assets/Sprite/4.png"))
                        );

                animation.setPlayMode(Animation.PlayMode.LOOP);
                batch.draw(animation.getKeyFrame(time += Gdx.graphics.getDeltaTime()), x, y);
                break;
            case 2:
                animation = new Animation
                        (
                                0.1f,
                                new TextureRegion(new Texture("desktop/assets/Sprite/1s.png")),
                                new TextureRegion(new Texture("desktop/assets/Sprite/2s.png")),
                                new TextureRegion(new Texture("desktop/assets/Sprite/3s.png")),
                                new TextureRegion(new Texture("desktop/assets/Sprite/6s.png")),
                                new TextureRegion(new Texture("desktop/assets/Sprite/4s.png"))
                        );

                animation.setPlayMode(Animation.PlayMode.LOOP);
                batch.draw(animation.getKeyFrame(time += Gdx.graphics.getDeltaTime()), x, y);
                break;
            case 3:
                animation = new Animation
                        (
                                0.5f,
                                new TextureRegion(new Texture("desktop/assets/Sprite/at1.png")),
                                new TextureRegion(new Texture("desktop/assets/Sprite/at3.png")),
                                new TextureRegion(new Texture("desktop/assets/Sprite/at2.png"))
                        );

                animation.setPlayMode(Animation.PlayMode.LOOP);
                batch.draw(animation.getKeyFrame(time += Gdx.graphics.getDeltaTime()), x, y);
                break;
            case 4:
                animation = new Animation
                        (
                                0.5f,
                                new TextureRegion(new Texture("desktop/assets/Sprite/at1s.png")),
                                new TextureRegion(new Texture("desktop/assets/Sprite/at3s.png")),
                                new TextureRegion(new Texture("desktop/assets/Sprite/at2s.png"))
                        );
                animation.setPlayMode(Animation.PlayMode.LOOP);
                batch.draw(animation.getKeyFrame(time += Gdx.graphics.getDeltaTime()), x, y);
                break;
            case 5:
                animation = new Animation
                        (
                                0.5f,
                                new TextureRegion(new Texture("desktop/assets/Sprite/f2.png")),
                                new TextureRegion(new Texture("desktop/assets/Sprite/f2.png")),
                                new TextureRegion(new Texture("desktop/assets/Sprite/f3.png")),
                                new TextureRegion(new Texture("desktop/assets/Sprite/f4.png"))
                        );
                animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
                batch.draw(animation.getKeyFrame(time += Gdx.graphics.getDeltaTime()), x, y);
                break;
            default:
                Draw(batch);
                break;
        }

    }

    ///__________________///
    //	CHECK POSITION	 //
    ///__________________///

    @Override
    public boolean IsInFallow(float x , float y)
    {
        PLAYER_AI_D = (int) (x- y);
        return Math.abs(PLAYER_AI_D) <= Range;
    }

    @Override
    public boolean IsInRange(float x , float y)
    {
        PLAYER_AI_D = (int) (x- y);
        return Math.abs(PLAYER_AI_D) <= 40;
    }
    ///__________________///
    //		DRAWING     //
    ///__________________///
    @Override
    public boolean Hited(float player) {
        if(IsInRange(player, GetPosition(1)))
        {	i1 = 1;
            over = true;
            ShouldStart();
            return true;
        }
        else
        {
            over = false;		ShouldStart();
            return false;
        }
    }

    ///__________________///
    //		HITED  P     //
    ///__________________///
    @Override
    public int DealDamange()
    {
        return AttackDamange;
    }
    @Override
    public float AttackSpeed() {
        return AttackSpeed;
    }

    ///__________________///
    //		HITED BY P   //
    ///__________________///

    public void Fall(Player player,int id ,SpriteBatch batch)
    {

        if(ShouldStart())
        {
            float position , positionChanger;
            batch.begin();
            id = GetPosition(1) > player.GetPosition(1) ? 1 : 2;
            switch (id) {
                case 1:
                    if (i1 <= 2)
                    {
                        random = (float) (Math.random() * 80 + 20);
                        i1++;
                    }
                    position = GetPosition(1);
                    positionChanger = position + random * Gdx.graphics.getDeltaTime() * 1.5f;
                    setPosition(positionChanger, GetPosition(2));
                    AnimationsDraw(batch, (int) GetPosition(1), (int) GetPosition(2), 5);
                    break;
                default:
                    if (i1 <= 2)
                    {
                        random = (float) (Math.random() * 80 + 20);
                        i1++;
                    }
                    position = GetPosition(1);
                    positionChanger = position - random * Gdx.graphics.getDeltaTime() * 1.5f;
                    setPosition(positionChanger, GetPosition(2));
                    AnimationsDraw(batch, (int) GetPosition(1), (int) GetPosition(2), 5);
                    break;
            }
            batch.end();
        }
    }

    @Override
    public void TakeDamange(int x , float f) {
        if(IsInRange(f, GetPosition(1)) && !ShouldStart())
        {
            HitPoints -= x;
            if(HitPoints < 0)
            {
                HitPoints = 0;
            }
        }

    }
    @Override
    public boolean ShouldStart()
    {
        return over;
    }
}
