package com.mygdx.game.entities.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.characters.Player;
import com.mygdx.game.entities.worlds.TiledWorld;
import com.mygdx.game.utilities.ButtonListener;


/**
 * Created by Jacob on 3/29/2017.
 */
public abstract class Game extends Entity {

	private OrthographicCamera camera;
	public static ButtonListener listener = new ButtonListener();
	
	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public abstract void draw(SpriteBatch batch);

	
}
