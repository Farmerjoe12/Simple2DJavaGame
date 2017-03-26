package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Player houses all of the functionality that the player currently has to offer
 * @author adamfarmelo
 *
 */
public class Player {
	
	private SpriteBatch batch;
	private static Sprite player;
	private Animator walkUP, walkDOWN, walkLEFT, walkRIGHT;
	private Animator currAnimation = null;
	//private State state;
	private int lastDir = 0;
	
	public Player() {
		batch = new SpriteBatch();
		player = new Sprite( new Texture(Gdx.files.internal("lastguardian_all.png")) );
		player.setRegion(32*2, 0, 32, 32);
		player.setBounds(Gdx.graphics.getWidth() / 2,
						Gdx.graphics.getHeight() / 2,
						32, 32);
		
		// Animator objects are used to represent the different mvmt animations
		walkUP = new Animator(0, 1);
		walkDOWN = new Animator(2,3);
		walkLEFT = new Animator(4,5);
		walkRIGHT = new Animator(6,7);
		
	}
	
	private Animator getAnim() {
		switch (StateMachine.getState()) {
		
		case UP: {
			lastDir = 1;
			return walkUP;
		}
		case DOWN: {
			lastDir = 2;
			return walkDOWN;
		}
		case RIGHT: {
			lastDir = 3;
			return walkRIGHT;
		}
		case LEFT: {
			lastDir = 4;
			return walkLEFT;
		} default: {
			return null;
		}
		}
	}
	
	private void drawLastFrame(Animator anim) {
		batch.begin();
		batch.draw(anim.getLastFrame(), 
				Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		batch.end();
	}
	
	// The render method here will render an animation while the player is 
	//  attempting to move, and when he stops moving displays a sprite
	//  representing the last direction traveled
	public void render() {
		currAnimation = getAnim();
		if (!(currAnimation == null)) {
			currAnimation.render();
		} else {
			if (lastDir ==1 ) {
				drawLastFrame(walkUP);
			} else if (lastDir == 2) {
				drawLastFrame(walkDOWN);
			} else if (lastDir == 3) {
				drawLastFrame(walkRIGHT);
			} else if (lastDir == 4) {
				drawLastFrame(walkLEFT);
			} else drawLastFrame(walkUP);
		}
	}
	
	public static void dispose() {
		player.getTexture().dispose();
		Animator.dispose();
	}
}
