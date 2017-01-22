package com.pesna.levels;

import com.pesna.Main;
import com.pesna.abstracts.EnemyStructure;
import com.pesna.entities.EnemyObject;
import com.pesna.objects.ScreenObject;
import com.pesna.objects.SimpleTimer;

/**
 * Created by Gagiu on 1/18/2017.
 */
public class Spawner implements ScreenObject
{
  Main reference;
  private SimpleTimer spawnTime;
  public float x = 0 ,y = 0;
  public Spawner(Main _reference)
  {
    spawnTime = new SimpleTimer(3.0f);
  }

  @Override
  public void draw(Main _reference)
  {
  //
  }

  @Override
  public void update(Main _reference)
  {
    if(spawnTime.TimeElapsed()) {
    }
  }

  public void SetSpawnTime(float Time)
  {
    spawnTime.setNewTime(Time);
  }

  public boolean StartSpawnMob()
  {
    return spawnTime.TimeElapsed();
  }

  public void SetPosition(int _x , int _y)
  {
    x = _x;
    y = _y;
  }
}
