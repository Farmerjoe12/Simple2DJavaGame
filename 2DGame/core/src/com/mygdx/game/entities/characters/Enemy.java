package com.mygdx.game.entities.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.graphicsComponent.EnemyGraphics;
import com.mygdx.game.components.inputComponent.AI;
import com.mygdx.game.components.physicsComponent.Collide;
import com.mygdx.game.components.physicsComponent.Transform;

public class Enemy extends Character {

	public Enemy()  /** Number 1 **/ {
		addComponent(new Transform(0, 0));
		addComponent(new EnemyGraphics());
		addComponent(new AI());
		addComponent(new Collide());
	}
	@Override
	public void draw(SpriteBatch b) {
		// TODO Auto-generated method stub
		getComponent(EnemyGraphics.class).draw(b);
	}

}
