package com.mygdx.server;

/**
 * Created by Jacob on 4/1/2017.
 */

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.KryoSerialization;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.graphicsComponent.PlayerGraphics;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.entities.characters.Player;
import java.io.IOException;
import java.util.ArrayList;

public class KryonetServer
{

  Server server;
  String tiledWorld = "1.tmx";
  ArrayList<Player> players = new ArrayList<Player>();


  public KryonetServer() {
    Kryo kryo = new Kryo();
    kryo.setReferences(true);
    KryoSerialization serialization = new KryoSerialization(kryo);

    server = new Server(1024,256,serialization);
    server.start();
    try {
      server.bind(54555, 54777);
    } catch (IOException e) {
      e.printStackTrace();
    }

    server.addListener(new Listener() {
      public void received (Connection connection, Object object) {
        if (object instanceof String[]) {
          String[] in = (String[])object;
          System.out.println("got message: ");
          for(int i = 0; i < in.length; i++)
          {
            System.out.println("\t"+in[i]);
          }
        }
        if(object instanceof Integer) {
          Player player = new Player();
          player.setId((Integer)object);
          players.add(player);
        }
      }
    });

    kryo = server.getKryo();
    kryo.register(String[].class);
    kryo.register(String.class);
    kryo.register(Integer.class);
  }

  public void tick() {
    for(Player player : players){
      server.sendToAllTCP(new String[]{
          "drawable",
          player.getId()+"",
          tiledWorld,
          player.getComponent(Transform.class).getPosition().x+"",
          player.getComponent(Transform.class).getPosition().y+"",
          "0",
          player.getComponent(PlayerGraphics.class).getCurrentCol()+""
      });
    }

  }

  public void dispose(){
    server.close();
    try {
      server.dispose();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    final KryonetServer server = new KryonetServer();
    try {
      new Thread(new Runnable() {
        public void run() {
          while (true) {
            server.tick();
          }
        }
      });
    }catch(Exception e){
      System.out.println("disposing");
      server.dispose();
    }
  }

}
