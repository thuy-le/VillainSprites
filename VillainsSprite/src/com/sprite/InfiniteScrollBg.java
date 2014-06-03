package com.sprite;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class InfiniteScrollBg extends Actor{

	private Texture background;
	public InfiniteScrollBg(float width, float height, Texture background) {
		setWidth(width);
		setHeight(height);
		setPosition(getWidth(), 0);
		this.background = background;
		/**
		 * 10 frames for it to move with suitable speed, higher frames means the background moves slowly,
		 * small frames then the background moves fast which means skipping the image.
		 * 
		 * getX() means reaching the end of the photo then reloop the image.
		 */
		addAction(forever(sequence(moveTo(0, 0, 10f), moveTo(getX(), 0))));
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		/**
		 * getX()-getWidth() means moving from the entrance of image - getX() to the width of the image
		 * getY() returns 0
		 */
		batch.draw(background, getX()-getWidth(), getY(), getWidth() * 2, getHeight());		
	}
}
