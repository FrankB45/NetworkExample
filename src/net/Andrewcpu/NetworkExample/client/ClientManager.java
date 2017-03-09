package net.Andrewcpu.NetworkExample.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.sun.deploy.util.SessionState;
import net.Andrewcpu.NetworkExample.packets.MessagePacket;
import net.Andrewcpu.NetworkExample.packets.PacketInformation;
import net.Andrewcpu.NetworkExample.server.ServerManager;
import net.Andrewcpu.NetworkExample.utils.Log;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by stein on 3/9/2017.
 */
public class ClientManager {
    private Client client;
    private ChatUI chatUI;
    public ClientManager(){
        Log.d("Starting client...");
        client = new Client();
        client.start();
        PacketInformation.registerPackets(client.getKryo());
        try {
            client.connect(5000,"home.andrewcpu.net", ServerManager.TCP,ServerManager.UDP);
        } catch (IOException e) {
            Log.e(e.getLocalizedMessage());
        }
        chatUI = new ChatUI(this);
        client.addListener(new Listener(){
            @Override
            public void connected(Connection connection) {
                super.connected(connection);
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
            }

            @Override
            public void received(Connection connection, Object o) {
                if(o instanceof MessagePacket){
                    MessagePacket messagePacket = (MessagePacket)o;
                    Log.d(messagePacket.getUser() + ": " + messagePacket.getMessage());
                    chatUI.addMessage(messagePacket);
                    JOptionPane.showMessageDialog(null, messagePacket.getMessage(), messagePacket.getUser(), 1);
                }
            }
        });

    }
    public void sendMessage(String s){
        client.sendTCP(new MessagePacket("Andrew",s));
    }
}
