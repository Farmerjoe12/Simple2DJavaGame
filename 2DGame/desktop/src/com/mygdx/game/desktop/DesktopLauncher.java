package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
  public static void main(String[] arg) {
	LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "2D Java Game";
    config.width = 1280;
    config.height = 720;
    config.resizable = false;
    new LwjglApplication(new MyGdxGame(), config); 
  }

}
