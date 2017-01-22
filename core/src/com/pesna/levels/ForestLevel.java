package com.pesna.levels;

import com.badlogic.gdx.graphics.Texture;
import com.pesna.Main;
import com.pesna.objects.ParallaxLoop;
import com.pesna.objects.ParallaxObject;
import com.pesna.objects.ScreenObject;

import java.awt.*;

/**
 * Created by Gagiu on 12/8/2016.
 */
public class ForestLevel implements ScreenObject
{
   private ParallaxLoop tree , bush , groundstone ;
   private ParallaxObject[] forest = new ParallaxObject[500];
   private ParallaxObject[] grass = new ParallaxObject[500];
   private Texture treeTexture , bushTexture , groundStone , grassTexture;
   private Main reference;
   private int forestStart = -3000 , grassStart =  -3000;
   public ForestLevel(Main _reference)
   {
      reference = _reference;
      grassTexture = reference.assetManager.get("ItemsSprites/grass.png");
      treeTexture =  reference.assetManager.get("ItemsSprites/tree.PNG");
      for(int i = 1 ; i <= 20 ; i++)
      {
         forest[i] = new ParallaxObject(forestStart,-270,0.0f, treeTexture);
         if(forestStart <= 200) {
            forestStart += 800;
         }
         else if( forestStart >= 1000 && forestStart < 1800)
         {
            forestStart +=1200;
         }
         else if (forestStart >= 1800)
         {
            forestStart+= 400;
         }
         grass[i] = new ParallaxObject(grassStart , -22 , 0.0f,grassTexture);
         grassStart += 400;
      }
   }

   @Override
   public void draw(Main _reference)
   {
      for(int i = 1 ; i <= 20 ; i++)
      {
         forest[i].draw(_reference.camera.position , reference.batch);
         grass[i].draw(_reference.camera.position , reference.batch);
      }
   }

   @Override
   public void update(Main _reference)
   {

   }
}
