package com.mygdx.game.entities.games;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.KryoSerialization;
import com.esotericsoftware.kryonet.Listener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.inputComponent.MultiplayerInput;
import com.mygdx.game.entities.worlds.TiledWorld;
import com.mygdx.server.KryonetServer;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jacob on 4/3/2017.
 */
public class Simple2DJavaGameMultiplayer extends Game {

  Client client;
  ArrayList<String[]> drawables = new ArrayList<String[]>();


  int clientID;
  String[] player;

  public Simple2DJavaGameMultiplayer() {
    new KryonetServer();
    TiledWorld tiledWorld = new TiledWorld();
    addChild(tiledWorld);

    Kryo kryo = new Kryo();
    kryo.setReferences(true);
    KryoSerialization serialization = new KryoSerialization(kryo);

    client = new Client(1024,256,serialization);
    kryo = client.getKryo();
    kryo.register(String.class);
    kryo.register(String[].class);
    kryo.register(Integer.class);
    kryo.register(Integer[].class);
    client.start();
    try {
      client.connect(5000, "127.0.0.1", 51197, 54777);
      clientID = client.getID();
    } catch (IOException e) {
      e.printStackTrace();
    }


    client.addListener(new Listener() {
      public void received (Connection connection, Object object) {
        if (object instanceof String[]) {
          String[] in = (String[])object;
          //System.out.println("client got message:");
          for(int i = 0; i < in.length; i++) {
            //System.out.println("\t"+in[i]);
          }
          if(in[0].equals("drawable")) {
            addDrawable(in);
            if(Integer.parseInt(in[1]) == client.getID()){
              player = in;
            }
          }
        }
      }
    });




  }

  public Client getClient() {
    return client;
  }

  public void addDrawable(String[] toAdd) {
    if(Integer.parseInt(toAdd[1]) == clientID){
      player = toAdd;
    }
    boolean contained = false;
    for(String[] s : drawables) {
      if(s[1].equals(toAdd[1])) {
        s[2] = toAdd[2];//texture (in this case the sprite map)
        s[3] = toAdd[3];//x position in float
        s[4] = toAdd[4];//y position in float
        s[5] = toAdd[5];//row in int
        s[6] = toAdd[6];//col in int
        contained = true;
        break;
      }
    }
    if(!contained) {
      drawables.add(toAdd);
    }
  }

  @Override
  public void updateCamera() {
    float x = Float.parseFloat(player[3]) + 16;
    float y = Float.parseFloat(player[4]) + 16;
    //System.out.println(x+","+y);
    getCamera().position.set(x, y, 0);
    checkMapBorders();
    getCamera().update();
    //System.out.println(getCamera().position.x+","+ getCamera().position.y);
  }

  public void dispose() {
    try {
      client.dispose();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void checkMapBorders() {
    MapProperties prop = getChild(TiledWorld.class).getProp();
    // get Tiled map properties
    int mapWidth = prop.get("width", Integer.class);
    int mapHeight = prop.get("height", Integer.class);
    int tilePixelWidth = prop.get("tilewidth", Integer.class);
    int tilePixelHeight = prop.get("tileheight", Integer.class);

    // 'width' and 'height' supply the amount of tiles in each row/column,
    // so you must multiply it by the tile width in order to get the map dimensions
    int mapPixelWidth = mapWidth * tilePixelWidth;
    int mapPixelHeight = mapHeight * tilePixelHeight;

    // determine when the camera will reach the ends of the map
    float viewportMinX = getCamera().viewportWidth / 2;
    float viewportMinY = getCamera().viewportHeight / 2;
    float viewportMaxX = mapPixelWidth - viewportMinX;
    float viewportMaxY = mapPixelHeight - viewportMinY;

    float x = Float.parseFloat(player[3]);
    float y = Float.parseFloat(player[4]);

    // preventing the camera from scrolling past the edge of the map
    if (x < viewportMinX) {
      getCamera().position.x = viewportMinX;
    } else if (x > viewportMaxX) {
      getCamera().position.x = viewportMaxX;
    } else {
      getCamera().position.x = x;
    }

    if (y < viewportMinY) {
      getCamera().position.y = viewportMinY;
    } else if (y > viewportMaxY) {
      getCamera().position.y = viewportMaxY;
    } else {
      getCamera().position.y = y;
    }
  }

  public void tick() {
    getComponent(MultiplayerInput.class).handleInput();
    if(player!=null){
      updateCamera();
    }

  }

  public int getClientID(){
    return clientID;
  }

  @Override
  public void draw(SpriteBatch batch) {

    getChild(TiledWorld.class).draw(batch);

    TextureRegion[][] tmp;
    batch.setProjectionMatrix(getCamera().combined);
    for(String[] s : drawables) {
      final int FRAME_COLS = 32, FRAME_ROWS = 23;
      Texture walkSheet = MyGdxGame.getAssetManager().get("lastguardian_all.png");
      tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS,
          walkSheet.getHeight() / FRAME_ROWS);
      batch.begin();
      batch.draw(tmp[Integer.parseInt(s[5])][Integer.parseInt(s[6])],Float.parseFloat(s[3]),Float.parseFloat(s[4]));
      batch.end();
    }

  }

}
