package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.entities.characters.Enemy;
import com.mygdx.game.entities.characters.Player;
import com.mygdx.game.entities.games.Game;
import com.mygdx.game.entities.worlds.TiledWorld;

/**
 * Created by Jacob on 3/29/2017.
 */
public class SPGameScreen extends Game implements Screen{

    /**
     * S2DJGSP when instantiated first calls super() which 
     * sets this game to the current game and sets up the 
     * camera and input to track within this game
     */
    MyGdxGame game;
    OrthographicCamera cam;
    SpriteBatch batch;
    public SPGameScreen(MyGdxGame game) {
	this.game = game;
	game.currentGame = this;
	this.show();
	TiledWorld tiledWorld = new TiledWorld();
	addChild(tiledWorld);
	Player player = new Player();
	Enemy enemy = new Enemy();
	addChild(enemy);
	player.getComponent(Transform.class).setPosition(new Vector2(450f, 900f));
	addChild(player);
	
	batch = new SpriteBatch();
	float w = Gdx.graphics.getWidth();
	float h = Gdx.graphics.getHeight();

	cam = new OrthographicCamera(30, 30 * (h / w));
	cam.setToOrtho(false, 960, 640);
	cam.position.set(0, 0, 0);
	cam.update();
	
	Gdx.graphics.setContinuousRendering(true);
    }

    public void tick() {
	updateCamera();
    }

    public void draw(SpriteBatch batch) {
    }

    public void updateCamera() {
	float x = getChild(Player.class).getComponent(Transform.class).getPosition().x + 16;
	float y = getChild(Player.class).getComponent(Transform.class).getPosition().y + 16;
	cam.position.set(x, y, 0);
	checkMapBorders();
	cam.update();
}

protected void checkMapBorders() {
	Player player = MyGdxGame.currentGame.getChild(Player.class);
	MapProperties prop = getChild(TiledWorld.class).getProp();
	// get Tiled map properties
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

	float x = player.getComponent(Transform.class).getPosition().x;
	float y = player.getComponent(Transform.class).getPosition().y;

	// preventing the camera from scrolling past the edge of the map
	if (x < viewportMinX) {
		cam.position.x = viewportMinX;
	} else if (x > viewportMaxX) {
		cam.position.x = viewportMaxX;
	} else {
		cam.position.x = x;
	}

	if (y < viewportMinY) {
		cam.position.y = viewportMinY;
	} else if (y > viewportMaxY) {
		cam.position.y = viewportMaxY;
	} else {
		cam.position.y = y;
	}
}

public OrthographicCamera getCamera() {
    return cam;
}
    @Override
    public void show() {
	// TODO Auto-generated method stub

    }

    @Override
    public void render(float delta) {
	// TODO Auto-generated method stub
	batch.setProjectionMatrix(cam.combined);
	getChild(TiledWorld.class).drawBackground(batch);
	batch.begin();
	getChild(Player.class).draw(batch);
	getChild(Enemy.class).draw(batch);
	batch.end();
	getChild(TiledWorld.class).drawForeground(batch);
    }

    @Override
    public void hide() {
	// TODO Auto-generated method stub

    }

    @Override
    public void resize(int width, int height) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void pause() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void resume() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void dispose() {
	// TODO Auto-generated method stub
	
    }

}
