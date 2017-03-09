package net.Andrewcpu.NetworkExample.client;

import net.Andrewcpu.NetworkExample.packets.MessagePacket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

/**
 * Created by stein on 3/9/2017.
 */
public class ChatUI extends JFrame implements KeyListener{
    private List<Message> messages = new ArrayList<>();
    private ClientManager clientManager;
    public ChatUI(ClientManager manager){
        setLayout(null);
        setBounds(0,0,500,500);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addKeyListener(this);
        clientManager = manager;
    }
    public void addMessage(MessagePacket packet){
        Message message = new Message(packet.getUser(), packet.getMessage());
        messages.add(0,message);
        repaint();
    }
    @Override
    public void paint(Graphics graphics){

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            String s = JOptionPane.showInputDialog(null,"Enter a message");
            clientManager.sendMessage(s);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
