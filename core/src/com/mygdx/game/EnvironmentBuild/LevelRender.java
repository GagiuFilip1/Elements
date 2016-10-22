package com.mygdx.game.EnvironmentBuild;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.Abstracts.ObjectsInGame;

import com.mygdx.game.GameMain.ReplyHandler;
import com.mygdx.game.GameObjects.Brick;
import com.mygdx.game.GameObjects.Player;

public class LevelRender
{
    private int H , W , alg = 0;
    private int[][] Map;
    private ReplyHandler Reply;
    private ArrayList<ObjectsInGame> objectsList;
    private static Texture texture = new Texture(Gdx.files.internal("desktop/assets/Backgrounds/FireBack.jpg"));
    public static Sprite sprite = new Sprite(texture);
    private boolean stop = false;

    public LevelRender()
    {
        Reply = new ReplyHandler();
        objectsList = new ArrayList<ObjectsInGame>();
    }

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
            Reply.GetReplyFor("Hero" , 1);
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
    public ArrayList<ObjectsInGame> GetObjectsList()
    {
        return objectsList;
    }
    public void SetBackground(Player player)
    {
        Sprite sprite = new Sprite(texture);
        sprite.setPosition(player.GetPosition(1)-683, 0);
    }
}