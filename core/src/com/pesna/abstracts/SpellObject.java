package com.pesna.abstracts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Filip on 11/20/2016.
 */
public interface SpellObject
{
    void Draw(SpriteBatch batch, int posX, int posY);
    void MoveTo(int TargetX, int TargetY);
    boolean Destroy(int TargetX);
}
