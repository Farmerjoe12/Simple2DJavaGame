package com.mygdx.game.components.inputComponent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.graphicsComponent.PlayerGraphics;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.entities.characters.Player;

/**
 * Created by Jacob on 3/29/2017.
 */
public class PlayerInput extends Component implements InputComponent {

    @Override
    public void handleInput(Player p) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            getParent().getComponent(Transform.class).deltaY(1f);
            getParent().getComponent(PlayerGraphics.class).setCurrentAnimation(0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            getParent().getComponent(Transform.class).deltaY(-1f);
            getParent().getComponent(PlayerGraphics.class).setCurrentAnimation(1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            getParent().getComponent(Transform.class).deltaX(-1f);
            getParent().getComponent(PlayerGraphics.class).setCurrentAnimation(2);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            getParent().getComponent(Transform.class).deltaX(1f);
            getParent().getComponent(PlayerGraphics.class).setCurrentAnimation(3);
        }
    }

    public boolean nothingPressed()
    {
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            return false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            return false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            return false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            return false;
        }
        return true;
    }
}
