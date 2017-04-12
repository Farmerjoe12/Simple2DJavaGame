package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.UI_Components.Launch_Screen;
import com.mygdx.game.entities.games.Game;

/**
 * This is the main class that runs the program, all game logic and rendering 
 * is controlled by the render() method
 * 
 * @author adamfarmelo
 *
 */
public class MyGdxGame extends ApplicationAdapter {

  static AssetManager assetManager = new AssetManager();
  public static Game currentGame;
  SpriteBatch batch;
  public static OrthographicCamera cam;

  @Override
  public void create() {
    loadAssets();

    batch = new SpriteBatch();

    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    
    cam = new OrthographicCamera(30, 30 * (h / w));
    cam.setToOrtho(false, 960, 640);
    cam.position.set(0, 0, 0);
    
    new Launch_Screen();
    currentGame.setCamera(cam);
    cam.update();
  }

  @Override
  public void render() {
    if(assetManager.update()) {
      //asset manager is done loading
    }
    else {
      //to-do: put a loading bar here once we have enough assets that load time is non-negligible
      //asset manager is loading
    }
    
    currentGame.tick();
    currentGame.draw(batch);
  }

  @Override
  public void dispose() {
    assetManager.dispose();
  }

  public void loadAssets() {
    assetManager.load("lastguardian_all.png", Texture.class);
    assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
    assetManager.load("1.tmx", TiledMap.class);
    assetManager.load("2.tmx", TiledMap.class);
    assetManager.load("21.tmx", TiledMap.class);
    assetManager.finishLoading();
  }

  public static AssetManager getAssetManager(){
    return assetManager;
  }
}
