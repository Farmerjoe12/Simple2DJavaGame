package com.mygdx.game.components.inputComponent;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.mygdx.game.components.Component;
import com.mygdx.game.entities.games.Game;
import com.mygdx.game.entities.games.Simple2DJavaGameMultiplayer;
import com.mygdx.game.utilities.Command;
import com.mygdx.game.utilities.InputMapper;

/**
 * Created by Jacob on 4/7/2017.
 */
public class MultiplayerInput extends Component implements InputComponent {

  InputMapper mapper;
  Simple2DJavaGameMultiplayer parent;

  public MultiplayerInput(){
    mapper = new InputMapper();

    mapper.mapCommand(new Integer[]{Input.Keys.UP},new Command() {
      @Override
      public String executeCommand() {
        parent = ((Simple2DJavaGameMultiplayer)getParent());
        ArrayList<Integer> buttonsList = Game.listener.getKeysPressed();
        Integer[] buttons = new Integer[buttonsList.size()];
        for(int i = 0; i < buttons.length; i++){
          buttons[i] = buttonsList.get(i);
        }
        parent.getClient().sendTCP(combine(new Integer[]{parent.getClientID()},buttons));
        return "sent to server : walk up";
      }
    });
    mapper.mapCommand(new Integer[]{Input.Keys.LEFT},new Command() {
      @Override
      public String executeCommand() {
        parent = ((Simple2DJavaGameMultiplayer)getParent());
        ArrayList<Integer> buttonsList = Game.listener.getKeysPressed();
        Integer[] buttons = new Integer[buttonsList.size()];
        for(int i = 0; i < buttons.length; i++){
          buttons[i] = buttonsList.get(i);
        }
        parent.getClient().sendTCP(combine(new Integer[]{parent.getClientID()},buttons));
        return "sent to server : walk left";
      }
    });
    mapper.mapCommand(new Integer[]{Input.Keys.RIGHT},new Command() {
      @Override
      public String executeCommand() {
        parent = ((Simple2DJavaGameMultiplayer)getParent());
        ArrayList<Integer> buttonsList = Game.listener.getKeysPressed();
        Integer[] buttons = new Integer[buttonsList.size()];
        for(int i = 0; i < buttons.length; i++){
          buttons[i] = buttonsList.get(i);
        }
        parent.getClient().sendTCP(combine(new Integer[]{parent.getClientID()},buttons));
        return "sent to server : walk right";
      }
    });
    mapper.mapCommand(new Integer[]{Input.Keys.DOWN},new Command() {
      @Override
      public String executeCommand() {
        parent = ((Simple2DJavaGameMultiplayer)getParent());
        ArrayList<Integer> buttonsList = Game.listener.getKeysPressed();
        Integer[] buttons = new Integer[buttonsList.size()];
        for(int i = 0; i < buttons.length; i++){
          buttons[i] = buttonsList.get(i);
        }
        parent.getClient().sendTCP(combine(new Integer[]{parent.getClientID()},buttons));
        return "sent to server : walk down";
      }
    });
  }

  @Override
  public int handleInput() {

    ArrayList<Integer> buttonsList = Game.listener.getKeysPressed();
    if(mapper.containsCommand(buttonsList)){
      mapper.getCommand(buttonsList).executeCommand();
    }
    return 0;
  }

  private Integer[] combine(Integer[] a, Integer[] b){
    Integer[] toRet = new Integer[a.length+b.length];
    for(int i = 0; i < a.length; i++) {
      toRet[i] = a[i];
    }
    for(int i = a.length; i < toRet.length; i++) {
      toRet[i] = b[i-a.length];
    }
    return toRet;
  }
}
