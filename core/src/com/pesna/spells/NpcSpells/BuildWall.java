package com.pesna.spells.NpcSpells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pesna.abstracts.SpellObject;

/**
 * Created by Filip on 11/26/2016.
 */
public class BuildWall implements SpellObject
{
    private Texture texture;
    private Sprite sprite;
    private boolean Should_Draw = true , Set_Target = false , Set_Point = false , In_Range = false,Set_Pos =false;
    private float CURENT_X , CURENT_Y , ENEMY_Y ,ENEMY_X , StartPoint;
    @Override
    public void Draw(SpriteBatch batch, int posX, int posY)
    {

    }

    @Override
    public void MoveTo(int TargetX, int TargetY)
    {

    }

    @Override
    public boolean Destroy(int TargetX)
    {
        return false;
    }
}
