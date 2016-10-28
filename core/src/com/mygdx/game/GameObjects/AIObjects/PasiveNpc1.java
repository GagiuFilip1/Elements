package com.mygdx.game.GameObjects.AIObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Abstracts.AIObjects;
import com.mygdx.game.GameMain.QuestHandler;
import com.mygdx.game.GameObjects.Player;

public class PasiveNpc1 extends AIObjects
{
    private Sprite sprite;
    private String NpcName = "";
    private Rectangle hitBox;
    private float time;
    private QuestHandler Quests;
    private int quest = 0;

    public PasiveNpc1(int x , int y)
    {
        Quests = new QuestHandler();
        Texture texture = new Texture(Gdx.files.internal(""));
        sprite = new Sprite(texture);
        hitBox = new Rectangle(0,0,128,128);
        setPosition(x ,y);
    }
    @Override
    public int hits(Rectangle r) {
        return 0;
    }

    @Override
    public void action(int type, float x, float y) {

    }

    @Override
    public void update(SpriteBatch batch)
    {

    }

    @Override
    public void setPosition(float x, float y)
    {
    sprite.setPosition(x ,y);
        hitBox.x = x;
        hitBox.y = y;
    }

    @Override
    public void moveLeft(float delta) {
    //Static NPC
    }

    @Override
    public void moveRight(float delta) {
    //Static NPC
    }

    @Override
    public void Draw(SpriteBatch batch)
    {
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void Jump()
    {
    //Static NPC
    }

    @Override
    public void Fallow(float x, SpriteBatch batch)
    {
    //Static NPC
    }

    @Override
    public boolean Attack(float player, SpriteBatch batch)
    {
        return false;
    }

    @Override
    public void AnimationsDraw(SpriteBatch batch, int x, int y, int id)
    {
        Animation animation;
        if(id ==1)
        {
//            TO ADD ANIMATION
            animation = new Animation
                    (
                            0.1f ,
                            new TextureRegion(new Texture("desktop/assets/Sprite/1.png")) ,
                            new TextureRegion(new Texture("desktop/assets/Sprite/2.png")) ,
                            new TextureRegion(new Texture("desktop/assets/Sprite/3.png")) ,
                            new TextureRegion(new Texture("desktop/assets/Sprite/6.png")) ,
                            new TextureRegion(new Texture("desktop/assets/Sprite/4.png"))
                    );

            animation.setPlayMode(Animation.PlayMode.LOOP);
            batch.draw(animation.getKeyFrame(time += Gdx.graphics.getDeltaTime()),x,y);
        }
    }

    @Override
    public float GetPosition(int x) {
        if(x == 1)
        {
            return hitBox.x;
        }
        else
        {
            return hitBox.y;
        }
    }

    @Override
    public Rectangle getHitBox() {
        return hitBox;
    }

    @Override
    public boolean Hited(float player) {
        return false;
    }

    @Override
    public boolean IsInRange(float x, float y) {
        return Math.abs(GetPosition(1) - x) <= 40;
    }
    @Override
    public void Fall(Player player, int id, SpriteBatch batch) {
    // Static NPC
    }
    @Override
    public boolean Dead() {
        return false;
    }

    @Override
    public boolean IsInFallow(float x, float y) {
        return false;
    }

    @Override
    public void TakeDamange(int x, float player)
    {
        //  Immune
    }

    @Override
    public void createHealthBar(Camera camera) {

    }

    @Override
    public int DealDamange() {
        return 0;
    }

    @Override
    public float AttackSpeed() {
        return 0;
    }

    private void Name(String nm)
    {
        NpcName = nm;
    }
    private void QuestNr(int Qnr)
    {
        quest = Qnr;
    }
    @Override
    public boolean ShouldStart() {
        return false;
    }
    public void GiveQuest(String Npc,int quest)
    {
        GetText(Npc,quest);
    }
    private String GetText(String npc,int index)
    {
        return Quests.GetQuest(npc,index);
    }
}
