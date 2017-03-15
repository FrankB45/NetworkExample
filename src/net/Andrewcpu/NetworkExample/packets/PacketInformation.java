package net.Andrewcpu.NetworkExample.packets;

import com.esotericsoftware.kryo.Kryo;
import net.Andrewcpu.NetworkExample.utils.Log;

public class PacketInformation {
    public static void registerPackets(Kryo kryo){
        kryo.register(MessagePacket.class);
        Log.d("Registered message packet class.");
        kryo.register(InputPacket.class);
        Log.d("Registered Input Class");
    }
}
