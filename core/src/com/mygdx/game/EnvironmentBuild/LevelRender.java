package com.mygdx.game.EnvironmentBuild;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.Abstracts.AIObjects;
import com.mygdx.game.Abstracts.ObjectsInGame;
import com.mygdx.game.Abstracts.PasiveNpc;
import com.mygdx.game.GameObjects.AIObjects.Enemy1;
import com.mygdx.game.GameObjects.AIObjects.PasiveNpc1;
import com.mygdx.game.GameObjects.Brick;
import com.mygdx.game.GameObjects.Player;
/**
 * Created by Filip
 */
public class LevelRender
{
    private int H , W , alg = 0;
    private int[][] Map;
    private ArrayList<ObjectsInGame> objectsList;
    private static Texture texture = new Texture(Gdx.files.internal("desktop/assets/Backgrounds/FireBack.jpg"));
    public static Sprite sprite = new Sprite(texture);
    private boolean stop = false;

    public LevelRender()
    {
        objectsList = new ArrayList<ObjectsInGame>();
    }

    /**
     *
     *  GetLevelData(level) get the map structure for a specific level but is not building it
     */
    public void GetLevelData(int level)
    {
        GetLevelBorders();
        Map = new int[H][W];
        String[] elements = new String[H * W];
        FileHandle file = Gdx.files.internal(ChooseLevel(level));
        StringTokenizer tokens = new StringTokenizer(file.readString());

        while(tokens.hasMoreTokens())
        {
            for(int i = 0 ;i < H *W ;i++)
            {
                String type = tokens.nextToken();
                elements[i] = type;
            }
            for(int i = 0 ;i < H ;i++)
            {
                for(int j = 0;j < W-1;j++)
                {
                    if(!elements[alg].equals("|"))
                    {
                        Map[i][j] = Integer.parseInt(elements[alg]);
                    }
                    alg++;
                }
                alg++;
            }
        }
        int width = -1000 , height = 0;
        for(int i = 0 ;i < H;i++)
        {
            for(int j = 0;j <= W-1;j++)
            {
                if(Map[i][j] == 1)
                {
                    objectsList.add(new Brick(width, height));
                }
                width += 180;
            }
            width = 0;
            height += 64;
        }
    }

    /**
     *
     * Build(batch, list) Build the environment objects that can be found is list
     *      You may use GetLevelData(Level) first;
     */
    public void Build(SpriteBatch batch , ArrayList<ObjectsInGame> list)
    {
           for (ObjectsInGame t : list) {
               t.Draw(batch);
           }
    }
    private String ChooseLevel(int x)
    {
        return "desktop/assets/LevelStructure/Level" +x;
    }

    private void GetLevelBorders()
    {
        FileHandle file = Gdx.files.internal("desktop/assets/LevelStructure/Level1");
        StringTokenizer tokens = new StringTokenizer(file.readString());
        while(tokens.hasMoreTokens())
        {
            String type = tokens.nextToken();

            if(!stop)
            {
                W++;
            }
            if(type.equals("|"))
            {
                H++;	stop= true;
            }
        }
    }

    /**
     *
     * @return the objects list created in GetLevelData(level), use it as parameter in Build(batch,list);
     */
    public ArrayList<ObjectsInGame> GetObjectsList()
    {
        return objectsList;
    }
    public void SetBackground(Player player)
    {
        Sprite sprite = new Sprite(texture);
        sprite.setPosition(player.GetPosition(1)-683, 0);
    }
    public void SetEnemy(SpriteBatch batch, ArrayList<AIObjects> enemyList)
    {
        int width = 0, height = 0;
        for(int i = 0 ;i < H;i++)
        {
            for(int j = 0;j <= W -1;j++)
            {
                if(Map[i][j] == 3)
                {
                    enemyList.add(new Enemy1(width , height));
                }
                width += 180;
            }
            width = 0;
            height += 60;
        }
        for(AIObjects t : enemyList )
        {
            t.Draw(batch);
        }
    }
    public void SetPasives(SpriteBatch batch, ArrayList<PasiveNpc> pasiveList)
    {
        int width = 0, height = 0;
        for(int i = 0 ;i < H;i++)
        {
            for(int j = 0;j <= W -1;j++)
            {
                if(Map[i][j] == 4)
                {
                    pasiveList.add(new PasiveNpc1(false , true, "TestNpc" , width , height));
                }
                width += 180;
            }
            width = 0;
            height += 60;
        }
        for(PasiveNpc t : pasiveList )
        {
            t.Draw(batch);
        }
    }

}