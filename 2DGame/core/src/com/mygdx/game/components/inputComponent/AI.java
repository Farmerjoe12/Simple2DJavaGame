package com.mygdx.game.components.inputComponent;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.physicsComponent.Collide;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.components.statComponent.EnemyStatComponent;
import com.mygdx.game.entities.characters.Player;

public class AI extends Component implements InputComponent {

	// input varies based on state; idle or chasing
	// if enemy is idle but player comes into range,
	// enemy will chase
	private boolean idle = true;
	private int input;
	private float num;
	private Random rand;
	private int sightRange, attackRange;
	private Vector2 playerPos;
	private int playerXTile, playerYTile;
	private Vector2 myPos;
	private int npcXTile, npcYTile;
	private int deltaX, deltaY;
	boolean moveFlag = false;
	private long lastTime;
	
	
	public AI() {
		rand = new Random();
		lastTime = TimeUtils.millis();
		sightRange = 250;
		attackRange = 32;
	}
	
	@Override
	public int handleInput() {
		playerPos = MyGdxGame.currentGame.getChild(Player.class).getComponent(Transform.class).getPosition();
		myPos = getParent().getComponent(Transform.class).getPosition();

		// checking if player is within range of enemy,
		// if search then player is found
		// if found, chase
		// else if player is lost, return to idle
		if (search(myPos, playerPos)) {
			idle = false;
			input = chase(myPos, playerPos);
		} else {
			idle = true;
		}

		// player alternates between wandering and standing every
		// **two** seconds while player is not found (idle)
		if (TimeUtils.timeSinceMillis(lastTime) > 1500 && idle){
			moveFlag = !moveFlag;
			lastTime = TimeUtils.millis();
			input = wander();
		}
		
		// move if !blocked && !idle (chasing)
		// or move if !blocked && moveFlag && idle (not chasing)
		// if player !moving (idle || blocked || !moveFlag) return -1
		if ((!getParent().getComponent(Collide.class).isBlocked(input) && !idle)
		|| (moveFlag && idle && !getParent().getComponent(Collide.class).isBlocked(input))) {
			move(input);
		} else input = -1;
		
		return input;
	}
	
	// search returns true if player is within range
	private boolean search(Vector2 myPos, Vector2 playerPos) {
		if ((Math.abs(myPos.x-playerPos.x) < sightRange) &&
			(Math.abs(myPos.y-playerPos.y) < sightRange)) {
			return true;
		}
		return false;
	}
	
	// wander generates random numbers to simulate enemy wandering about
	private int wander() {
		num = rand.nextFloat();
		if (num < .25) {
			input = 0;
		} else if (num >= .25 && num < .5) {
			input = 1;
		} else if (num >= .5 && num < .75) {
			input = 2;
		} else if (num >= .75) {
			input = 3;}
		
		return input;
	}

	// every .5 seconds calculate playerPos and change direction
	private int chase(Vector2 myPos, Vector2 playerPos) {
		npcXTile = (int)myPos.x / 32;
		npcYTile = (int)myPos.y / 32;
		
		playerXTile = (int)playerPos.x / 32;
		playerYTile = (int)playerPos.y / 32;
		
		deltaY = npcYTile-playerYTile;
		deltaX = npcXTile-playerXTile;
		
		boolean chasing = false;
		boolean attacking = false;
		
		// check if player is within attack range
		// if so, stop chasing/moving and attack
		if (Math.abs(myPos.x-playerPos.x) < attackRange && 
				Math.abs(myPos.y-playerPos.y) < attackRange) {
			chasing = false;
			attacking = true;
			attack();
			input = -1;
		}
		
		// check if enemy is not chasing and not attacking
		// if true, get new input
		// chasing is reset every *arbitrary unit of seconds*
		// to allow for direction change and simulated intelligence
		if (!chasing && !attacking) {
			lastTime = TimeUtils.millis();
			if (Math.abs(deltaY) > Math.abs(deltaX)) {
				if (deltaY < 0) {	// if deltaY is negative, player is higher 
					input = 0;		// move up
				} else input = 1;	// else move down
			} else {
				if (deltaX < 0) {	// if deltaX is negative, player is righter
					input = 3;		// move right
				} else input = 2;	// else move left
			}
			chasing = true;
		} else if (chasing){
			if (TimeUtils.timeSinceMillis(lastTime) > 2000) {
				lastTime = TimeUtils.millis();
				chasing = false;
			}
		}
			return input;
	}
	
	// clearly lackluster attack method
	private void attack() {
		System.out.println("Attacking!");
		
	}
	
	// basic move method, handled now by input instead of graphics
	private void move(int input)
	  {
		float delta = getParent().getComponent(EnemyStatComponent.class).getMoveSpeed();
		  switch (input) {
		  case -1: 
			break;
		  case 0: getParent().getComponent(Transform.class).deltaY(delta);
		  	break;
		  case 1: getParent().getComponent(Transform.class).deltaY(-delta);
		  	break;
		  case 2: getParent().getComponent(Transform.class).deltaX(-delta);
		  	break;
		  case 3: getParent().getComponent(Transform.class).deltaX(delta);
		  	break;
		  default: 
			break;
		  }
	  }
}
