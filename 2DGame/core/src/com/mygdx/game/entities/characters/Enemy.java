package com.mygdx.game.entities.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.graphicsComponent.EnemyGraphics;
import com.mygdx.game.components.inputComponent.AI;
import com.mygdx.game.components.physicsComponent.Collide;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.components.statComponent.EnemyStatComponent;

public class Enemy extends Character {

	// location of char is passed at creation of transform
	public Enemy()  /** Number 1 **/ {
		addComponent(new Transform(900, 900));
		addComponent(new EnemyStatComponent());
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
