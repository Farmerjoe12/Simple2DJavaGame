package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputHandler {

	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	public static int getInput() {
		
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) { return UP; }
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) { return DOWN; }
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) { return LEFT; }
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) { return RIGHT; }
		
		return -1;
	}
}