package net.Andrewcpu.NetworkExample.client;

import net.Andrewcpu.NetworkExample.packets.CordPacket;

/**
 * Created by Nick on 3/11/2017.
 */
public class Player {

    private String Name;
    private CordPacket cord;

    public Player(String name, CordPacket cords)
    {
        this.Name=name;
        this.cord=cords;
    }

    public String getName(){return this.Name;}
    public CordPacket getCord(){return this.cord;}
    public void setCord(CordPacket aCord){this.cord = aCord;}

}
