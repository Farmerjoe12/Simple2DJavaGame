
package com.mygdx.game.components.physicsComponent;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.Component;
import com.mygdx.game.entities.worlds.TiledWorld;

/**
 * 
 * Ideally this component should be implemented by the worldMap entity. Then,
 * within handleInput() of PlayerInput before transform is called, the Collide
 * compponent should be called and utilized so that the player's position is not updated
 * until the tile in the desired direction of travel is verified to be clear 
 * @author adamfarmelo
 *
 */
public class Collide extends Component{
	private Vector2 position;
	private TiledMap tiledMap;
	private MapProperties prop;
	private int mapWidth;
	private int mapHeight;
  private int tilePixelWidth;
  private int tilePixelHeight;

  // m supply the amount of tiles in each row/column,
  // so you must multiply it by the tile width in order to get the map dimensions
  int mapPixelWidth;
  int mapPixelHeight;
    
	private boolean bottomLeft, bottomRight, topRight, topLeft;

  public Collide()
  {
    tiledMap = MyGdxGame.currentGame.getChild(TiledWorld.class).tiledMap;
    prop = tiledMap.getProperties();
    mapWidth = prop.get("width", Integer.class);
    mapHeight = prop.get("height", Integer.class);
    tilePixelWidth = prop.get("tilewidth", Integer.class);
    tilePixelHeight = prop.get("tileheight", Integer.class);
    mapPixelWidth = mapWidth * tilePixelWidth;
    mapPixelHeight = mapHeight * tilePixelHeight;
  }

	
	/** isBlocked() is based on the individual properties of the tiles within the cells of 
	 * the tiledMap 
	 * 
	 */
	public boolean isBlocked(int facing)
	{
		position = getParent().getComponent(Transform.class).position;
	    
    /**
     * baseX&Y and adjX&Y are used to get the corners of the sprite, since
     * the sprites position is based off of the bottom left corner, adjusted
     * values are needed for the other three corners
     */
    float baseX = position.x;
    float baseY = position.y;
    float adjX = position.x + 31;
    float adjY = position.y + 15;
	    
		// TMTL is storing the collidable layer from 2.tmx
		TiledMapTileLayer collidableLayer = (TiledMapTileLayer)tiledMap.getLayers().get("collidable");
		
    // Values tweaked to prevent texture overlap

    // UP
		// If facing up, checks top left and right pixels for collision and also top map bounds
    if (facing == 0) {
      adjY += 1;
      topLeft = collidableLayer.getCell((int)(baseX/32), (int)(adjY/32)) != null;
      topRight = collidableLayer.getCell((int)(adjX/32), (int)(adjY/32)) != null;
      return ((topLeft || topRight) ) || position.y + 32 > mapHeight*tilePixelHeight;
    }
    // DOWN
  // If facing down, checks bottom left and right pixels for collision and also bottom map bounds
    if (facing == 1) {
      baseY -= 1;
      bottomLeft = collidableLayer.getCell((int)(baseX/32), (int)(baseY/32)) != null;
      bottomRight = collidableLayer.getCell((int)(adjX/32), (int)(baseY/32)) != null;
      return ((bottomLeft) || (bottomRight)) || position.y < 0;
    }
    // LEFT
    // If facing left, checks bottom and top left pixels for collision and also left map bounds
    if (facing == 2) {
      baseX -= 1;
      bottomLeft = collidableLayer.getCell((int)(baseX/32), (int)(baseY/32)) != null;
      topLeft = collidableLayer.getCell((int)(baseX/32), (int)(adjY/32)) != null;
    return ((bottomLeft) || (topLeft)) || position.x < 0;
    }
    // RIGHT
    // If facing right, checks bottom and top right pixels for collision and also right map bounds
    if (facing == 3) {
      adjX += 1;
      bottomRight = collidableLayer.getCell((int)(adjX/32), (int)(baseY/32)) != null;
      topRight = collidableLayer.getCell((int)(adjX/32), (int)(adjY/32)) != null;
      return ((bottomRight) || (topRight)) || position.x + 32 > mapWidth*tilePixelWidth;
    }
    return false;
	}
}

