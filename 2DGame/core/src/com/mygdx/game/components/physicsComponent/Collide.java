package com.mygdx.game.components.physicsComponent;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.Component;
import com.mygdx.game.entities.characters.Player;
import com.mygdx.game.entities.worlds.TiledWorld;

public class Collide extends Component{
	public Box2D boundBox;
	Vector2 position = new Vector2(0, 0);
	MapProperties prop = new MapProperties();
	
	public float checkMapBounds()
	{
		position = getParent().getComponent(Transform.class).position;
		prop = MyGdxGame.simpleGame.getChild(TiledWorld.class).prop;

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
	    
	    if (position.y < 0 || position.y + 32f > mapPixelHeight)
	    {
	    	position.y = 0f;
	    	return 0f;
	    } else if (position.y + 32 > mapPixelHeight) {
	    	position.x = mapPixelHeight - 32;
	    	return 0f;
	    }
		
		
		return 1f;
	}
}
