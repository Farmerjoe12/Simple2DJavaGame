package com.mygdx.game.components.physicsComponent;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.Component;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.characters.Player;
import com.mygdx.game.entities.worlds.TiledWorld;

public class Collide extends Component{
	Rectangle rect, collidableRect;
	Vector2 position, collidablePosition;
	TiledMap tiledMap;
	MapProperties prop = new MapProperties();
	
	public float checkCollisions()
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
	    
	    if (position.x < 0)
	    {
	    	position.x = 0f;
	    	return 0f;
	    } else if (position.x + 32 > mapPixelWidth) {
	    	position.x = mapPixelWidth - 32;
	    	return 0f;
	    }
	    
	    if (position.y < 0)
	    {
	    	position.y = 0;
	    	return 0f;
	    } else if (position.y + 32 > mapPixelHeight) {
	    	position.x = mapPixelHeight - 32;
	    	return 0f;
	    }
	    
	    if (isCollision())
	    	return 0f;
		
		return 1f;
	}
	
	public boolean isCollision()
	{
		position = getParent().getComponent(Transform.class).position;
		TiledMapTileLayer collidableLayer = (TiledMapTileLayer)tiledMap.getLayers().get("collidable");
		
		if (collidableLayer.getCell((int)position.x, (int)position.y) != null)
			return true;
		if (collidableLayer.getCell((int)position.x + 32, (int)position.y) != null)
			return true;
		if (collidableLayer.getCell((int)position.x, (int)position.y + 32) != null)
			return true;
		if (collidableLayer.getCell((int)position.x + 32, (int)position.y + 32) != null)
			return true;
		
		return false;
		
	}
}
