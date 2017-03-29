package com.mygdx.game.entities.characters;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.graphicsComponent.PlayerGraphics;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.entities.Entity;

/**
 * Created by Jacob on 3/29/2017.
 */
public abstract class Character extends Entity {
    public void setPosition(Vector2 pos){
        getComponent(Transform.class).setPosition(pos);
        getComponent(PlayerGraphics.class).updateSpritePositions();
    }
}
