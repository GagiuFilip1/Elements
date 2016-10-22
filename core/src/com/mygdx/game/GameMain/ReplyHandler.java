package com.mygdx.game.GameMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.StringTokenizer;

public class ReplyHandler
{
    private String Character = "", Line="";
    public void GetReplyFor(String Name , int id)
    {
        FileHandle file = Gdx.files.internal("desktop/assets/Text/AllText");
        StringTokenizer tokens = new StringTokenizer(file.readString());
        while(tokens.hasMoreTokens())
        {
            String token = tokens.nextToken();
            if(token.contains("*") && token.equals("*"+Name)) {
               Character = GetNPC_Name(token) + " ";
               Character = Character.replace("*"+Name , Name);
           }
           if(token.equals(String.valueOf(id)))
           {
               token = tokens.nextToken();
               while(!token.equals("|"))
                {
                    Line += token + " ";
                    token = tokens.nextToken();
                }
           }
        }
        System.out.print("\n"+CreateReply());
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
