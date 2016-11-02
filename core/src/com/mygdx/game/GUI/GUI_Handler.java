package com.mygdx.game.GUI;

import java.util.ArrayList;

/**
 * Created by Filip on 11/2/2016.
 **/
public class GUI_Handler
{
    private  DialogueInterface DialogInterface;
    static public boolean DrawStart = false;
        public GUI_Handler()
        {
            DialogInterface =new DialogueInterface();
        }
        public void Dialog()
        {
            if(DrawStart) {
                DialogInterface.Draw();
            }
        }
}
