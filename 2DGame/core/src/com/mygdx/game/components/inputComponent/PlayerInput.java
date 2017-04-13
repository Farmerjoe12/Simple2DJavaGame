package com.mygdx.game.components.inputComponent;


import com.badlogic.gdx.Gdx;


import com.badlogic.gdx.Input;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.Component;

import com.mygdx.game.components.physicsComponent.Collide;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.components.statComponent.PlayerStatComponent;
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
        float deltaVal = getParent().getComponent(PlayerStatComponent.class).getMoveSpeed();
        currentDirection = UP;
        if(!getParent().getComponent(Collide.class).isBlocked(currentDirection)){
          getParent().getComponent(Transform.class).deltaY(deltaVal);
        }

        return "walk-up";
      }
    });
    mapper.mapCommand(new Integer[]{Input.Keys.LEFT},new Command() {
      @Override
      public String executeCommand() {
        float deltaVal = getParent().getComponent(PlayerStatComponent.class).getMoveSpeed();
        currentDirection = LEFT;
        if(!getParent().getComponent(Collide.class).isBlocked(currentDirection)){
          getParent().getComponent(Transform.class).deltaX(-deltaVal);
        }

        return "walk-left";
      }
    });
    mapper.mapCommand(new Integer[]{Input.Keys.RIGHT},new Command() {
      @Override
      public String executeCommand() {
        float deltaVal = getParent().getComponent(PlayerStatComponent.class).getMoveSpeed();
        currentDirection = RIGHT;
        if(!getParent().getComponent(Collide.class).isBlocked(currentDirection)){
          getParent().getComponent(Transform.class).deltaX(deltaVal);
        }

        return "walk-right";
      }
    });
    mapper.mapCommand(new Integer[]{Input.Keys.DOWN},new Command() {
      @Override
      public String executeCommand() {
        float deltaVal = getParent().getComponent(PlayerStatComponent.class).getMoveSpeed();
        currentDirection = DOWN;
        if(!getParent().getComponent(Collide.class).isBlocked(currentDirection)){
          getParent().getComponent(Transform.class).deltaY(-deltaVal);
        }

        return "walk-down";
      }
    });
    mapper.mapCommand(new Integer[]{Input.Keys.UP,Input.Keys.SHIFT_LEFT},new Command() {
      @Override
      public String executeCommand() {
        float deltaVal = getParent().getComponent(PlayerStatComponent.class).getMoveSpeed();
        currentDirection = UP;
        if(!getParent().getComponent(Collide.class).isBlocked(currentDirection)){
          getParent().getComponent(Transform.class).deltaY(2*deltaVal);
        }

        return "walk-up";
      }
    });
    mapper.mapCommand(new Integer[]{Input.Keys.LEFT,Input.Keys.SHIFT_LEFT},new Command() {
      @Override
      public String executeCommand() {
        float deltaVal = getParent().getComponent(PlayerStatComponent.class).getMoveSpeed();
        currentDirection = LEFT;
        if(!getParent().getComponent(Collide.class).isBlocked(currentDirection)){
          getParent().getComponent(Transform.class).deltaX(-2*deltaVal);
        }

        return "walk-left";
      }
    });
    mapper.mapCommand(new Integer[]{Input.Keys.RIGHT,Input.Keys.SHIFT_LEFT},new Command() {
      @Override
      public String executeCommand() {
        float deltaVal = getParent().getComponent(PlayerStatComponent.class).getMoveSpeed();
        currentDirection = RIGHT;
        if(!getParent().getComponent(Collide.class).isBlocked(currentDirection)){
          getParent().getComponent(Transform.class).deltaX(2*deltaVal);
        }

        return "walk-right";
      }
    });
    mapper.mapCommand(new Integer[]{Input.Keys.DOWN,Input.Keys.SHIFT_LEFT},new Command() {
      @Override
      public String executeCommand() {
        float deltaVal = getParent().getComponent(PlayerStatComponent.class).getMoveSpeed();
        currentDirection = DOWN;
        if(!getParent().getComponent(Collide.class).isBlocked(currentDirection)){
          getParent().getComponent(Transform.class).deltaY(-2*deltaVal);
        }

        return "walk-down";
      }
    });
  }

  @Override
  public int handleInput() {
    ArrayList<Integer> buttonsList = MyGdxGame.listener.getKeysPressed();
    if(mapper.containsCommand(buttonsList)){
      mapper.getCommand(buttonsList).executeCommand();
      return currentDirection;
    } else {
      //To-Do : check to see if there is a subsection of buttons pressed that is a relevant command
      //          and check from context what attempted command is most probable
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
