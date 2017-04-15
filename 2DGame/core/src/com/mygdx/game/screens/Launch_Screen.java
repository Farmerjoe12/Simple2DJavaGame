package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Launch screen has become a valid Game screen to be drawn from MyGdxGame
 * The buttons on the screen will switch between Single and Multiplayer
 * 
 * Scene2D uses stages as the "canvas" to draw components, known as
 * "actors" like buttons and checkboxes, etc,.
 * @author adamfarmelo
 *
 */
public class Launch_Screen extends Screen{

    private Stage stage;
    private Skin skin;
    private Button singlePlay, multiPlay;
    BitmapFont font;
    SpriteBatch batch;
    Texture background;
    Sprite bgSprite;

    @Override
    public void create() {
	stage = new Stage(new ScreenViewport()); 
	TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/Scene/medieval.atlas"));
	Skin skin = new  Skin(Gdx.files.internal("data/Scene/medieval.json"));
	
	Table table = new Table();
	table.setFillParent(true);
	
	batch = new SpriteBatch();
	background = new Texture(Gdx.files.internal("data/Stills/background.png"));
	bgSprite = new Sprite(background);
	
	
	stage.addActor(table);
	
	ButtonStyle beigeStyle = new ButtonStyle();
	beigeStyle.up = skin.getDrawable("buttonlong_beige");
	beigeStyle.down = skin.getDrawable("buttonlong_beige_pressed");
	
	singlePlay = new Button(beigeStyle);	
	multiPlay = new Button(beigeStyle);
	Button optionButton = new Button(beigeStyle);
	
	BitmapFont font = new BitmapFont(Gdx.files.internal("data/Scene/prince.fnt"), Gdx.files.internal("data/Scene/princeFont.png"), false);
	LabelStyle labelStyle = new LabelStyle(font, new Color(255f,255f,255f, 1f));

	Label single = new Label("Single-Player: ", skin);
	single.setStyle(labelStyle);
	table.add(single).spaceBottom(20);	
	table.add(singlePlay).spaceBottom(20);
	table.row();
	
	Label multi = new Label("Multi-Player: ", skin);
	multi.setStyle(labelStyle);
	table.add(multi).spaceBottom(20);
	table.add(multiPlay).spaceBottom(20);
	table.row();
	
	Label options = new Label("Options: ", skin);
	options.setStyle(labelStyle);
	table.add(options);
	table.add(optionButton);
	
	Gdx.input.setInputProcessor(stage);		
    }
    
    @Override
    public void render() {
	batch.begin();
	bgSprite.draw(batch);
	batch.end();
	stage.draw();
    }
    
    public void resize (int width, int height) {
	stage.getViewport().update(width, height, true);
    }
}
