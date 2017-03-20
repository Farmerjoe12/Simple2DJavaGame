package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * Renderer is basically a static class used to house all of the rendering 
 * functionality from the other classes
 * @author adamfarmelo
 *
 */
public class Renderer {

	private static Player player = new Player();
	private static Camera cam = new Camera();
	
	public static void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.render();
		player.render();
	}
}
