package com.mygdx.game.components.physicsComponent;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.graphicsComponent.PlayerGraphics;
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
	Rectangle rect, collidableRect;
	Vector2 position, collidablePosition;
	TiledMap tiledMap;
	MapProperties prop = new MapProperties();
	int facing = 0;
	
	/** isBlocked() is based on the individual properties of the tiles within the cells of 
	 * the tiledMap 
	 * 
	 */
	public boolean isBlocked(int facing)
	{
		
		position = getParent().getComponent(Transform.class).position;
		tiledMap = MyGdxGame.currentGame.getChild(TiledWorld.class).tiledMap;
		prop = tiledMap.getProperties();
		
	    int mapWidth = prop.get("width", Integer.class);
	    int mapHeight = prop.get("height", Integer.class);
	    int tilePixelWidth = prop.get("tilewidth", Integer.class);
	    int tilePixelHeight = prop.get("tileheight", Integer.class);

	    // 'width' and 'height' supply the amount of tiles in each row/column,
	    // so you must multiply it by the tile width in order to get the map dimensions
	    int mapPixelWidth = mapWidth * tilePixelWidth;
	    int mapPixelHeight = mapHeight * tilePixelHeight;
	    
		/** if the player is attempting to move into a blocked cell, the player
		 * should not be able to move
		 */
		boolean blocked = false;
		
		// TMTL is storing the collidable layer from 2.tmx
		TiledMapTileLayer collidableLayer = (TiledMapTileLayer)tiledMap.getLayers().get("collidable");
		
		// Checks the cell that each corner pixel of the player is in
		boolean bottomLeft = collidableLayer.getCell((int)((position.x - 1)/32), (int)((position.y - 1)/32)) != null,
				bottomRight = collidableLayer.getCell((int)((position.x + 33)/32), (int)((position.y - 1)/32)) != null,
				topLeft = collidableLayer.getCell((int)((position.x - 1)/32), (int)((position.y + 33)/32)) != null,
				topRight = collidableLayer.getCell((int)((position.x + 33)/32), (int)((position.y + 33)/32)) != null;
		
		switch (facing) {
		
		// If facing up, checks top left and right pixels for collision and also top map bounds
		case 0: return (topLeft || topRight) || position.y + 32 > mapPixelHeight;
		
		// If facing down, checks bottom left and right pixels for collision and also bottom map bounds
		case 1: return (bottomLeft || bottomRight) || position.y < 0;
		
		// If facing left, checks bottom and top left pixels for collision and also left map bounds
		case 2: return (bottomLeft || topLeft) || position.x < 0;
		
		// If facing right, checks bottom and top right pixels for collision and also right map bounds
		case 3: return (bottomRight || topRight) || position.x + 32 > mapPixelWidth;
		
		default: break;
		}
		
		return blocked;
	}
}
