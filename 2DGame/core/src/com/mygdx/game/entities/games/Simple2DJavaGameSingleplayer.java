package com.mygdx.game.entities.games;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.entities.characters.Enemy;
import com.mygdx.game.entities.characters.Player;
import com.mygdx.game.entities.worlds.TiledWorld;

/**
 * Created by Jacob on 3/29/2017.
 */
public class Simple2DJavaGameSingleplayer extends Game {

  public Simple2DJavaGameSingleplayer() {
    MyGdxGame.currentGame = this;
    TiledWorld tiledWorld = new TiledWorld();
    addChild(tiledWorld);
    Player player = new Player();
    Enemy enemy = new Enemy();
    addChild(enemy);
    player.getComponent(Transform.class).setPosition(new Vector2(450f, 900f));

    addChild(player);

  }

  public void tick() {
    getChild(TiledWorld.class).tick();
    getChild(Player.class).tick();
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
