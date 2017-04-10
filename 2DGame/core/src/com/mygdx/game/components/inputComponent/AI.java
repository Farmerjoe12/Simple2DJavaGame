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
	int input;
	float num;
	private Random rand;
	int range = 50;
	Vector2 playerPos;
	Vector2 myPos;
	Vector2 diff;
	boolean moveFlag = false;
	long lastTime;
	
	public AI() {
		rand = new Random();
		lastTime = TimeUtils.millis();
	}
	
	@Override
	public int handleInput() {
		playerPos = MyGdxGame.simpleGame.getChild(Player.class).getComponent(Transform.class).getPosition();
		myPos = getParent().getComponent(Transform.class).getPosition();

		// checking if player is within range of enemy,
		// if search then player is found
		// if found, chase
		if (search(myPos, playerPos)) {
			idle = false;
			input = chase(myPos, playerPos);
		}

		// player alternates between wandering and standing every
		// **two** seconds while player is not found (idle)
		if (TimeUtils.timeSinceMillis(lastTime) > 2000 && idle){
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
		if ((Math.abs(myPos.x-playerPos.x) < range) &&
			(Math.abs(myPos.y-playerPos.y) < range)) {
			return true;
		}
		return false;
	}
	private int wander() {
		System.out.println("wandering");
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
	
	private int chase(Vector2 myPos, Vector2 playerPos) {
		// every .5 seconds calculate playerPos and change direction
		diff = myPos.sub(playerPos);
		if (TimeUtils.timeSinceMillis(lastTime) > 500) {
			if (diff.y > 0) {
				input = 0;
			} else if (diff.y < 0) {
				input = 1;
			} else if (diff.x > 0) {
				input = 2;
			} else if (diff.x < 0) {
				input = 3;
			} else {
				input = -1;
				System.out.println("Caught player");
			}
			lastTime = TimeUtils.millis();
		} else {
			System.out.println("Player found");
		}
			return input;
	}
	
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
