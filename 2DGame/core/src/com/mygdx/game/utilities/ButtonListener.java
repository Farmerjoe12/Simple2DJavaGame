package com.mygdx.game.utilities;

import com.badlogic.gdx.InputProcessor;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Jacob on 4/7/2017.
 */
public class ButtonListener implements InputProcessor {

  ArrayList<Integer> keysPressed = new ArrayList<Integer>();

  @Override
  public boolean keyDown(int keycode) {
    keysPressed.add(keycode);
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    keysPressed.remove(new Integer(keycode));
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override
  public boolean scrolled(int amount) {
    return false;
  }

  public ArrayList<Integer> getKeysPressed(){
    Collections.sort(keysPressed);
    return keysPressed;
  }
}
