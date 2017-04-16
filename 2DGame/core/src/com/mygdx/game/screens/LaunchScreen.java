package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;

/**
 * Launch screen has become a valid Game screen to be drawn from MyGdxGame
 * The buttons on the screen will switch between Single and Multiplayer
 * 
 * Scene2D uses stages as the "canvas" to draw components, known as
 * "actors" like buttons and checkboxes, etc,.
 * @author adamfarmelo
 *
 */
public class LaunchScreen implements Screen{

    private MyGdxGame game;
    private Screen currScreen = this;
    private Stage stage;
    private Skin skin;
    private Button singlePlay, multiPlay;
    SpriteBatch batch;
    Texture background;
    Sprite bgSprite;

    public LaunchScreen(final MyGdxGame game) {
	this.game = game;

	float w = Gdx.graphics.getWidth();
	float h = Gdx.graphics.getHeight();
	
	// Stage, skin, and atlas
	stage = new Stage(new ScreenViewport()); 
	TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/Scene/medieval.atlas"));
	Skin skin = new  Skin(Gdx.files.internal("data/Scene/medieval.json"));

	// Background image
	batch = new SpriteBatch();
	background = new Texture(Gdx.files.internal("data/Stills/background.png"));
	bgSprite = new Sprite(background);
	bgSprite.setSize(w,h);
	
	// Font & labelStyle
	BitmapFont smallFont = new BitmapFont(Gdx.files.internal("data/Scene/prince.fnt"), Gdx.files.internal("data/Scene/princeFont.png"), false);
	LabelStyle smallStyle = new LabelStyle(smallFont, new Color(255f,255f,255f, 1f));
	BitmapFont largeFont = new BitmapFont(Gdx.files.internal("data/Scene/roman_big.fnt"), Gdx.files.internal("data/Scene/romanBigFont.png"), false);
	LabelStyle largeStyle = new LabelStyle(largeFont, new Color(255f, 255f, 255f, 1f));
	
	// mainTable
	Table mainTable = new Table();
	mainTable.setFillParent(true);
	stage.addActor(mainTable);
	
	// Title
	Label title = new Label("Welcome to 2D Java Game", skin);
	title.setStyle(largeStyle);
	mainTable.add(title).expandY().top();
	mainTable.row();
	
	// Button style
	ButtonStyle beigeStyle = new ButtonStyle();
	beigeStyle.up = skin.getDrawable("buttonlong_beige");
	beigeStyle.down = skin.getDrawable("buttonlong_beige_pressed");
	
	// buttonTable
	Table buttonTable = new Table();
	buttonTable.setFillParent(false);
	mainTable.add(buttonTable);
	
	// SP button
	singlePlay = new Button(beigeStyle);
	singlePlay.addListener(new ChangeListener() {
	    @Override
	    public void changed(ChangeEvent event, Actor actor) {
		currScreen.hide();
		game.setScreen(new SPGameScreen(game));
	    }	    
	});
	Label single = new Label("Single-Player: ", skin);
	single.setStyle(smallStyle);
	buttonTable.add(single).spaceBottom(20);	
	buttonTable.add(singlePlay).left().spaceBottom(20);
	buttonTable.row();
	
	// MP button
	multiPlay = new Button(beigeStyle);
	Label multi = new Label("Multi-Player: ", skin);
	multi.setStyle(smallStyle);
	buttonTable.add(multi).spaceBottom(20);
	buttonTable.add(multiPlay).spaceBottom(20);
	buttonTable.row();
	
	// OPT button
	Button optionButton = new Button(beigeStyle);
	Label options = new Label("Options: ", skin);
	options.setStyle(smallStyle);
	buttonTable.add(options).spaceBottom(20);
	buttonTable.add(optionButton).spaceBottom(20);
	
	Gdx.input.setInputProcessor(stage);
	Gdx.graphics.setContinuousRendering(false);
    }
    
    @Override
    public void show() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void render(float delta) {
	batch.begin();
	bgSprite.draw(batch);
	batch.end();
	stage.draw();
	
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
	stage.dispose();
	background.dispose();
	batch.dispose();
    }
}
