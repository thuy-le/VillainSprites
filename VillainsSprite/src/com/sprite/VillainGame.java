package com.sprite;

import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.esotericsoftware.tablelayout.Cell;

public class VillainGame extends Table{

	private InfiniteScrollBg backgroundRoad;
	private long lastVillainTime = 0;
	private int villainAverageHeight = 150;
	private Array<Villain> villains;
	private int level;
	private int startPointX = -20;
	private int startPointY = 10;
	private float minSpeed = 4.0f;
	private float maxSpeed = 60.0f;
	private int lives = 3;
	private int score = 0;
	private Table liveTable;
	
	public VillainGame(int level) {
		setBounds(0, 0, 800, 480);
		setClip(true);
		this.level = level;
		if (this.level <= 2);
		else if (this.level <= 5){
			minSpeed = minSpeed * 2f;
			maxSpeed = maxSpeed * 1.5f;
		}
		else if (this.level <= 10){
			minSpeed = minSpeed * 4f;
			maxSpeed = maxSpeed * 3f;
		}
		else{
			minSpeed = minSpeed * 8f;
			maxSpeed = maxSpeed * 5f;
		}
		//Make scrolling background
		backgroundRoad = new InfiniteScrollBg(getWidth(), getHeight(), Assets.background);
		addActor(backgroundRoad);
		liveTable = new Table();
		addActor(liveTable);
		liveTable.top().right();
		liveTable.setHeight(480);
		liveTable.setWidth(800);		
		//Image heartImg = new Image(Assets.heart);
		for (int i = 0; i < lives; i++){
			Image heartImg = new Image(Assets.heart);
			heartImg.setWidth(30);
			heartImg.setHeight(30);
			liveTable.add(heartImg);
		}
		liveTable.row();
		//Label label = new Label(String.valueOf(score), new Skin());
		
		
		villains = new Array<Villain>();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		List<Cell> cells = liveTable.getCells();
		if(cells.size() <= 0) return;
		
		//Start creating villains
		if (TimeUtils.nanoTime() - lastVillainTime > 3000000000f) spawnVillain();
		
		Iterator<Villain> iter = villains.iterator();
		while (iter.hasNext()) {
			Villain villain = iter.next();
			if (villain.getBounds().x > getWidth()-80) {
				iter.remove();				
				if(cells.size() > 0){
					cells.get(cells.size()-1).setWidget(null);
					cells.remove(cells.size() - 1);
				}
				
				if(cells.size() == 0){
					//System.out.print("GAME OVER");
					Image gameOverImg = new Image(Assets.gameOver);
					addActor(gameOverImg);
				}
				removeActor(villain);
			}
		}
	}
	
	private void spawnVillain() {
		//Choosing what kind of villain to spawn
		int villainType = MathUtils.random(1, level);
		final Villain villain;
		if(villainType == 1) {
			//On ground villains
			villain = new Villain(startPointX, startPointY, villainType, minSpeed, maxSpeed);
		} else if (villainType == 2) {
			villain = new Villain(startPointX, startPointY, villainType, minSpeed, maxSpeed);
		} else {
			//Flying villains
			float yAxis = MathUtils.random(villainAverageHeight, getHeight()-villainAverageHeight);
			villain = new Villain(startPointX, yAxis, villainType, minSpeed, maxSpeed);
		}
		
		villain.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				villain.decreaseEnergyByTouch();
				if (villain.getEnergy() == 0)
					villain.eliminate(villain);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		//Add to the collection
		villains.add(villain);
		addActor(villain);
		lastVillainTime = TimeUtils.nanoTime();
	}
	
	public void simpleTab(float x, float y) {
//		Iterator<Villain> iter = villains.iterator();
//		while (iter.hasNext()) {
//			Villain villain = iter.next();
//			if (villain.getBounds().x<x && x<(villain.getBounds().x+villain.getBounds().width)
//					&& villain.getBounds().y<y && y<(villain.getBounds().y+villain.getBounds().height)) {
//				System.out.println("Enemy touch x: "+villain.getBounds().getX() + " Enemy touch y: "+villain.getBounds().getY());
//				iter.remove();
//				//villain.eliminate(villain);
//			}
//		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(Color.WHITE);
		super.draw(batch, parentAlpha);
	}
	
	public Array<Villain> getVillainsArray() {
		return this.villains;
	}
}
