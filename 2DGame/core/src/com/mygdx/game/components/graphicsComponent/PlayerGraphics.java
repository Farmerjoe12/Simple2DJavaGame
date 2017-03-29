package com.mygdx.game.components.graphicsComponent;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utilities.Animator;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.inputComponent.PlayerInput;
import com.mygdx.game.components.physicsComponent.Transform;

/**
 * Created by Jacob on 3/29/2017.
 */
public class PlayerGraphics extends Component implements GraphicsComponent
{
    private Animator walkUP, walkDOWN, walkLEFT, walkRIGHT;
    private Animator currAnimation = null;

    public PlayerGraphics()
    {

        walkUP = new Animator(0, 1);
        walkDOWN = new Animator(2,3);
        walkLEFT = new Animator(4,5);
        walkRIGHT = new Animator(6,7);
        currAnimation = walkDOWN;
    }

    @Override
    public void draw(SpriteBatch batch) {
        float x = getParent().getComponent(Transform.class).getPosition().x;
        float y = getParent().getComponent(Transform.class).getPosition().y;
        if(getParent().getComponent(PlayerInput.class).nothingPressed())
        {
            batch.draw(currAnimation.getLastFrame(),x, y, 32, 32);
        }
        else
        {
            batch.draw(currAnimation.getCurrentTextureRegion(),x, y, 32, 32);
        }

    }

    public void setCurrentAnimation(int x)
    {
        switch(x)
        {
            case 0:
                currAnimation = walkUP;
                break;
            case 1:
                currAnimation = walkDOWN;
                break;
            case 2:
                currAnimation = walkLEFT;
                break;
            case 3:
                currAnimation = walkRIGHT;
                break;
        }
    }

    public void updateSpritePositions()
    {

    }
}
