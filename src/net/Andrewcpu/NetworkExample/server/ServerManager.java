package net.Andrewcpu.NetworkExample.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import net.Andrewcpu.NetworkExample.packets.MessagePacket;
import net.Andrewcpu.NetworkExample.packets.PacketInformation;
import net.Andrewcpu.NetworkExample.utils.Log;
import sun.plugin2.message.Message;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by stein on 3/9/2017.
 */
public class ServerManager {
    private Server server;
    public static final int TCP = 1000;
    public static final int UDP = 1000;
    private HashMap<Connection,UUID> playerMap = new HashMap<>();
    public ServerManager(){
        server = new Server();
        server.start();
        try {
            server.bind(TCP,UDP);
        } catch (IOException e) {
            Log.e(e.getLocalizedMessage());
        }
        PacketInformation.registerPackets(server.getKryo());
        server.addListener(new Listener(){
            @Override
            public void connected(Connection connection) {
                MessagePacket messagePacket = new MessagePacket("Server","Welcome to the chat server");
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
            }
        });
    }
}
