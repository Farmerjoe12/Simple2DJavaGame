package com.mygdx.game.components.graphicsComponent;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.inputComponent.AI;
import com.mygdx.game.components.physicsComponent.Collide;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.utilities.Animator;


public class EnemyGraphics extends Component implements GraphicsComponent {
  private Animator[] animSet;
  private Animator currAnimation;
  private int input, lastDir;

  /**
   * EnemyGraphics is solely responsible for drawing the animations and sprites
   * -- as it should be
   */
  public EnemyGraphics() {
    // createAnimSet takes parameters for row on the spritesheet and set
    // of sprites, the top leftmost set is row 0, set 0 increasing
    // down and to the right
    createAnimSet(12, 0);
    currAnimation = null;
    lastDir = 1;
  }

  // draw gets the input as well as current location
  // then draws the animation or last frame depending on if moving or not
  // which is determined by input
  @Override
  public void draw(SpriteBatch batch) {
    float x = getParent().getComponent(Transform.class).getPosition().x;
    float y = getParent().getComponent(Transform.class).getPosition().y; 
    
    input = getParent().getComponent(AI.class).handleInput();
    currAnimation = getCurrentAnimation(input);
    
	if (!getParent().getComponent(Collide.class).isBlocked(input)
			&& currAnimation != null) {
        batch.draw(currAnimation.getCurrentTextureRegion(), x, y, 32, 32);
	} else {
	     batch.draw(animSet[lastDir].getLastFrame(), x, y, 32, 32);		
	}
	
  }
  
  private Animator getCurrentAnimation(int input) {
    if (input >= 0) {
      lastDir = input;
      return animSet[input];
    } else
      return null;
  }
  
  private void createAnimSet(int spriteRow, int spriteSet) {
    int startSprite = spriteSet * 8;
    int endSprite = startSprite + 1;

    animSet = new Animator[4];
    animSet[0] = new Animator(spriteRow, startSprite, endSprite); // UP
    animSet[1] = new Animator(spriteRow, startSprite + 2, endSprite + 2); // DOWN
    animSet[2] = new Animator(spriteRow, startSprite + 4, endSprite + 4); // LEFT
    animSet[3] = new Animator(spriteRow, startSprite + 6, endSprite + 6); // RIGHT
  }
}
