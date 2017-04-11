package com.mygdx.game.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jacob on 4/7/2017.
 */
public class InputMapper {
  private HashMap<List<Integer>,Command> commands;

  public InputMapper() {
    commands = new HashMap<List<Integer>,Command>();
  }

  public Command getCommand(List<Integer> s){
    return commands.get(s);
  }

  public boolean containsCommand(List<Integer> s){
    return commands.containsKey(s);
  }

  public void mapCommand(Integer[] s, Command c){
    ArrayList<Integer> keys = new ArrayList<Integer>();
    for(int i = 0; i < s.length; i++){
      keys.add(s[i]);
    }
    commands.put(keys,c);
  }
}
