package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
	private float rotationSpeed;
	private static SpriteBatch batch;
	private static Sprite mapSprite;
	private float translateSpeed;
	private static final int WORLD_WIDTH = 100, WORLD_HEIGHT = 100;
	
	public Camera() {
		// Map creation
		batch = new SpriteBatch();
		mapSprite = new Sprite(new Texture(Gdx.files.internal("CastleExample.png")));
		mapSprite.setPosition(0,0);
		mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
		
		// Camera creation and functionality
		translateSpeed = 0.1f;
		rotationSpeed = 0.5f;
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(30,30 * (h/w));
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
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
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cam.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-translateSpeed, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(translateSpeed, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -translateSpeed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, translateSpeed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            cam.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            cam.rotate(rotationSpeed, 0, 0, 1);
        }

        cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 100/cam.viewportWidth);

        float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

        cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
    }
    
    public static void dispose() {
    	mapSprite.getTexture().dispose();
    	batch.dispose();
    }
}
