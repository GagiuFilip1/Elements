package com.mygdx.game.GameMain;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.StringTokenizer;
/**
 * Created by Filip on 10/22/2016
 */
class ReplyHandler
{
    private String Character = "", Line="";
    /**
     * @param Name The name of the character we want to get a reply from
     * @param id the id of the reply we want our character to say
     *           GetReplyFor(name , id) add the name and the id of a reply you want to display
     */
    String GetReplyFor(String Name, int id)
    {
        FileHandle file = Gdx.files.internal("desktop/assets/Text/AllText");
        StringTokenizer tokens = new StringTokenizer(file.readString());
        while (tokens.hasMoreTokens())
        {
            String token = tokens.nextToken();
            if (token.contains("*") && token.equals("*" + Name))
            {
                Character = GetNPC_Name(token) + " ";
                Character = Character.replace("*" + Name, Name);
            }
            if (token.equals(String.valueOf(id)))
            {
                Line = "";
                token = tokens.nextToken();
                if (!token.equals("|"))
                {
                    do
                    {
                        Line += token + " ";
                        token = tokens.nextToken();
                    } while (!token.equals("|"));
                }
                if(token.equals("|") && !Line.equals(""))
                {
                    return CreateReply();
                }
            }
        }
      return null;
    }
    private String CreateReply()
    {
        return Line;
    }
    private String GetNPC_Name(String aux)
    {
        return aux;
    }
}
