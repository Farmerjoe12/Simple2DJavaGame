package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

/**
 * Camera is a meaty class, containing both the ortho cam and the world map
 * @author adamfarmelo
 *
 */
public class Camera {

	private OrthographicCamera cam;
	private float w, h;
	private static SpriteBatch batch;
	private static Sprite mapSprite;
	private float translateSpeed;
	private static final int WORLD_WIDTH = 100, WORLD_HEIGHT = 100;
	private float effectiveViewportWidth;
	private float effectiveViewportHeight;
	private float boundaryClampValue = 32f;
    
	public Camera() {
		// Map creation
		batch = new SpriteBatch();
		mapSprite = new Sprite(new Texture(Gdx.files.internal("CastleExample.png")));
		mapSprite.setPosition(0,0);
		mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
		
		// translateSpeed here is used to control the speed that the camera moves 
		// when the player presses the arrow keys i.e. "moving" the sprite 
		translateSpeed = 0.08f;
		
		// Camera creation and functionality
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		
		// the values passed into the camera constructor represent how much of 
		// the world is visible in the viewport, basically the "zoom" value
		cam = new OrthographicCamera(27,27 * (h/w));
		
		cam.position.set((cam.viewportWidth / 2f) + 21, 
				(cam.viewportHeight / 2f) + 4, 0);
		
		// not sure if this call to update is really neccessary
		cam.update();
	}
	
	public void render() {
		handleInput();
		cam.update();
		batch.begin();
		batch.setProjectionMatrix(cam.combined);
		mapSprite.draw(batch);
		batch.end();
	}

    private void handleInput() {
    	int input = InputHandler.getInput();
    	
        if (input ==  InputHandler.LEFT) {
            cam.translate(-translateSpeed, 0, 0);
        }
        if (input == InputHandler.RIGHT) {
            cam.translate(translateSpeed, 0, 0);
        }
        if (input == InputHandler.DOWN) {
            cam.translate(0, -translateSpeed, 0);
        }
        if (input == InputHandler.UP) {
            cam.translate(0, translateSpeed, 0);
        }

    	effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        effectiveViewportHeight = cam.viewportHeight * cam.zoom;
        
        // boundaryClampValue represents the clamping of the camera to a certain 
        // value where the camera will move in regards to the edge of the map
        cam.position.x = MathUtils.clamp(cam.position.x, 
        		effectiveViewportWidth / boundaryClampValue,
        		99 - effectiveViewportWidth / boundaryClampValue);
        
        cam.position.y = MathUtils.clamp(cam.position.y, 
        		effectiveViewportHeight / boundaryClampValue,
        		99 - effectiveViewportHeight / boundaryClampValue);        	
    }
    
    public static void dispose() {
    	mapSprite.getTexture().dispose();
    	batch.dispose();
    }
}
