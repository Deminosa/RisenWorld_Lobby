package de.deminosa.lobby.main.shop.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.deminosa.core.builders.CorePlayer;

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
	public void getAction(Player player);
	public ItemStack getIcon(CorePlayer player);
	public int getItemID();
	public int getSlot();
	public int getItemLevel();
	public boolean canBuying();
	
}
