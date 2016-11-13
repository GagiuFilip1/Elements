package com.pesna.init;

import java.util.ArrayList;

import com.pesna.Main;
import com.pesna.screens.ErrorScreen;
import com.pesna.screens.GameScreen;
import com.pesna.screens.IScreen;
import com.pesna.screens.LoadingScreen;

public class ScreenManager {
	public ArrayList<IScreen> screens = new ArrayList<IScreen>();
	public IScreen currentScreen;
	private final Main reference;
	
	public final IScreen loadingScreen, errorScreen, gameScreen;
	
    
	public ScreenManager( Main _reference )
	{
		reference = _reference;
		loadingScreen = new LoadingScreen();
		errorScreen = new ErrorScreen();
		gameScreen = new GameScreen();
		
		currentScreen = loadingScreen; // You start with the loading screen
	}
	
	public void draw(){currentScreen.draw(reference);}
	public void update(){currentScreen.update(reference);}
	
	public void setScreen( IScreen newScreen )
	{
		currentScreen = newScreen;
	}
	
}
