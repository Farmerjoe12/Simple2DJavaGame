package com.mygdx.game.entities.worlds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.statComponent.PlayerStatComponent;
import com.mygdx.game.entities.characters.Player;

/**
 * Created by Jacob on 3/29/2017.
 */
public class TiledWorld extends World {
  public TiledMap tiledMap;
  TiledMapRenderer tiledMapRenderer;
  public MapProperties prop;
  
  Integer currentMap = new Integer(1);

  int[] backgroundLayers = {0, 1};
  int[] foregroundLayers = {2};

  public TiledWorld() {
	  
    // loading in Tiled map, initializing the renderer
    tiledMap = MyGdxGame.getAssetManager().get(Integer.toString(currentMap) + ".tmx");
    tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    prop = tiledMap.getProperties();
    
  }
  
  
  public int getMap()
  {
	  return currentMap;
  }
  
  public int setMap(int map)
  {
	  currentMap = map;
	  tiledMap = MyGdxGame.getAssetManager().get(Integer.toString(currentMap) + ".tmx");
	  tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
	  return currentMap;
  }
  
  public void draw(SpriteBatch batch) {
    tiledMapRenderer.setView(MyGdxGame.currentGame.getCamera());
    tiledMapRenderer.render();
  }

  public void drawBackground(SpriteBatch batch) {
    tiledMapRenderer.setView(MyGdxGame.currentGame.getCamera());
    tiledMapRenderer.render(backgroundLayers);

  }

  public void drawForeground(SpriteBatch batch) {
	tiledMapRenderer.setView(MyGdxGame.currentGame.getCamera());
	tiledMapRenderer.render(foregroundLayers);
  }
  public MapProperties getProp() {
    return prop;
  }
  
  public void dispose()
  {
	  this.dispose();
  }

}
