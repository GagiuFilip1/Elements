package com.pesna.spells;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.pesna.Main;
import com.pesna.abstracts.EnemyStructure;
import com.pesna.abstracts.SpellObject;
import com.pesna.spells.NpcSpells.BuildWall;
import com.pesna.spells.NpcSpells.ThrowRock;

/**
 * Created by Filip on 11/22/2016.
 */
public class SpellBook
{
    private static int anIndex = 0;
    private static float counter = 3 , castTime;
    private static boolean HasColapsed = false;
    private static List<SpellObject> All_Npc_Spels = new LinkedList<SpellObject>();
    static SpellObject[] object = new SpellObject[500];
    private static void AddAll( Main reference)
    {
        All_Npc_Spels.add(anIndex,new ThrowRock(reference));
    }
    public static void AI_Cast( Main reference, int index , EnemyStructure ai)
    {
        if(All_Npc_Spels.isEmpty())
        {
            AddAll(reference);
        }
      if(index == 0)
      {
          UseThrowRock( reference, index , ai);
      }
      if(index == 1)
      {
          UseWall( reference, index , ai);
      }
    }
    public static void FinishThrow( Main reference, int index , EnemyStructure ai)
    {
        try {
            object[index] = All_Npc_Spels.get(anIndex);
            object[index].Draw(reference.batch, ai.x, ai.y);
            object[index].MoveTo((int) reference.player.x, (int) reference.player.y);
        }
        catch (IndexOutOfBoundsException ex)
        {

        }
    }
    private static boolean Timer( Main reference, int id,float FinishPoint)
    {
        counter += 2*Gdx.graphics.getDeltaTime();
        if(HasColapsed)
        {
            anIndex++;
            if(id == 0)
            {
                All_Npc_Spels.add(anIndex , new ThrowRock(reference));
            }
            if(id == 1)
            {
                All_Npc_Spels.add(anIndex,new BuildWall());
            }
            counter = 0.0f;
        }
        if(counter < FinishPoint)
        {
            HasColapsed = false;
            return false;
        }
        else
        {
            return true;
        }

    }
    private static void SpellLogic( Main reference, int index , EnemyStructure ai)
    {
        if(index == 0)
        {
            castTime = ai.GetAtkSpeed();
        }
        else if(index == 1)
        {
            castTime = 3 * ai.GetAtkSpeed();
        }
        if (Timer( reference, index,castTime))
        {
            object[index].Draw(reference.batch, ai.x, ai.y);
            object[index].MoveTo((int) reference.player.x, (int) reference.player.y);
            if (object[index].Destroy((int) reference.player.x))
            {
                HasColapsed = true;
            }
        }
    }
    private static void UseThrowRock( Main reference, int index , EnemyStructure ai)
    {
        object[index] = All_Npc_Spels.get(anIndex);
        SpellLogic( reference, index,ai);
    }
    private static void UseWall( Main reference, int index , EnemyStructure ai)
    {
        object[index] = All_Npc_Spels.get(anIndex);
        SpellLogic( reference, index, ai);
    }

}
