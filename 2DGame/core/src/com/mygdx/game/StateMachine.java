package com.mygdx.game;

/**
 * StateMachine helps the player class with directions and animations
 * @author adamfarmelo
 *
 */
public class StateMachine {
	
	public static enum State {

		STANDING,
		UP,
		DOWN,
		LEFT,
		RIGHT;
		
	}
	
	public static State getState() {
		
		if (InputHandler.getInput() == InputHandler.UP) {	return State.UP; }
		if (InputHandler.getInput() == InputHandler.DOWN) { return State.DOWN; }
		if (InputHandler.getInput() == InputHandler.LEFT) { return State.LEFT; }
		if (InputHandler.getInput() == InputHandler.RIGHT) { return State.RIGHT; }
		return State.STANDING;

	}
}
