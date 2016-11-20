package com.pesna.init;

import java.util.ArrayList;

import com.pesna.Main;
import com.pesna.screens.ErrorScreen;
import com.pesna.screens.GameScreen;
import com.pesna.screens.IScreen;
import com.pesna.screens.LoadingScreen;

public class ScreenManager {
	public ArrayList<IScreen> screens = new ArrayList<IScreen>();
	private IScreen currentScreen;
	public IScreen queuedScreen;//This variable ensures correct screen-changing
	private final Main reference;
	
	public IScreen loadingScreen, errorScreen, gameScreen;
	
    
	public ScreenManager( Main _reference )
	{
		reference = _reference;
		loadingScreen = new LoadingScreen();
		errorScreen = new ErrorScreen();
		gameScreen = new GameScreen(_reference);
		
		currentScreen = loadingScreen; // You start with the loading screen
		queuedScreen = currentScreen;
	}
	
	public void draw(){currentScreen.draw(reference); currentScreen = queuedScreen;}
	public void update(){currentScreen.update(reference);}
	
	public void onAssetsLoaded()
	{
		((GameScreen)gameScreen).onAssetsLoaded();
	}
	
	public void queueScreen( IScreen newScreen ){queuedScreen = newScreen;}
}
