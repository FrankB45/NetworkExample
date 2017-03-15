package net.Andrewcpu.NetworkExample.packets;

/**
 * Created by Nick on 3/11/2017.
 */
public class InputPacket {
    private String name;
    private String direction;
    public InputPacket()
    {
        this.name="Default";
        this.direction="null";
    }
    public InputPacket(String n, String dir)
    {
        this.name=n;
        this.direction=dir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
