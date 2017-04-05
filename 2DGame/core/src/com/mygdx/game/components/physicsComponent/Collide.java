package com.mygdx.game.components.physicsComponent;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
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
	
	public boolean checkCollisions()
	{
		position = getParent().getComponent(Transform.class).position;
		tiledMap = MyGdxGame.simpleGame.getChild(TiledWorld.class).tiledMap;
		prop = tiledMap.getProperties();

	    int mapWidth = prop.get("width", Integer.class);
	    int mapHeight = prop.get("height", Integer.class);
	    int tilePixelWidth = prop.get("tilewidth", Integer.class);
	    int tilePixelHeight = prop.get("tileheight", Integer.class);

	    // 'width' and 'height' supply the amount of tiles in each row/column,
	    // so you must multiply it by the tile width in order to get the map dimensions
	    int mapPixelWidth = mapWidth * tilePixelWidth;
	    int mapPixelHeight = mapHeight * tilePixelHeight;
	    
	    float delta = 1f;
	    float x = position.x;
	    float y = position.y;
	    
	    // Checking map x boundaries
	    if (x < 0)
	    {
	    	return true;
	    } else if (x + 32 > mapPixelWidth) {
	    	return true;
	    }
	    // Checking map y boundaries
	    if (y < 0)
	    {
	    	return true;
	    } else if (y + 32 > mapPixelHeight) {
	    	return true;
	    }
	    
	    facing = getParent().getComponent(PlayerGraphics.class).getInput();
	    //UP = 0
	    //DOWN = 1
	    //LEFT = 2
	    //RIGHT = 3
	     
	    // Collision detection currently is only checking the players current
	    // position. this should be called by the playerInput class to check if 
	    // the next cell is blocked or not
	    if (isBlocked(position.x + 32, position.y + 32))
	    {		    	
	    	return true;
	    }

		
		return false;
	}
	
	/** isBlocked() is based on the individual properties of the tiles within the cells of 
	 * the tiledMap 
	 * 
	 */
	public boolean isBlocked(float nextX, float nextY)
	{
		/** if the player is attempting to move into a blocked cell, the player
		 * should not be able to move
		 */
		Cell cell = null;
		boolean blocked = false;
		
		// TMTL is storing the collidable layer from 2.tmx
		TiledMapTileLayer collidableLayer = (TiledMapTileLayer)tiledMap.getLayers().get("collidable");
		
		/* 
		try 
		{
			// getCell is returning the cell specified by the param nextX and nextY
			// where each param is divided by 32 in order to get cell rows and cols
			cell = collidableLayer.getCell((int) (nextX/32), (int) (nextY/32));
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		// here if the cell returned by getCell and the corresponding tile aren't null,
		// then the tile properties from the cell are searched for the key "blocked"
		// if the key is found, the cell is not traversable
		/*if (cell!= null && cell.getTile() != null)
		{
			if (cell.getTile().getProperties().containsKey("blocked")) {
				blocked = true;
			}
		}*/
		
		// Checking corners of the sprite
		// Bottom left
		if (collidableLayer.getCell((int)(position.x/32), (int)(position.y/32)) != null)
 			return true;
		// Bottom right
 		if (collidableLayer.getCell((int)((position.x + 32)/32), (int)(position.y/32)) != null)
 			return true;
 		// Top left
 		if (collidableLayer.getCell((int)(position.x/32), (int)((position.y + 32)/32)) != null)
 			return true;
 		// Top right
 		if (collidableLayer.getCell((int)((position.x + 32)/32), (int)((position.y + 32)/32)) != null)
 			return true;
		
		return blocked;
	}
}
