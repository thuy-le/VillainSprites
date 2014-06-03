package com.sprite;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameMenu implements Screen, GestureListener{

	private Stage stage;
	private Sprite logo;
	private int totalLevel = 21;
	private int levelPerPage = 21;
	private int currentLevel = 3;
	private ArrayList<ImageTextButton> menuItems;
	private InfiniteScrollBg backgroundRoad;
	
	public GameMenu(final Smasher gameEngine) {
		stage = new Stage();		
		logo = new Sprite();
		logo.setTexture(new Texture(Gdx.files.internal("logo.png")));
		TextureRegion region = new TextureRegion(logo, -100, 0, 700, 480);
		Image actor = new Image(region);
		backgroundRoad = new InfiniteScrollBg(850, 480, Assets.background2);
		stage.addActor(backgroundRoad);
		stage.addActor(actor);
		Table table = new Table();
		table.bottom().padBottom(60f);
		table.setHeight(300);
		table.setWidth(800);
        stage.addActor(table);
        menuItems = new ArrayList<ImageTextButton>();
        //icon_active
		Texture iconTexture = new Texture(Gdx.files.internal("menu_icon.png"));
		TextureRegion iconRegion = new TextureRegion(iconTexture, 0, 0, 80, 80);        
		TextureRegionDrawable icon_image = new TextureRegionDrawable(iconRegion);
        //icon_disable
		Texture iconTexture_gray = new Texture(Gdx.files.internal("menu_icon_gray.png"));
		TextureRegion iconRegion_gray = new TextureRegion(iconTexture_gray, 0, 0, 80, 80);        
		TextureRegionDrawable icon_image_gray = new TextureRegionDrawable(iconRegion_gray);
		//menu icon style
		ImageTextButtonStyle style = new ImageTextButtonStyle();
		style.up = icon_image;		
		style.disabled = icon_image_gray;
		style.font = new BitmapFont();		
		
		for(int i = 0; i < totalLevel; i++){
			String text = "Level " + (i+1);
			final int level = i + 1;
			ImageTextButton itb = new ImageTextButton(text, style);
			itb.addListener(new InputListener(){
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					// TODO Auto-generated method stub
					System.out.print("TOUCHED ICON");
					GameScreen gs = new GameScreen(level);
					gameEngine.setScreen(gs);
					return super.touchDown(event, x, y, pointer, button);
				}
			});
			menuItems.add(itb);
		}
		
		for(int i = 0; i < levelPerPage; i++){
			if((i+1) > currentLevel) menuItems.get(i).setDisabled(true);			
			table.add(menuItems.get(i)).padRight(10f).padBottom(10f);
			if((i+1)%(levelPerPage/3) == 0){
				table.row();
			}
		}

	}
	
	//Resize the screen based on WIDTH and HEIGHT
	public void resize(int width, int height) {
		stage.setViewport(Smasher.WIDTH, Smasher.HEIGHT, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override 
    public void hide() {
    	Gdx.input.setInputProcessor(null);
    }
	
	@Override public boolean fling(float velocityX, float velocityY, int button) {return false;}
	
	@Override public void resume() {}
	@Override public void pause() {}
	@Override public void dispose() {}
	
	@Override 
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}
	
	@Override public boolean touchDown(float x, float y, int pointer, int button) {return false;}
	@Override public boolean longPress(float x, float y) {return false;}
	@Override public boolean pan(float x, float y, float deltaX, float deltaY) {return false;}
	@Override public boolean zoom(float initialDistance, float distance) {return false;}
	@Override public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {return false;}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {return false;}
}
