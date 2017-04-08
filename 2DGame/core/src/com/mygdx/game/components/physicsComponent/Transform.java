package com.mygdx.game.components.physicsComponent;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.Component;

/**
 * Created by Jacob on 3/23/2017.
 */
public class Transform extends Component {
  public float rotation = 0f;
  public float scale = 1f;
  Vector2 position = new Vector2();
  Vector2 velocity = new Vector2(0, 0);
  Vector2 acceleration = new Vector2(0, 0);

  public Transform(Vector2 position) {
    this.position = position;
  }

  public Transform(float x, float y) {
    position = new Vector2(x, y);
  }

  // deltaX and deltaY are the actual movement methods
  public void deltaX(float dx) {
    position.x += dx;
  }

  public void deltaY(float dy) {
    position.y += dy;
  }

  // Getters and setters
  public float getRotation() {
    return rotation;
  }

  public void setRotation(float rotation) {
    this.rotation = rotation;
  }

  public float getScale() {
    return scale;
  }

  public void setScale(float scale) {
    this.scale = scale;
  }

  public Vector2 getPosition() {
    return position;
  }

  public void setPosition(Vector2 position) {
    this.position = position;
  }
  
  public void setX(float x) {
	  this.position.x = x;
  }
  
  public void setY(float y) {
	  this.position.y = y;
  }

  public Vector2 getVelocity() {
    return velocity;
  }

  public void setVelocity(Vector2 velocity) {
    this.velocity = velocity;
  }

  public Vector2 getAcceleration() {
    return acceleration;
  }

  public void setAcceleration(Vector2 acceleration) {
    this.acceleration = acceleration;
  }
}
