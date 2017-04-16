package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.entities.games.Game;
import com.mygdx.game.screens.LaunchScreen;

/**
 * This is the main class that runs the program, all game logic and rendering 
 * is controlled by the render() method
 * 
 * @author adamfarmelo
 *
 */
public class MyGdxGame extends ApplicationAdapter {

    private SpriteBatch batch;
    public static AssetManager assetManager = new AssetManager();
    public static Screen currScreen;
    public static Game currentGame;

    @Override
    public void create() {
	batch = new SpriteBatch();
	setScreen(new LaunchScreen());
    }

    @Override
    public void render() {
	if (currScreen != null) {
	    currScreen.render(Gdx.graphics.getDeltaTime());
	} else currentGame.draw(batch);
	if(assetManager.update()) {
	    //asset manager is done loading
	}
	else {
	    //to-do: put a loading bar here once we have enough assets that 
	    // load time is non-negligible
	    //asset manager is loading
	}
    }

    @Override
    public void dispose() {
	assetManager.dispose();
    }

    public void loadAssets() {
	assetManager.load("lastguardian_all.png", Texture.class);
	assetManager.setLoader(TiledMap.class, 
		new TmxMapLoader(new InternalFileHandleResolver()));
	assetManager.load("1.tmx", TiledMap.class);
	assetManager.load("2.tmx", TiledMap.class);
	assetManager.load("21.tmx", TiledMap.class);
	assetManager.load("uiskin.atlas", Skin.class);
	assetManager.load("uiskin.json", Skin.class);
	assetManager.load("uiskin.png", Skin.class);
	assetManager.finishLoading();
    }

    public void setScreen(Screen newScreen) {
	currScreen = newScreen;
    }
    
    public static AssetManager getAssetManager(){
	return assetManager;
    }
}
