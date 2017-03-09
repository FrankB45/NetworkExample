package net.Andrewcpu.NetworkExample;

import net.Andrewcpu.NetworkExample.client.ClientManager;
import net.Andrewcpu.NetworkExample.server.ServerManager;

/**
 * Created by stein on 3/9/2017.
 */
public class Start {
    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("server")){
            new ServerManager();
        }
        else{
            new ClientManager();
        }
    }
}
