package com.mygdx.game;

<<<<<<< HEAD
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
=======
import com.badlogic.gdx.graphics.OrthographicCamera;
>>>>>>> playerUpgrade
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Player houses all of the functionality that the player currently has to offer
 * @author adamfarmelo
 *
 */
public class Player {
	
<<<<<<< HEAD
	SpriteBatch batch;
	private static Sprite player;
	private Animator walkUP, walkDOWN, walkLEFT, walkRIGHT;
	private Animator currAnimation = null;
	private int lastDir = 0;
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
=======
	private SpriteBatch batch;
	/** SpriteRow holds the row of sprites on the spriteSheet, spriteSet is
	 *  which set of sprites to use. A set is all 6 sprites that make up one char's
	 *  complete moveset. 
	 *  Ex: row 0 set 0 = Light Male Wizard, row 0 set 1 = Dark Male Wizard
	 *  row 1 set 0 = Light Male Swordsman, row 1 set 1 = Dark Male Swordsman	 
	 *  
	 *  MAX ROW VAL = 21 FOR FULL ROW 22 IS LAST SET
	 *  MAX SET VAL = 4
	 *  
	 *  */
	private int spriteRow = 22, spriteSet = 0;
	
	// Animator init variables
	private Animator currAnimation = null;
	private int lastDir = -1;	
	private Animator[] animSet;
	private int input;
	
	// camera and speed variables
	final Vector2 position = new Vector2();
	final Vector2 speed = new Vector2();
	private OrthographicCamera cam;
	static float MAX_SPEED = 5.0f;
	
	public Player(OrthographicCamera cam2) {
		batch = new SpriteBatch();
		animSet = makeAnims(spriteRow, spriteSet);
>>>>>>> playerUpgrade
		cam = cam2;
		
	}
	
	private Animator[] makeAnims(int row, int set) {
		// Animator objects are used to represent the different mvmt animations
		
		/** Make an array of animations using the parameters passed from player
		 *  Player calls makeAnims(row, set)
		 *  makeAnims returns Animation[0,1,2,3] which is an array of directional
		 *  Animations
		 *  getAnim(getInput()) is called and the index of the array corresponding
		 *  with input is returned
		 */
		
		int startSprite = set*8;
		int endSprite = startSprite+1;
		
		animSet = new Animator[4];
		animSet[0] = new Animator(row, startSprite, endSprite);		// UP
		animSet[1] = new Animator(row, startSprite+2, endSprite+2);	// DOWN
		animSet[2] = new Animator(row, startSprite+4, endSprite+4);	// LEFT
		animSet[3] = new Animator(row, startSprite+6, endSprite+6);	// RIGHT	
		
		return animSet;
	}
	
<<<<<<< HEAD
	private Animator getAnim() {
		
		switch (StateMachine.getState()) {
		
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
=======
	private Animator getAnim(int input, Animator[] animSet) {
		if (input >= 0) {
			lastDir = input;
			return animSet[input];
		} else return null;
	}
	
	private void drawLastFrame(int spriteDirection) {
		batch.setProjectionMatrix(cam.combined);
		getBatch().begin();
		if (spriteDirection < 0) {
			getBatch().draw(animSet[0].getLastFrame(), 
					this.position.x, this.position.y, 32, 32);
		} else {
			getBatch().draw(animSet[spriteDirection].getLastFrame(), 
					this.position.x, this.position.y, 32, 32);
		}
		getBatch().end();
	}
	
	private void move(int input) {
		switch (input) {
		case 0: {			//UP
			position.y++;
			break;
		}
		case 1: {
			position.y--;	//DOWN
			break;
		}
		case 2: {
			position.x--;	//LEFT
			break;
		}
		case 3: {
			position.x++;	//RIGHT
			break;
>>>>>>> playerUpgrade
		}
		}
	}
	
<<<<<<< HEAD
	private void drawLastFrame(Animator anim) {
		batch.setProjectionMatrix(cam.combined);
		getBatch().begin();
		getBatch().draw(anim.getLastFrame(), 
				this.position.x, this.position.y, 32, 32);
		getBatch().end();
	}
	
=======
>>>>>>> playerUpgrade
	// The render method here will render an animation while the player is 
	//  attempting to move, and when he stops moving displays a sprite
	//  representing the last direction traveled
	public void render() {
		input = InputHandler.getInput();
		currAnimation = getAnim(input, animSet);
		if (!(currAnimation == null)) {
<<<<<<< HEAD
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
=======
				move(input);
				currAnimation.render(position, cam);
		} else {drawLastFrame(lastDir);
		} 
>>>>>>> playerUpgrade
	}
	
	public static void dispose() {
		Animator.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}
	
	public void setLoc(int x, int y)
	{
		this.position.x = (float)x * 32;
		this.position.y = (float)y * 32;
	}
}
