package com.pesna.init;

import com.pesna.Main;

public class GameRegistry {
	private final Main reference;
	public ItemManager itemManager;
	
	public GameRegistry( Main _reference )
	{
		reference = _reference;
		itemManager = new ItemManager( reference );
	}
	
	
	
}
