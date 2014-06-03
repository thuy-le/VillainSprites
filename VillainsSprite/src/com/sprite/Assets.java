package com.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	public static Texture[] Ripjaws;
	public static Texture[] Ghosts;
	public static Texture[] Enemies6;
	public static Texture background;
	public static Texture background2;		
	public static TextureRegion heart;
	public static TextureRegion gameOver;
	public static TextureRegion[] RipjawsRegion;
	public static TextureRegion[] GhostsRegion;
	public static TextureRegion[] Enemies6Region;
	public static TextureRegion backgroundRegion;

	public static void load() {
		
		//Removes ower of 2 rules for images
		Texture.setEnforcePotImages(false);
		
		Ripjaws = new Texture[4];
		Ripjaws[0] = new Texture(Gdx.files.internal("Ripjaws/Ripjaws1.png"));
		Ripjaws[1] = new Texture(Gdx.files.internal("Ripjaws/Ripjaws2.png"));
		Ripjaws[2] = new Texture(Gdx.files.internal("Ripjaws/Ripjaws3.png"));
		Ripjaws[3] = new Texture(Gdx.files.internal("Ripjaws/Ripjaws4.png"));
		//Ripjaws[4] = new Texture(Gdx.files.internal("Ripjaws/Ripjaws_die1.png"));
		//Ripjaws[5] = new Texture(Gdx.files.internal("Ripjaws/Ripjaws_die2.png"));
		RipjawsRegion= new TextureRegion[4];;
		RipjawsRegion[0] = new TextureRegion(Ripjaws[0]);
		RipjawsRegion[1] = new TextureRegion(Ripjaws[1]);
		RipjawsRegion[2] = new TextureRegion(Ripjaws[2]);
		RipjawsRegion[3] = new TextureRegion(Ripjaws[3]);
		//RipjawsRegion[4] = new TextureRegion(Ripjaws[4]);
		//RipjawsRegion[5] = new TextureRegion(Ripjaws[5]);
		
		
		Ghosts = new Texture[5];
		Ghosts[0] = new Texture(Gdx.files.internal("Ghosts/Ghost1.png"));
		Ghosts[1] = new Texture(Gdx.files.internal("Ghosts/Ghost2.png"));
		Ghosts[2] = new Texture(Gdx.files.internal("Ghosts/Ghost3.png"));
		Ghosts[3] = new Texture(Gdx.files.internal("Ghosts/Ghost4.png"));
		Ghosts[4] = new Texture(Gdx.files.internal("Ghosts/Ghost5.png"));
		//Ghosts[5] = new Texture(Gdx.files.internal("Ghosts/Ghost_die1.png"));
		GhostsRegion= new TextureRegion[5];;
		GhostsRegion[0] = new TextureRegion(Ghosts[0]);
		GhostsRegion[1] = new TextureRegion(Ghosts[1]);
		GhostsRegion[2] = new TextureRegion(Ghosts[2]);
		GhostsRegion[3] = new TextureRegion(Ghosts[3]);
		GhostsRegion[4] = new TextureRegion(Ghosts[4]);
		//GhostsRegion[5] = new TextureRegion(Ghosts[5]);
		
		Enemies6 = new Texture[4];
		Enemies6[0] = new Texture(Gdx.files.internal("Enemy6/enemy06a.png"));
		Enemies6[1] = new Texture(Gdx.files.internal("Enemy6/enemy06b.png"));
		Enemies6[2] = new Texture(Gdx.files.internal("Enemy6/enemy06c.png"));
		Enemies6[3] = new Texture(Gdx.files.internal("Enemy6/enemy06d.png"));
		Enemies6Region= new TextureRegion[4];;
		Enemies6Region[0] = new TextureRegion(Enemies6[0]);
		Enemies6Region[1] = new TextureRegion(Enemies6[1]);
		Enemies6Region[2] = new TextureRegion(Enemies6[2]);
		Enemies6Region[3] = new TextureRegion(Enemies6[3]);
		
		background = new Texture(Gdx.files.internal("citybackground.png"));
		backgroundRegion = new TextureRegion(background);
		background2 = new Texture(Gdx.files.internal("dark_forest.png"));
		heart = new TextureRegion(new Texture(Gdx.files.internal("heart.png")));
		gameOver = new TextureRegion(new Texture(Gdx.files.internal("gameover.png")));
	}

	public static void dispose() {
		for(int i=0; i<Ripjaws.length; i++) {
			Ripjaws[i].dispose();
		}
		for(int i=0; i<Ghosts.length; i++) {
			Ghosts[i].dispose();
		}
		for(int i=0; i<Enemies6.length; i++) {
			Enemies6[i].dispose();
		}
		background.dispose();
		background2.dispose();
	}
}
