package com.mygdx.game.components.inputComponent;

import com.badlogic.gdx.Input;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.utilities.InputMapper;
import com.mygdx.game.utilities.Command;
import java.util.ArrayList;

/**
 * Created by Jacob on 3/29/2017.
 */
public class PlayerInput extends Component implements InputComponent {

  InputMapper mapper;
  // @param deltaVal controls the amount that the player moves every frame
  // while recieving input
  private float deltaVal = 1f;
  public static final int UP = 0;
  public static final int DOWN = 1;
  public static final int LEFT = 2;
  public static final int RIGHT = 3;
  int currentDirection = 0;

  public PlayerInput() {
    mapper = new InputMapper();
    mapper.mapCommand(new Integer[]{Input.Keys.UP},new Command() {
      @Override
      public String executeCommand() {
        getParent().getComponent(Transform.class).deltaY(deltaVal);
        currentDirection = UP;
        return "walk-up";
      }
    });
    mapper.mapCommand(new Integer[]{Input.Keys.LEFT},new Command() {
      @Override
      public String executeCommand() {
        getParent().getComponent(Transform.class).deltaX(-deltaVal);
        currentDirection = LEFT;
        return "walk-left";
      }
    });
    mapper.mapCommand(new Integer[]{Input.Keys.RIGHT},new Command() {
      @Override
      public String executeCommand() {
        getParent().getComponent(Transform.class).deltaX(deltaVal);
        currentDirection = RIGHT;
        return "walk-right";
      }
    });
    mapper.mapCommand(new Integer[]{Input.Keys.DOWN},new Command() {
      @Override
      public String executeCommand() {
        getParent().getComponent(Transform.class).deltaY(-deltaVal);
        currentDirection = DOWN;
        return "walk-down";
      }
    });
  }

  @Override
  public int handleInput() {

    ArrayList<Integer> buttonsList = MyGdxGame.listener.getKeysPressed();
    String command;
    if(mapper.containsCommand(buttonsList)){
      command = mapper.getCommand(buttonsList).executeCommand();
      if(command.equals("walk-left")){
        return LEFT;
      }
      if(command.equals("walk-right")){
        return RIGHT;
      }
      if(command.equals("walk-up")){
        return UP;
      }
      if(command.equals("walk-down")){
        return DOWN;
      }
    } else {
      //System.out.println("mapper doesnt contain current button array");
    }

    return -1;
  }

  public InputMapper getMapper(){
    return mapper;
  }

  public int getCurrentDirection() {
    return currentDirection;
  }
}
