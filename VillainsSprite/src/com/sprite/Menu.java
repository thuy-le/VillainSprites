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

public class Menu implements Screen, GestureListener{

	private Stage stage;
	private Sprite logo;
	private ArrayList<ImageTextButton> menuItems;
	private InfiniteScrollBg backgroundRoad;
	
	public Menu(final Smasher gameEngine, final GameMenu gm) {
		stage = new Stage();
		logo = new Sprite();
		logo.setTexture(new Texture(Gdx.files.internal("logo.png")));
		TextureRegion region = new TextureRegion(logo, -100, 0, 700, 480);
		Image actor = new Image(region);
		backgroundRoad = new InfiniteScrollBg(850, 480, Assets.background2);
		stage.addActor(backgroundRoad);
		stage.addActor(actor);
		Table table = new Table();
		table.bottom().padBottom(50f);
		table.setHeight(300);
		table.setWidth(800);
        stage.addActor(table);
        menuItems = new ArrayList<ImageTextButton>();
        
        //icon_active_start
		Texture iconTexture_start = new Texture(Gdx.files.internal("start_btn.png"));
		TextureRegion iconRegion_start = new TextureRegion(iconTexture_start, 0, 0, 200, 120);        
		TextureRegionDrawable icon_image_start = new TextureRegionDrawable(iconRegion_start);
		ImageTextButtonStyle start_style = new ImageTextButtonStyle();
		start_style.up = icon_image_start;		
		start_style.font = new BitmapFont();
		ImageTextButton itb_start = new ImageTextButton("", start_style);
		itb_start.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub				
				System.out.print("TOUCHED");
				gameEngine.setScreen(gm);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		menuItems.add(itb_start);

        //icon_active_help
		Texture iconTexture_help = new Texture(Gdx.files.internal("help_btn.png"));
		TextureRegion iconRegion_help = new TextureRegion(iconTexture_help, 0, 0, 200, 120);        
		TextureRegionDrawable icon_image_help = new TextureRegionDrawable(iconRegion_help);
		ImageTextButtonStyle help_style = new ImageTextButtonStyle();
		help_style.up = icon_image_help;		
		help_style.font = new BitmapFont();
		ImageTextButton itb_help = new ImageTextButton("", help_style);						
		menuItems.add(itb_help);
		
        //icon_active_exit
		Texture iconTexture_exit = new Texture(Gdx.files.internal("exit_btn.png"));
		TextureRegion iconRegion_exit = new TextureRegion(iconTexture_exit, 0, 0, 200, 120);        
		TextureRegionDrawable icon_image_exit = new TextureRegionDrawable(iconRegion_exit);
		ImageTextButtonStyle exit_style = new ImageTextButtonStyle();
		exit_style.up = icon_image_exit;		
		exit_style.font = new BitmapFont();
		ImageTextButton itb_exit = new ImageTextButton("", exit_style);						
		menuItems.add(itb_exit);
		
		for(int i = 0; i < menuItems.size(); i++){			
			table.add(menuItems.get(i)).padRight(10f).padBottom(10f);			
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
		//Gdx.input.setInputProcessor(new GestureDetector(this));
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
