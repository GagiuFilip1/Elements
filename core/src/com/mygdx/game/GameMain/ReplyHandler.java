package com.mygdx.game.GameMain;
/**
 * Created by Filip on 10/22/2016
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.StringTokenizer;

public class ReplyHandler
{
    private String Character = "", Line="";
    void GetReplyFor(String Name, int id)
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
                token = tokens.nextToken();
                if (!token.equals("|")) {
                    do {
                        Line += token + " ";
                        token = tokens.nextToken();
                    } while (!token.equals("|"));
                }
                if(token.equals("|") && Line != "")
                {
                    System.out.print(CreateReply());
                    Line = "";;
                }
            }
        }
    }
    private String CreateReply()
    {
        return Character + Line;
    }
    private String GetNPC_Name(String aux)
    {
        return aux;
    }
}
