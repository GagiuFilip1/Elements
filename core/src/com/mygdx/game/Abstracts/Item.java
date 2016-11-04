package com.mygdx.game.Abstracts;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameMain.MainClass;

/**
 * Created by teo on 29.10.2016.
 */
public abstract class Item {
    private int ID, metadata = 0;
    Texture texture;


    Item(int _ID, int _metadata, String filename)
    {
        ID = _ID;
        metadata = _metadata;
        texture = MainClass.assetManager.get(filename);
    }

    int getID()
    {
        return ID;
    }

    int getMetadata()
    {
        return metadata;
    }
}
