package com.sprite;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.removeActor;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Villain extends Actor{
	
	private Rectangle bounds = new Rectangle();
	private int villainType;
	private Animation RipjawsAnimation;
	private Animation GhostsAnimation;
	private Animation Enemies6Animation;
	private TextureRegion currentFrame;
	private float stateTime;
	private int energy = 1;
	
	public Villain(float x, float y, int type, float minSpeed, float maxSpeed) {
		villainType = type;		
		setWidth(100);
		setHeight(116);
		setPosition(x, y);
		
		//special enemy cases
		switch(villainType){
			case 1:
				energy = 1;
				break;
			case 2:
				energy = 2;
				minSpeed = minSpeed + 30f;
				maxSpeed = maxSpeed + 10f;
				break;
			case 3:
				energy = 3;
				minSpeed = minSpeed - 10f;
				maxSpeed = maxSpeed - 10f;
				break;
			default:
				break;
		}		
		
		//Destination to reach, y Axis, random the speed.
		addAction(moveTo(800, getY(), MathUtils.random(minSpeed, maxSpeed)));
		
		stateTime = 0;
		RipjawsAnimation = new Animation(0.015f, Assets.RipjawsRegion);
		GhostsAnimation = new Animation(0.015f, Assets.GhostsRegion);
		Enemies6Animation = new Animation(0.015f, Assets.Enemies6Region);
	}
	
	public int getEnergy(){
		return energy;
	}
	
	public void decreaseEnergyByTouch(){
		energy = energy - 1;
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		updateBounds();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		
		if(villainType == 1) {
			stateTime += 0.001;
		//	System.out.println(stateTime);
			currentFrame = RipjawsAnimation.getKeyFrame(stateTime, true);
			batch.draw(currentFrame, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
			
		} else if(villainType == 2) {
			stateTime += 0.001;
			//System.out.println(stateTime);
			currentFrame = Enemies6Animation.getKeyFrame(stateTime, true);
			batch.draw(currentFrame, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
		} else if(villainType == 3){
			stateTime += 0.001;
			//System.out.println(stateTime);
			currentFrame = GhostsAnimation.getKeyFrame(stateTime, true);
			batch.draw(currentFrame, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
		}
		
	}

	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}
	
	public void eliminate(Villain villain) {
		clearActions();
		addAction(fadeOut(1f));		
		addAction(sequence(parallel(rotateBy(360, 1.5f), moveBy(-200, 200, 1.5f)), removeActor()));
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
	
}
