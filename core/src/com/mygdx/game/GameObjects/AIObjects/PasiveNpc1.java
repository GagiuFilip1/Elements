package com.mygdx.game.GameObjects.AIObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Abstracts.PasiveNpc;
import com.mygdx.game.GUI.DialogueInterface;
import com.mygdx.game.GameMain.DialogueHandler;
import com.mygdx.game.GameMain.QuestHandler;
import com.mygdx.game.GameObjects.Player;


public class PasiveNpc1 extends PasiveNpc
{
    private String SetName;
    private QuestHandler Quester;
    private Rectangle HitBox;
    private DialogueInterface DialogGUI;
    private DialogueHandler Dialog;
    private Sprite sprite;
    public boolean Quest_Check , Dialog_Check ,Start;
    public PasiveNpc1(boolean HaveQuest, boolean HaveDialog , String Name ,int x ,int y)
    {
        DialogGUI = new DialogueInterface();
        Quest_Check = HaveQuest;
        HitBox = new Rectangle(0,0,128,128);
        Dialog_Check = HaveDialog;
        Start = false;
        Quester = new QuestHandler();
        Dialog = new DialogueHandler();
        Texture texture = new Texture(Gdx.files.internal("desktop/assets/Sprite/stay.png"));
        sprite = new Sprite(texture,0,0,128,128);
        SetName(Name);
        setPosition(x ,y);
    }
    @Override
    public void setPosition(float x, float y)
    {
        sprite.setPosition(x , y);
        HitBox.x =x;
        HitBox.y = y;
    }
    @Override
    public void Draw(SpriteBatch batch)
    {
        batch.begin();
        batch.draw(sprite , HitBox.x , HitBox.y);
        CreateQuestMark(batch);
        batch.end();

    }

    @Override
    public void AnimationsDraw(SpriteBatch batch, int x, int y, int id)
    {

    }
    private void CreateQuestMark(SpriteBatch batch)
    {
        if(Quest_Check)
        {
            Texture markTexture = new Texture(Gdx.files.internal(""));
            Sprite questMark = new Sprite(markTexture);
            questMark.setPosition(GetPosition(1) , GetPosition(2) + 100);
            sprite.draw(batch);
        }
    }

    @Override
    public void SetName(String name)
    {
        SetName = name;
    }

    @Override
    public void StartDialog(int a, int b, int c ,int d)
    {
        if (Dialog_Check && Start)
        {
            Dialog.GetDialogueLines(a, b, c, d);
            Dialog.BuildDialogue("Hero", SetName);
            DialogGUI.Start(Dialog.Text());
            //System.out.print(Dialog.Text() + " That was hero " +"\n");
        }
    }
    @Override
    public void GiveQuest()
    {
       if(Quest_Check)
       {
           int questsId = 1;
           System.out.print(Quester.GetQuest(SetName , questsId));
       }
    }
    @Override
    public float GetPosition(int x)
    {
        switch(x)
        {
            case 1:
                return HitBox.x;
            case 2 :
                return HitBox.y;
            default:
                return 0;
        }
    }

    @Override
    public boolean IsInRange(Player x)
    {
        return Math.abs(x.GetPosition(1) - GetPosition(1)) < 40;
    }
}
