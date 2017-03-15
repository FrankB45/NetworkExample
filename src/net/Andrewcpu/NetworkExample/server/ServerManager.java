package net.Andrewcpu.NetworkExample.server;
import net.Andrewcpu.NetworkExample.client.Player;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import net.Andrewcpu.NetworkExample.packets.CordPacket;
import net.Andrewcpu.NetworkExample.packets.InputPacket;
import net.Andrewcpu.NetworkExample.packets.MessagePacket;
import net.Andrewcpu.NetworkExample.packets.PacketInformation;
import net.Andrewcpu.NetworkExample.utils.Log;
import sun.plugin2.message.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ServerManager {
    private Server server;
    public static final int TCP = 1000;
    public static final int UDP = 1000;
    private HashMap<Connection, UUID> playerMap = new HashMap<>();
    private ArrayList<Player> players = new ArrayList<>();

    public ServerManager() {
        server = new Server();
        server.start();
        try {
            server.bind(TCP, UDP);
            Log.d("Server Bound" + TCP);
        } catch (IOException e) {
            Log.e(e.getLocalizedMessage());
        }
        PacketInformation.registerPackets(server.getKryo());
        server.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                MessagePacket messagePacket = new MessagePacket("Server", "Welcome to the chat server");
                connection.sendTCP(messagePacket);
                Log.d("Sending message to client...");
                super.connected(connection);
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
            }

            @Override
            public void received(Connection connection, Object o) {
                if (o instanceof MessagePacket) {
                    MessagePacket messagePacket = (MessagePacket) o;
                    Log.d("Received message packet: " + messagePacket.getMessage() + " from " + messagePacket.getUser());
                    server.sendToAllTCP(messagePacket);
                }
                if (o instanceof InputPacket) {
                    InputPacket input = (InputPacket) o;
                    Log.d("Received Input Packet from: "+input.getName());
                    move(input);
                }
            }
        });
    }

    private void move(InputPacket input)
    {
        //Log.d("Move Received packet");
        switch (input.getDirection()) {
            case "new":
                players.add(new Player(input.getName(), new CordPacket(10, 10)));
                Log.d("Player Registered: " + input.getName());
                break;
            case "y+":
                for (Player player : players) {
                    if (player.getName().equals(input.getName())) {
                        CordPacket cords = new CordPacket(player.getCord().getX(), player.getCord().getY() + 1);
                        player.setCord(cords);
                        Log.d(player.getName() + " Moved to: " + player.getCord().getX() + " , " + player.getCord().getY());
                    }
                }

                break;
            case "y-":
                for (Player player : players) {
                    if (player.getName().equals(input.getName())) {
                        CordPacket cords = new CordPacket(player.getCord().getX(), player.getCord().getY() - 1);
                        player.setCord(cords);
                        Log.d(player.getName() + " Moved to: " + player.getCord().getX() + " , " + player.getCord().getY());
                    }
                }

                break;
            case "x+":
                for(Player player: players)
                    if(player.getName().equals(input.getName())){
                        CordPacket cords = new CordPacket(player.getCord().getX()+1,player.getCord().getY());
                        player.setCord(cords);
                        Log.d(player.getName() + " Moved to: " + player.getCord().getX() + " , " + player.getCord().getY());
                    }
                    break;
            case "x-":
                for(Player player: players){
                    if(player.getName().equals(input.getName())){
                        CordPacket cords = new CordPacket(player.getCord().getX()-1,player.getCord().getY());
                        player.setCord(cords);
                        Log.d(player.getName() + " Moved to: " + player.getCord().getX() + " , " + player.getCord().getY());
                    }
                }

        }




    }
}
