package de.deminosa.lobby.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.deminosa.core.utils.itembuilder.ItemBuilder;

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
		return new ItemBuilder(Material.BEACON).setName("§b➤ §6Run and Jump").build();
	}
	
}
