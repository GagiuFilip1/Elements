package com.mygdx.game.GUI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.GameMain.Control;
import com.mygdx.game.GameMain.MainClass;


import java.util.ArrayList;


/**
 * Created by Filip on 11/1/2016.
 **/
public class DialogueInterface
{
    private MainClass main;
    private SpriteBatch batch;
    private Texture Bodytexture , CharTexture;
    private Sprite Bodysprite , CharSprite;
    private BitmapFont font;
    private GUI_Handler gui;
    private ShapeRenderer Shapes;
    private int index = 0;
    private ArrayList<String> Lines;
    private String text = "       ";
    private Control keys;
    public boolean Should_Stop = false;
    public boolean Should_Draw = true;
    public DialogueInterface()
        {
            keys = new Control();
            main = new MainClass();
            batch = main.batch;
           // Bodytexture = new Texture(Gdx.files.internal(""));
           // CharTexture = new Texture(Gdx.files.internal(""));
           // Bodysprite = new Sprite(Bodytexture , 300 , 0,900,300);
           // CharSprite = new Sprite(CharTexture ,0 ,0 ,300,300);
            Shapes = new ShapeRenderer();
            font = new BitmapFont();
            Lines = new ArrayList<String>();
        }
        private void Create()
        {
               Shapes.begin(ShapeRenderer.ShapeType.Filled);
               Shapes.setColor(Color.WHITE);
               Shapes.rect(0, 0, 300, 300);
               //Shapes.setProjectionMatrix(main.camera.combined);
               Shapes.end();
               // CharSprite.draw(main.batch);
               // Bodysprite.draw(main.batch);
        }
        private void Update()
        {
            if(index < Lines.size())
            {
                text = Lines.get(index);
                //font.draw(main.batch, text , Bodysprite.getX() , Bodysprite.getY());
                index++;
                keys.start = false;
            }
            System.out.print("\n" + index + "\n");
            if(index >= Lines.size())
            {
                Should_Stop = true;
                System.out.print("aha");
                Destroy();
            }
        }
        public boolean Destroy()
        {
            return Should_Stop;
        }
        public void Start(ArrayList<String> Text)
        {
            Lines = Text;
            if(!keys.start)
            {
                Draw();
                System.out.print(keys.start);
                Update();
            }
        }
        public void Draw()
        {
                Create();
        }
}
