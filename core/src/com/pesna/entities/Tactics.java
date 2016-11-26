package com.pesna.entities;

import com.pesna.Main;
import com.pesna.abstracts.EnemyStructure;
import com.pesna.spells.SpellBook;

/**
 * Created by Filip on 11/24/2016.
 */
public class Tactics
{
    private static int DISTANCE;

    public static void Defensive( Main reference, int Level , EnemyStructure enemy )
    {
        DISTANCE = Math.abs( enemy.x - (int)reference.player.x);
        switch (Level)
        {
            case 1:
                if(DISTANCE < 700) {
                    if (DISTANCE <= 200) {
                        enemy.Follow(reference.player);
                        enemy.Attack(reference.player);
                        enemy.fall(reference.player);
                        SpellBook.FinishThrow( reference, 0 , enemy );
                    }
                    else if (DISTANCE >= 210)
                    {
                        enemy.fall(reference.player);
                        SpellBook.AI_Cast( reference, 0, enemy);
                    }
                }
                else
                {
                    SpellBook.FinishThrow( reference, 0 , enemy );
                    enemy.fall(reference.player);
                }
                break;
            case 2:
                if(DISTANCE < 700)
                {
                    if (DISTANCE < 50)
                    {
                        enemy.Follow(reference.player);
                        enemy.Attack(reference.player);
                        enemy.fall(reference.player);
                        SpellBook.FinishThrow( reference, 0 , enemy );
                    }
                    else if (DISTANCE < 250 && DISTANCE > 50)
                    {
                        enemy.Run(reference.player);
                        enemy.fall(reference.player);
                        SpellBook.FinishThrow( reference, 0 , enemy );
                    }
                    else if(DISTANCE>250)
                    {
                        enemy.fall(reference.player);
                        SpellBook.AI_Cast( reference, 0, enemy);
                    }
                }
                else
                {
                    SpellBook.FinishThrow( reference, 0 , enemy );
                }
                break;
        }
    }
    public static void Offensive( Main reference, int Level , EnemyStructure enemy )
    {
        DISTANCE = Math.abs(enemy.x - reference.player.x);
        switch (Level)
        {
            case 1:
                break;
            case 2:
                break;
        }
    }
}

