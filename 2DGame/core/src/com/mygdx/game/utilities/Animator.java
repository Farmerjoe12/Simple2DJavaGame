package com.mygdx.game.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Animator is used to provide the animations for the sprites
 * 
 * @author adamfarmelo
 *
 */
public class Animator {

  // Cols and rows come from the lastguardian_all.png
  // they represent the number of sprites on the sheet
  private static final int FRAME_COLS = 32, FRAME_ROWS = 23;

  private Animation<TextureRegion> walkAnimation;
  private TextureRegion[] walkFrames;
  private static Texture walkSheet;
  private static SpriteBatch spriteBatch;
  private float stateTime;
  private float frameTime;


  public Animator(int row, int startSprite, int endSprite) {

    // frameTime controls the speed of the animation, lower is faster
    frameTime = 0.2f;

    walkSheet = new Texture(Gdx.files.internal("lastguardian_all.png"));

    // this array is populated with individual indexes of sprites from the
    // sprite sheet to make them easily accessible
    TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS,
        walkSheet.getHeight() / FRAME_ROWS);

    // walkFrames is used to hold the frames or sprites that will be cycled
    // by an animation
    walkFrames = new TextureRegion[2];

    walkFrames[0] = tmp[row][startSprite];
    walkFrames[1] = tmp[row][endSprite];

    walkAnimation = new Animation<TextureRegion>(frameTime, walkFrames);

    spriteBatch = new SpriteBatch();
    stateTime = 0f;
  }

  public TextureRegion getCurrentTextureRegion() {
    stateTime += Gdx.graphics.getDeltaTime();
    
    return walkAnimation.getKeyFrame(stateTime, true);
  }

  public static void dispose() {
    spriteBatch.dispose();
    walkSheet.dispose();
  }

  public TextureRegion getLastFrame() {
    return walkFrames[1];
  }
}
