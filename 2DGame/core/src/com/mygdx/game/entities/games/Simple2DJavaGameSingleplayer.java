package com.mygdx.game.entities.games;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.entities.characters.Enemy;
import com.mygdx.game.entities.characters.Player;
import com.mygdx.game.entities.worlds.TiledWorld;

/**
 * Created by Jacob on 3/29/2017.
 */
public class Simple2DJavaGameSingleplayer extends Game {
	
	/**
	 * S2DJGSP when instantiated first calls super() which 
	 * sets this game to the current game and sets up the 
	 * camera and input to track within this game
	 */
  public Simple2DJavaGameSingleplayer() {
	super();

    Player player = new Player();
    player.getComponent(Transform.class).setPosition(new Vector2(450f, 900f));
    addChild(player);
    Enemy enemy = new Enemy();    
    TiledWorld tiledWorld = new TiledWorld();
    addChild(tiledWorld);
    addChild(enemy);

  }
  
  public void tick() {
	  updateCamera();
  }
  
  public void draw(SpriteBatch batch) {
    batch.setProjectionMatrix(getCamera().combined);
    getChild(TiledWorld.class).drawBackground(batch);
    batch.begin();
    getChild(Player.class).draw(batch);
    getChild(Enemy.class).draw(batch);
    batch.end();
    getChild(TiledWorld.class).drawForeground(batch);
  }

}
