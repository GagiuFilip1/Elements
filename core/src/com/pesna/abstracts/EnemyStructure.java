package com.pesna.abstracts;


import com.pesna.objects.ScreenObject;
import com.pesna.player.Player;

/**
 * Created by Filip on 11/23/2016.
 */
public abstract class EnemyStructure extends ScreenObject
{
	public int x,y;
    public abstract void Attack(Player player);
    public abstract void Follow(Player player);
    public abstract void Run(Player player);
    public abstract void fall(Player player);
    public abstract int GetAtkSpeed();
}
