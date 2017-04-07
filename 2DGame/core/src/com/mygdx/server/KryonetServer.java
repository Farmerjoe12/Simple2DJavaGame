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
import com.mygdx.game.components.inputComponent.InputComponent;
import com.mygdx.game.components.inputComponent.PlayerInput;
import com.mygdx.game.components.physicsComponent.Transform;
import com.mygdx.game.entities.characters.Player;
import com.mygdx.game.utilities.InputMapper;
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
        if (object instanceof Integer[]) {
          Integer[] in = (Integer[])object;
          Player player = getPlayerByID(in[0]);
          ArrayList<Integer> keys = new ArrayList<Integer>();
          for(int i = 1; i < in.length; i++){
            keys.add(in[i]);
          }
          InputMapper mapper = player.getComponent(PlayerInput.class).getMapper();
          if(mapper.containsCommand(keys)){
            mapper.getCommand(keys).executeCommand();
            server.sendToAllTCP(new String[]{
                "drawable",
                in[0]+"",
                tiledWorld,
                player.getComponent(Transform.class).getPosition().x+"",
                player.getComponent(Transform.class).getPosition().y+"",
                "0",
                player.getComponent(PlayerGraphics.class).getCurrentCol()+""
            });
          }


          //System.out.println("got message: ");
          for(int i = 0; i < in.length; i++)
          {
            //System.out.println("\t"+in[i]);
          }
        }
      }
    });

    server.addListener(new Listener() {
      @Override
      public void connected(Connection connection) {
        super.connected(connection);
        Player player = new Player();
        player.setId(connection.getID());
        players.add(player);
        server.sendToAllTCP(new String[]{
            "drawable",
            connection.getID()+"",
            "lastguardian_all.png",
            player.getComponent(Transform.class).getPosition().x+"",
            player.getComponent(Transform.class).getPosition().y+"",
            "0",
            player.getComponent(PlayerGraphics.class).getCurrentCol()+""
        });
      }
    });

    kryo = server.getKryo();
    kryo.register(String[].class);
    kryo.register(String.class);
    kryo.register(Integer.class);
  }

  public void dispose(){
    server.close();
    try {
      server.dispose();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Player getPlayerByID(int id){
    //System.out.println("searching for player with id "+ id);
    for(Player p : players){
      if(p.getId() == id){
        //System.out.println("Player found");
        return p;

      }
    }
    System.out.println("player not found");
    return new Player();
  }


}
