package com.sprite;

import com.badlogic.gdx.Game;

public class Smasher extends Game {
	
	public final static int WIDTH = 800;
	public final static int HEIGHT = 480;
	//private GameScreen gameScreen;
	private GameMenu gameMenu;
	private Menu menu;
	
	@Override
	public void create() {
		
		Assets.load();
		//gameScreen = new GameScreen();
		gameMenu = new GameMenu(this);
		menu = new Menu(this, gameMenu);
		//setScreen(gameScreen);
		//setScreen(gameMenu);
		setScreen(menu);
	}

	@Override
	public void dispose() {
		Assets.dispose();
		//gameScreen.dispose();
		gameMenu.dispose();
		menu.dispose();
	}
}
