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

    /**
     *
     * @param Name The name of the character we want to get a reply from
     * @param id the id of the reply we want our character to say
     *           GetReplyFor(name , id) add the name and the id of a reply you want to display
     */
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
