package com.mygdx.game.components.inputComponent;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.mygdx.game.components.Component;


/**
 * Created by Jacob on 3/29/2017.
 */
public class PlayerInput extends Component implements InputComponent {

  // @param deltaVal controls the amount that the player moves every frame
  // while recieving input
  public static final int UP = 0;
  public static final int DOWN = 1;
  public static final int LEFT = 2;
  public static final int RIGHT = 3;
  public static boolean nothingPressed = true;

  @Override
  public int handleInput() {
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      nothingPressed = false;
      return UP;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      nothingPressed = false;
      return DOWN;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      nothingPressed = false;
      return LEFT;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      nothingPressed = false;
      return RIGHT;
    }

    nothingPressed = true;
    return -1;
  }
}
