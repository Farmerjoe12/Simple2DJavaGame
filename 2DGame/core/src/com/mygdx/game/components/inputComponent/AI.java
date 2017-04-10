package com.mygdx.game.components.inputComponent;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.components.Component;

public class AI extends Component implements InputComponent {

	// input varies based on state; idle or chasing
	// if enemy is idle but player comes into range,
	// enemy will chase; hence @param playerFound
	private boolean idle = true;
	private boolean playerFound = false;
	int input;
	float num;
	private Random rand;
	
	public AI() {
		rand = new Random();
	}
	
	@Override
	public int handleInput() {

		if (idle) {
			input = -1;
		} else {
			num = rand.nextFloat();
			if (num < .25) {
				input = 0;
			} else if (num >= .25&& num < .5) {
				input = 1;
			} else if (num >= .5 && num < .75) {
				input = 2;
			} else if (num >= .75) {
				input = 3;}
		}// end main if/else
		return input;
	}
	
	public void chase() {
		
	}
}
