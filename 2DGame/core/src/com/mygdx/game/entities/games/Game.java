package com.mygdx.game.entities.games;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.characters.Player;
import com.mygdx.game.entities.worlds.TiledWorld;


/**
 * Created by Jacob on 3/29/2017.
 */
public abstract class Game extends Entity {
  private OrthographicCamera camera;

  public void setCamera(OrthographicCamera camera) {
    this.camera = camera;
  }

  public OrthographicCamera getCamera() {
    return camera;
  }

  public abstract void draw(SpriteBatch batch);

  public void updateCamera() {

    float x = getChild(Player.class).getComponent(Transform.class).getPosition().x + 16;
    float y = getChild(Player.class).getComponent(Transform.class).getPosition().y + 16;
    camera.position.set(x, y, 0);
    checkMapBorders();
    camera.update();

  }

  private void checkMapBorders() {
    Player player = MyGdxGame.simpleGame.getChild(Player.class);
    MapProperties prop = getChild(TiledWorld.class).getProp();
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
    float viewportMinX = camera.viewportWidth / 2;
    float viewportMinY = camera.viewportHeight / 2;
    float viewportMaxX = mapPixelWidth - viewportMinX;
    float viewportMaxY = mapPixelHeight - viewportMinY;

    float x = player.getComponent(Transform.class).getPosition().x;
    float y = player.getComponent(Transform.class).getPosition().y;

    // preventing the camera from scrolling past the edge of the map
    if (x < viewportMinX) {
      camera.position.x = viewportMinX;
    } else if (x > viewportMaxX) {
      camera.position.x = viewportMaxX;
    } else {
      camera.position.x = x;
    }

    if (y < viewportMinY) {
      camera.position.y = viewportMinY;
    } else if (y > viewportMaxY) {
      camera.position.y = viewportMaxY;
    } else {
      camera.position.y = y;
    }
  }
}
