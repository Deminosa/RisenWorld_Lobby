package de.deminosa.lobby.main.shop.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	17:13:59 # 17.07.2019
*
*/

public interface ShopItemBuilder {

	public int getPrice();
	public String getItemName();
	public ItemStack getItem(Player player);
	public ItemStack getIcon();
	public int getItemID();
	public int getSlot();
	public int getItemLevel();
	
}
