package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Camera is a meaty class, containing both the ortho cam and the world map
 * @author adamfarmelo
 *
 */
public class Camera {

	private OrthographicCamera cam;
	
	private Player player;
	
	TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	MapProperties prop;
    
	public Camera() {		
		// loading in Tiled map, initializing the renderer
		tiledMap = new TmxMapLoader().load("2.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		prop = tiledMap.getProperties();
		
		// initialize camera, set to view of 30x20 tiles at 32px per tile
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 960, 640);
		
		// update() is required after every change to the camera
		cam.update();
		
		player = new Player(cam);
		
		// setting player position, multiply by 32 in order to be on the position 15, 10 on the map
		player.position.set(15 * 32, 10 * 32);
		

	}
	
	public void render() {
		
		// set the player position to project according to the camera rather than the window
		player.getBatch().setProjectionMatrix(cam.combined);
		
		//check the scrolling
		checkMapBorders();
		
		//update camera from checkMapBorders and any other changes that may have happened
		cam.update();
		tiledMapRenderer.setView(cam);
		tiledMapRenderer.render();
		player.render();
	}

    private void checkMapBorders() {
    	
    	//get Tiled map properties
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
    	
    	// preventing the camera from scrolling past the edge of the map
    	if (player.position.x < viewportMinX && player.position.y < viewportMinY)
    	{
    		cam.position.x = viewportMinX;
    		cam.position.y = viewportMinY;
    	}else if(player.position.x > viewportMaxX && player.position.y > viewportMaxY)
    	{
    		cam.position.x = viewportMaxX;
    		cam.position.y = viewportMaxY;
    	}else if(player.position.x > viewportMaxX && player.position.y < viewportMinY)
    	{
    		cam.position.x = viewportMaxX;
    		cam.position.y = viewportMinY;
    	}else if(player.position.x < viewportMinX && player.position.y > viewportMaxY)
    	{
    		cam.position.x = viewportMinX;
    		cam.position.y = viewportMaxY;
    	}else if(player.position.x < viewportMinX)
    	{
    		cam.position.x = viewportMinX;
    		cam.position.y = player.position.y;
    	}else if(player.position.y < viewportMinY)
    	{
    		cam.position.y = viewportMinY;
    		cam.position.x = player.position.x;
    	}else if(player.position.x > viewportMaxX)
    	{
    		cam.position.x = viewportMaxX;
    		cam.position.y = player.position.y;
    	}else if(player.position.y > viewportMaxY)
    	{
    		cam.position.y = viewportMaxY;
    		cam.position.x = player.position.x;
    	}else{
    		cam.position.x = player.position.x;
    		cam.position.y = player.position.y;
    	}
    	
    }
    
    public static void dispose() {
    }
}
