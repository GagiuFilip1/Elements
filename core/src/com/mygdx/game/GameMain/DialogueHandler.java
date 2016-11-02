package com.mygdx.game.GameMain;

import java.util.ArrayList;

/**
 * Created by Filip on 10/22/2016.
 **/
    public class DialogueHandler
{
    private ReplyHandler ReplyText;
    private int[] Lines1 , Lines2;
    private ArrayList<String> TextLines;
    private int indexC1= 0, indexC2 = 0;

    public DialogueHandler()
    {
        ReplyText = new ReplyHandler();
        TextLines = new ArrayList<String>();
    }
    /**
     *
     * @param CharId1 Name of the first character of the dialog
     * @param CharId2 Name of the second character of the dialog
     *                BuildDialog("c1" ,"c2") create the dialog between the characters
     */
  public void BuildDialogue(String CharId1 , String CharId2)
    {

        if ((indexC1 < (Lines1.length - 1)) && (indexC2 < (Lines2.length - 1)))
        {
            do
            {
                if(ReplyText.GetReplyFor(CharId1, SendChar1Line(indexC1)) != null) {
                    TextLines.add(ReplyText.GetReplyFor(CharId1, SendChar1Line(indexC1)));
                }
                else
                {
                    TextLines.add(" ");
                }
                if(ReplyText.GetReplyFor(CharId2 , SendChar2Line(indexC2)) != null) {
                    TextLines.add(ReplyText.GetReplyFor(CharId2, SendChar2Line(indexC2)));
                }
                else
                {
                    TextLines.add(" ");
                }
                if (indexC1 < Lines1.length - 1)
                {
                    indexC1++;
                }
                if (indexC2 < Lines2.length - 1)
                {
                    indexC2++;
                }
            } while ((indexC1 < (Lines1.length - 1)) && (indexC2 < (Lines2.length - 1)) );
        }
    }

    /**
     *
     * @param a and @param b , get the lines for the first character from a to b
     * @param x and @param y , get the lines for the second character from x to y
     *          GetDialogueLines( 5, 8 , 3 ,6) that means...char1 will get the lines 5 ,6 ,7, 8 and char2 lines 3 4 5 6
     */
   public void GetDialogueLines(int a , int b , int x ,int y)
    {
        int ALL_LINES1 = b - a;         int ALL_LINES2 = y - x;
        Lines1 = new int[b];    Lines2 = new int[y];
        for(int i = 0; i <= ALL_LINES1; i++)
        {
            Lines1[i] =a;
            a++;
        }
        for(int i = 0; i<= ALL_LINES2; i++)
        {
            Lines2[i] = x;
            x++;
        }
    }

    /**
     * @return List of the replys of Char1 and Char2
     */
   public ArrayList<String>Text()
    {
        return TextLines;
    }
    private int SendChar1Line(int index)
    {
        return Lines1[index];
    }
    private int SendChar2Line(int index)
    {
        return Lines2[index];
    }
}
