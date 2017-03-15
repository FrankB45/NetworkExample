package net.Andrewcpu.NetworkExample.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.sun.deploy.util.SessionState;
import net.Andrewcpu.NetworkExample.packets.CordPacket;
import net.Andrewcpu.NetworkExample.packets.InputPacket;
import net.Andrewcpu.NetworkExample.packets.MessagePacket;
import net.Andrewcpu.NetworkExample.packets.PacketInformation;
import net.Andrewcpu.NetworkExample.server.ServerManager;
import net.Andrewcpu.NetworkExample.utils.Log;

import javax.swing.*;
import java.io.IOException;

public class ClientManager {
    private Client client;
    private ChatUI chatUI;
    private Player player;
    public ClientManager(){
        Log.d("Starting client...");
        client = new Client();
        client.start();
        PacketInformation.registerPackets(client.getKryo());
        try {
            client.connect(5000,"127.0.0.1", ServerManager.TCP,ServerManager.UDP);
            Log.d("Connected..");
        } catch (IOException e) {
            Log.e(e.getLocalizedMessage());
        }
        chatUI = new ChatUI(this);
        client.sendTCP(new InputPacket("Frank","new"));
        Log.d("TCP New sent..");
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
    public void sendCord(String dir){
        client.sendTCP(new InputPacket("Frank",dir));
        Log.d("Cord Sent.."+dir);
    }
}
