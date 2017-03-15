package net.Andrewcpu.NetworkExample;

import net.Andrewcpu.NetworkExample.client.ClientManager;
import net.Andrewcpu.NetworkExample.server.ServerManager;
import net.Andrewcpu.NetworkExample.utils.Log;

public class Start {
    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("server")){
            Log.d("Server Selected...");
            new ServerManager();

        }
        else{
            new ClientManager();
        }
    }
}
