package de.deminosa.lobby.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.lobby.RisenWorld_Lobby;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	18:20:08 # 13.09.2019
*
*/

public class Utils {
	
	public static ItemStack getGAMES() {
		return new ItemBuilder(Material.FIREBALL).setName("§b➤ §6Games").build();
	}
	
	public static ItemStack getSHOP() {
		return new ItemBuilder(Material.ENDER_CHEST).setName("§b➤ §6Shop").build();
	}
	
	public static ItemStack getJUMP() {
		return new ItemBuilder(Material.BEACON).setName("§b➤ §6J'n'R").build();
	}
	
	public static ItemStack getREWARD() {
		return new ItemBuilder(Material.SPECKLED_MELON).setName("§b➤ §6Tagesbonus")
				.addUnsafeEnchantment(Enchantment.LUCK, 0).build();
	}
	
	public static ItemStack getEVENT() {
		return new ItemBuilder(Material.NETHER_STAR).setName("§b➤ §6Event Server").build();
	}
	
	
	public static void connectTo(Player player, String Server) {
		player.sendMessage("§7Connecting....");
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(Server);
        player.sendPluginMessage(RisenWorld_Lobby.getInstance(), "BungeeCord", out.toByteArray());
	}
}
