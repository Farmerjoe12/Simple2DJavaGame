package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Player houses all of the functionality that the player currently has to offer
 * @author adamfarmelo
 *
 */
public class Player {
	
	SpriteBatch batch;
	private static Sprite player;
	private Animator walkUP, walkDOWN, walkLEFT, walkRIGHT;
	private Animator currAnimation = null;
	private State state;
	private int lastDir = 0;
	private int mapID = 1;
	private float mapX = Gdx.graphics.getWidth() / 2;
	private float mapY = Gdx.graphics.getHeight() / 2;
	final Vector2 position = new Vector2();
	final Vector2 speed = new Vector2();
	OrthographicCamera cam;
	
	static float MAX_SPEED = 5.0f;
	
	public Player(OrthographicCamera cam2) {
		batch = new SpriteBatch();
		player = new Sprite( new Texture(Gdx.files.internal("assets/lastguardian_all.png")) );
		player.setRegion(32*2, 0, 32, 32);
		player.setBounds(this.position.x,
						this.position.y,
						32, 32);
		cam = cam2;
		
		// Animator objects are used to represent the different mvmt animations
		walkUP = new Animator(0, 1);
		walkDOWN = new Animator(2,3);
		walkLEFT = new Animator(4,5);
		walkRIGHT = new Animator(6,7);
		
	}
	
	private Animator getAnim() {
		state = StateMachine.getInput();
		
		switch (state) {
		
		case UP: {
			lastDir = 1;
			position.y++;
			return walkUP;
		}
		case DOWN: {
			lastDir = 2;
			position.y--;
			return walkDOWN;
		}
		case RIGHT: {
			lastDir = 3;
			position.x++;
			return walkRIGHT;
		}
		case LEFT: {
			lastDir = 4;
			position.x--;
			return walkLEFT;
		} default: {
			return null;
		}
		}
	}
	
	private void drawLastFrame(Animator anim) {
		batch.setProjectionMatrix(cam.combined);
		getBatch().begin();
		getBatch().draw(anim.getLastFrame(), 
				this.position.x, this.position.y, 32, 32);
		getBatch().end();
	}
	
	// The render method here will render an animation while the player is 
	//  attempting to move, and when he stops moving displays a sprite
	//  representing the last direction traveled
	public void render() {
		currAnimation = getAnim();
		if (!(currAnimation == null)) {
			currAnimation.render(position, cam);
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

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}
}
