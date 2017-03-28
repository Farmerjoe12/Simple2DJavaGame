package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * StateMachine helps the player class with directions and animations
 * @author adamfarmelo
 *
 */
public class StateMachine {

	public static State getInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			return State.UP;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			return State.DOWN;
		} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			return State.LEFT;
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			return State.RIGHT;
		} else return State.STANDING;

	}
}
