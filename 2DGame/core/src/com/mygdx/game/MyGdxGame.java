package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.characters.Player;
import com.mygdx.game.entities.games.Game;
import com.mygdx.game.entities.games.Simple2DJavaGame;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.entities.worlds.TiledWorld;

/**
 * This is the main class that runs the program, all rendering is currently
 * handled through the render method with a static call to the renderer class
 * @author adamfarmelo
 *
 */
public class MyGdxGame extends ApplicationAdapter {

	public static Game simpleGame;
	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		simpleGame = new Simple2DJavaGame();

		Player player = new Player();
		player.getComponent(Transform.class).setPosition(new Vector2(100f,100f));

		simpleGame.addChild(player);
		simpleGame.addChild(new TiledWorld());

		OrthographicCamera cam = new OrthographicCamera(30,30*(h/w));
		cam.setToOrtho(false, 960, 640);
		cam.position.set(0,0,0);
		simpleGame.setCamera(cam);

		cam.update();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		simpleGame.tick();
		simpleGame.draw(batch);
	}
	
	@Override
	public void dispose () {

	}
}
