package com.mygdx.game.components;

import com.mygdx.game.entities.Entity;

/**
 * Created by Jacob on 3/29/2017.
 */
public abstract class Component {

  Entity parent;

  public Entity getParent() {
    return parent;
  }

  public void setParent(Entity set) {
    parent = set;
  }

}
