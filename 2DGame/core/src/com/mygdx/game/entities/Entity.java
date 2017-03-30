package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;

import java.util.ArrayList;

/**
 * Created by Jacob on 3/29/2017.
 */
public abstract class Entity {
  private final int MAX_COMPONENTS = 10;
  private ArrayList<Component> components = new ArrayList<Component>();
  private ArrayList<Entity> children = new ArrayList<Entity>();
  private Entity parent;

  public void tick() {

  }

  public abstract void draw(SpriteBatch b);

  public <T extends Component> T getComponent(Class<T> toGet) {
    for (Component c : components) {
      if (toGet.isInstance(c)) {
        return toGet.cast(c);
      }
    }

    return (T) new Component() {};
  }

  public <T extends Entity> T getChild(Class<T> toGet) {
    for (Entity e : children) {
      if (toGet.isInstance(e)) {
        return toGet.cast(e);
      }
    }

    return (T) new Entity() {
      @Override
      public void draw(SpriteBatch b) {

      }
    };
  }

  public void addComponent(Component c) {
    if (components.size() < MAX_COMPONENTS) {
      components.add(c);
      c.setParent(this);
    }
  }

  public void removeComponent(Component c) {
    components.remove(c);
  }

  public Entity getParent() {
    return parent;
  }

  public void setParent(Entity parent) {
    if (this.parent == null) {
      this.parent = parent;
    } else {
      this.parent.removeChild(this);
      this.parent = parent;
    }
  }

  public ArrayList<Entity> getChildren() {
    return children;
  }

  public void addChild(Entity e) {
    children.add(e);
    e.setParent(this);
  }

  public void removeChild(Entity e) {
    children.remove(e);
  }
}
