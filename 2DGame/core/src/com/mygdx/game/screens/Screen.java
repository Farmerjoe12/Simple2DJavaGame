package com.mygdx.game.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public abstract class Screen extends ApplicationAdapter{

    protected static AssetManager assetManager = new AssetManager();
    public Stage stage;
    protected Skin skin;
    protected TextureAtlas atlas;
    
    public void Screen() {
	stage = new Stage(new ScreenViewport());
	Gdx.input.setInputProcessor(stage);
    }
    
    
    public void draw() {
	stage.draw();
	Gdx.graphics.setContinuousRendering(false);
    }

    public void loadAssets() {
	assetManager.load("assets/lastguardian_all.png", Texture.class);
	//assetManager.load("Tileset.png", Texture.class);
	//assetManager.load("skin/medieval.atlas", TextureAtlas.class);
	//assetManager.load("skin.medieval.json", Skin.class);
	/**
	assetManager.setLoader(TiledMap.class, 
		new TmxMapLoader(new InternalFileHandleResolver()));
	assetManager.load("assets/1.tmx", TiledMap.class);
	assetManager.load("assets/2.tmx", TiledMap.class);
	assetManager.finishLoading();
	**/
    }

    public AssetManager getAssetManager(){
	return assetManager;
    }
}
