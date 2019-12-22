package de.deminosa.lobby.utils;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:45:46 # 10.11.2019
*
*/

public class GameChange {

	public static void sendGameState(Player player, int type, float state) {
		  PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(type, state);
		  ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		}
	
}
