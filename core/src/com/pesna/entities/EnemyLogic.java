package com.pesna.entities;

import java.util.Iterator;
import java.util.LinkedList;

import com.pesna.Main;
import com.pesna.abstracts.EnemyStructure;

/**
 * Created by Filip on 11/23/2016.
 */
public class EnemyLogic
{
	/*
    public void Load( LinkedList<EnemyStructure> enemysOnMap )
    {
        for(Iterator<EnemyStructure> i = enemysOnMap.iterator(); i.hasNext();)
        {
            EnemyObject t = (EnemyObject) i.next();
            get( reference, t );
        }
    }
    */
    
    public static void get( Main reference, EnemyObject t)
    {
        Tactics.Defensive( reference, 1 ,t );
    }
}
