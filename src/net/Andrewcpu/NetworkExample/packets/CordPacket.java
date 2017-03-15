package net.Andrewcpu.NetworkExample.packets;

public class CordPacket {

    private int x;
    private int y;
    public CordPacket(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
    public CordPacket()
    {
        this.x=100;
        this.y=100;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
