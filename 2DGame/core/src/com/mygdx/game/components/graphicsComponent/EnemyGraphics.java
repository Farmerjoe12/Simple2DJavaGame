package com.mygdx.game.components.graphicsComponent;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.inputComponent.AI;
import com.mygdx.game.components.physicsComponent.Collide;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.utilities.Animator;


public class EnemyGraphics extends Component implements GraphicsComponent {
  private Animator[] animSet;
  private Animator currAnimation = null;
  private int input;
  private int lastDir = 1;
  float delta = 1f;
  boolean moveFlag = false;
  long lastTime;

  public EnemyGraphics() {
    // createAnimSet takes parameters for row on the spritesheet and set
    // of sprites, the top leftmost set is row 0, set 0 increasing
    // down and to the right
    createAnimSet(12, 0);
    //currAnimation = animSet[1];
    lastTime = TimeUtils.millis();
  }

  @Override
  public void draw(SpriteBatch batch) {
    float x = getParent().getComponent(Transform.class).getPosition().x;
    float y = getParent().getComponent(Transform.class).getPosition().y; 
    
    if (TimeUtils.timeSinceMillis(lastTime) > 2000){
		moveFlag = !moveFlag;
		lastTime = TimeUtils.millis();
		input = getParent().getComponent(AI.class).handleInput();
	}
    
	if (moveFlag && 
			!getParent().getComponent(Collide.class).isBlocked(input) &&
			currAnimation != null) {
    	move(input);
        currAnimation = getCurrentAnimation(input);
        batch.draw(currAnimation.getCurrentTextureRegion(), x, y, 32, 32);
	} else {
	     batch.draw(animSet[lastDir].getLastFrame(), x, y, 32, 32);		
	}
	
  }
  
  public void move(int input)
  {
	  
	  switch (input) {
	  case -1: 
		break;
	  case 0: getParent().getComponent(Transform.class).deltaY(delta);
	  	break;
	  case 1: getParent().getComponent(Transform.class).deltaY(-delta);
	  	break;
	  case 2: getParent().getComponent(Transform.class).deltaX(-delta);
	  	break;
	  case 3: getParent().getComponent(Transform.class).deltaX(delta);
	  	break;
	  default: 
		break;
	  }
  }

  public Animator getCurrentAnimation(int input) {
    if (input >= 0) {
      lastDir = input;
      return animSet[input];
    } else
      return null;
  }
  public void createAnimSet(int spriteRow, int spriteSet) {
    int startSprite = spriteSet * 8;
    int endSprite = startSprite + 1;

    animSet = new Animator[4];
    animSet[0] = new Animator(spriteRow, startSprite, endSprite); // UP
    animSet[1] = new Animator(spriteRow, startSprite + 2, endSprite + 2); // DOWN
    animSet[2] = new Animator(spriteRow, startSprite + 4, endSprite + 4); // LEFT
    animSet[3] = new Animator(spriteRow, startSprite + 6, endSprite + 6); // RIGHT
  }
}
