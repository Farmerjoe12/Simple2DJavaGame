package com.mygdx.game.entities.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.graphicsComponent.PlayerGraphics;
import com.mygdx.game.components.inputComponent.PlayerInput;
import com.mygdx.game.components.physicsComponent.Collide;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.components.statComponent.PlayerStatComponent;

/**
 * Created by Jacob on 3/29/2017.
 */
public class Player extends Character {

  public Player() {
    Transform transform = new Transform(900, 900);
    addComponent(transform);
    addComponent(new PlayerStatComponent());
    addComponent(new PlayerGraphics());
    addComponent(new PlayerInput());
    addComponent(new Collide());
  }

  @Override
  public void draw(SpriteBatch b) {
    b.setProjectionMatrix(MyGdxGame.currentGame.getCamera().combined);
    getComponent(PlayerGraphics.class).draw(b);
  }
}
