package com.mygdx.game.components.inputComponent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.physicsComponent.Collide;
import com.mygdx.game.components.physicsComponent.Transform;

/**
 * Created by Jacob on 3/29/2017.
 */
public class PlayerInput extends Component implements InputComponent {

  // @param deltaVal controls the amount that the player moves every frame
  // while recieving input
  private float deltaVal = 1f; //getParent().getComponent(Collide.class).checkMapBounds();
  public static final int UP = 0;
  public static final int DOWN = 1;
  public static final int LEFT = 2;
  public static final int RIGHT = 3;

  @Override
  public int handleInput() {
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      getParent().getComponent(Transform.class).deltaY(getParent().getComponent(Collide.class).checkMapBounds());
      return UP;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      getParent().getComponent(Transform.class).deltaY(-(getParent().getComponent(Collide.class).checkMapBounds()));
      return DOWN;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      getParent().getComponent(Transform.class).deltaX(-(getParent().getComponent(Collide.class).checkMapBounds()));
      return LEFT;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      getParent().getComponent(Transform.class).deltaX(getParent().getComponent(Collide.class).checkMapBounds());
      return RIGHT;
    }

    return -1;
  }

  public boolean nothingPressed() {
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      return false;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      return false;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      return false;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      return false;
    }
    return true;
  }
}
