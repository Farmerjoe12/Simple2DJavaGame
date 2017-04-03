package com.mygdx.game.entities.worlds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Jacob on 3/29/2017.
 */
public class TiledWorld extends World {
  TiledMap tiledMap;
  TiledMapRenderer tiledMapRenderer;
  public MapProperties prop;

  public TiledWorld() {
    // loading in Tiled map, initializing the renderer
    tiledMap = new TmxMapLoader().load("2.tmx");
    tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    prop = tiledMap.getProperties();
  }

  public void draw(SpriteBatch batch) {
    tiledMapRenderer.setView(MyGdxGame.simpleGame.getCamera());
    tiledMapRenderer.render();
  }

  public MapProperties getProp() {
    return prop;
  }
}
