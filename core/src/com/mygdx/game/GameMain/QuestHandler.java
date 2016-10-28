package com.mygdx.game.GameMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.StringTokenizer;

/**
 * Created by Filip on 10/28/2016.
 **/
public class QuestHandler
{
    private String QUEST_LINE="";
    private void ReadQuest(String NameOfNpc ,int index)
    {
        String S = String.valueOf(index);
        FileHandle file = Gdx.files.internal("desktop/assets/Quests/AllQuests");
        StringTokenizer tokens = new StringTokenizer(file.readString());
        while (tokens.hasMoreTokens())
        {
            String token = tokens.nextToken();
            if(token.equals("*"+NameOfNpc))
            {
                token = tokens.nextToken();
                if (token.equals(S))
                {
                    QUEST_LINE = "";
                    token = tokens.nextToken();
                    if (!token.equals("|"))
                    {
                        do
                        {
                            QUEST_LINE += token + " ";
                            token = tokens.nextToken();
                        } while (!token.equals("|"));
                    }
                }
                else
                {
                    System.out.print("else");
                    while(!token.equals(S))
                    {
                        token = tokens.nextToken();
                    }
                    if (token.equals(S))
                    {
                        QUEST_LINE = "";
                        token = tokens.nextToken();
                        if (!token.equals("|"))
                        {
                            do
                            {
                                QUEST_LINE += token + " ";
                                token = tokens.nextToken();
                            } while (!token.equals("|"));
                        }
                    }
                }
            }
        }
    }
    public String GetQuest(String NameOFNpc, int index)
    {
            ReadQuest(NameOFNpc ,index);
            return QUEST_LINE;
    }
}
