package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.entities.characters.Player;
import com.mygdx.game.entities.games.Game;
import com.mygdx.game.entities.games.Simple2DJavaGameMultiplayer;
import com.mygdx.game.entities.games.Simple2DJavaGameSingleplayer;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.entities.worlds.TiledWorld;
import com.mygdx.game.utilities.ButtonListener;

/**
 * This is the main class that runs the program, all rendering is currently handled through the
 * render method with a static call to the renderer class
 * 
 * @author adamfarmelo
 *
 */
public class MyGdxGame extends ApplicationAdapter {

  public static ButtonListener listener = new ButtonListener();
  static AssetManager assetManager = new AssetManager();
  public static Simple2DJavaGameSingleplayer simple2DJavaGameSingleplayer;
  public static Simple2DJavaGameMultiplayer simple2DJavaGameMultiplayer;
  public static Game currentGame;
  SpriteBatch batch;
  OrthographicCamera cam;

  @Override
  public void create() {
    loadAssets();

    batch = new SpriteBatch();

    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();

    simple2DJavaGameSingleplayer = new Simple2DJavaGameSingleplayer();
    //simple2DJavaGameMultiplayer = new Simple2DJavaGameMultiplayer();



    cam = new OrthographicCamera(30, 30 * (h / w));
    cam.setToOrtho(false, 960, 640);
    cam.position.set(0, 0, 0);
    currentGame.setCamera(cam);
    cam.update();

    Gdx.input.setInputProcessor(listener);

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

    Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    currentGame.tick();
    currentGame.draw(batch);

    cam.update();
  }

  @Override
  public void dispose() {
    assetManager.dispose();
    //simple2DJavaGameMultiplayer.dispose();
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

  private void checkMapBorders() {

    Player player = MyGdxGame.currentGame.getChild(Player.class);
    MapProperties prop = currentGame.getChild(TiledWorld.class).getProp();
    // get Tiled map properties
    int mapWidth = prop.get("width", Integer.class);
    int mapHeight = prop.get("height", Integer.class);
    int tilePixelWidth = prop.get("tilewidth", Integer.class);
    int tilePixelHeight = prop.get("tileheight", Integer.class);

    // 'width' and 'height' supply the amount of tiles in each row/column,
    // so you must multiply it by the tile width in order to get the map dimensions
    int mapPixelWidth = mapWidth * tilePixelWidth;
    int mapPixelHeight = mapHeight * tilePixelHeight;

    // determine when the camera will reach the ends of the map
    float viewportMinX = cam.viewportWidth / 2;
    float viewportMinY = cam.viewportHeight / 2;
    float viewportMaxX = mapPixelWidth - viewportMinX;
    float viewportMaxY = mapPixelHeight - viewportMinY;

    float x = player.getComponent(Transform.class).getPosition().x;
    float y = player.getComponent(Transform.class).getPosition().y;

    // preventing the camera from scrolling past the edge of the map
    if (x < viewportMinX) {
      cam.position.x = viewportMinX;
    } else if (x > viewportMaxX) {
      cam.position.x = viewportMaxX;
    } else {
      cam.position.x = x;
    }

    if (y < viewportMinY) {
      cam.position.y = viewportMinY;
    } else if (y > viewportMaxY) {
      cam.position.y = viewportMaxY;
    } else {
      cam.position.y = y;
    }
  }
}
