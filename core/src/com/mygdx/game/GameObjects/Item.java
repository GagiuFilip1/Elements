package com.mygdx.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Abstracts.Items;
import com.mygdx.game.GameMain.MainClass;
import java.util.StringTokenizer;

/**
 * Created by Filip on 11/4/2016.
 **/
public class Item extends Items
{
    private int ID , ITEM_TYPE ,POSITION_X ,POSITION_Y;
    private boolean IS ,draw =true;
    private Sprite ITEM_SPRITE;
    private String NAME ,META_DATA;

    public Item(int id , int type , int X_pos , int Y_pos)
    {
        ID =id;
        ITEM_TYPE = type;
        POSITION_X = X_pos;
        POSITION_Y = Y_pos;
        Build_Item();
    }

    @Override
    public void SetItem()
    {
        String name = " ";
        String DESCRIPTION = " ";
        FileHandle file = Gdx.files.internal("desktop/assets/ItemsList/ItemData");
        StringTokenizer tokens = new StringTokenizer(file.readString());
        while(tokens.hasMoreTokens())
        {
            String token = tokens.nextToken();
            if(token.equals(String.valueOf(ID)) )
            {

                token = tokens.nextToken();
                name = token;
                token = tokens.nextToken();
                while(!token.equals("|"))
                {
                    DESCRIPTION += token + " ";
                    token = tokens.nextToken();
                }
            }
        }
        NAME = name;
        META_DATA = (NAME  + "\n" + DESCRIPTION);
    }

    @Override
    public void GetMetada()
    {
        System.out.print(META_DATA);
    }

    @Override
    public void GetType()
    {
        switch (ITEM_TYPE)
        {
            case 1:
                IS = true;
                break;
            case 0 :
                IS = false;
                break;
        }
    }

    @Override
    public void SetSpawnPoint()
    {
        ITEM_SPRITE.setPosition(POSITION_X ,POSITION_Y);
    }

    @Override
    public void GetColected()
    {
        if(Colectible())
        {
            ///add in inventory
            draw = false;
        }
    }
    private void Build_Item()
    {
        SetItem();
        Texture ITEM_TEXTURE = new Texture(Gdx.files.internal("desktop/assets/ItemsSprites/" + NAME + ".png"));
        ITEM_SPRITE = new Sprite(ITEM_TEXTURE,50,50);
        SetSpawnPoint();
        GetMetada();
    }
    public void Draw(MainClass main)
    {
        if(draw)
        {
            ITEM_SPRITE.draw(main.batch);
        }
    }
    private boolean Colectible()
    {
        return IS;
    }
}
