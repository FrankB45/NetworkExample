package net.Andrewcpu.NetworkExample.packets;

import com.esotericsoftware.kryo.Kryo;
import net.Andrewcpu.NetworkExample.utils.Log;

/**
 * Created by stein on 3/9/2017.
 */
public class PacketInformation {
    public static void registerPackets(Kryo kryo){
        kryo.register(MessagePacket.class);
        Log.d("Registered message packet class.");
    }
}
