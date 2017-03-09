package net.Andrewcpu.NetworkExample.packets;

/**
 * Created by stein on 3/9/2017.
 */
public class MessagePacket {
    private String user;
    private String message;
    public MessagePacket(String user, String message){
        this.user = user;
        this.message = message;
    }
    public MessagePacket(){
        this.user = "";
        this.message = "";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
