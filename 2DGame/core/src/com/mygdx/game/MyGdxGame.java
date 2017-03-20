package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

/**
 * This is the main class that runs the program, all rendering is currently
 * handled through the render method with a static call to the renderer class
 * @author adamfarmelo
 *
 */
public class MyGdxGame extends ApplicationAdapter {
	
	@Override
	public void create () {
		
	}

	@Override
	public void render () {
		Renderer.render();
	}
	
	@Override
	public void dispose () {
		Camera.dispose();
		Player.dispose();
	}
}
