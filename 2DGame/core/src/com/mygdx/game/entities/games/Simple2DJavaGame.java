package com.mygdx.game.entities.games;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entities.characters.Enemy;
import com.mygdx.game.entities.characters.Player;
import com.mygdx.game.entities.worlds.TiledWorld;

/**
 * Created by Jacob on 3/29/2017.
 */
public class Simple2DJavaGame extends Game
{
    public void tick()
    {
        getChild(TiledWorld.class).tick();
        getChild(Player.class).tick();
        updateCamera();
    }

    public void draw(SpriteBatch batch)
    {
        batch.setProjectionMatrix(getCamera().combined);
        getChild(TiledWorld.class).drawBackground(batch);
        batch.begin();
        getChild(Player.class).draw(batch);
        getChild(Enemy.class).draw(batch);
        batch.end();
        getChild(TiledWorld.class).drawForeground(batch);
    }



}
