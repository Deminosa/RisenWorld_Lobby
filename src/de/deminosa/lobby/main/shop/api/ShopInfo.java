package de.deminosa.lobby.main.shop.api;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.deminosa.coinmanager.Coins;
import de.deminosa.core.utils.gui.GUIButton;
import de.deminosa.core.utils.itembuilder.ItemBuilder;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	17:47:02 # 22.12.2019
*
*/

public class ShopInfo {

	public static class Balance extends GUIButton{
		Player player;
		
		public Balance(Player player) {
			this.player = player;
		}
		
		@Override
		public ItemStack getIcon() {
			return new ItemBuilder(Material.SIGN)
					.setName("§6Coins")
					.addLoreLine("§b" + Coins.getCoins(player) + " Coins")
					.build();
		}

		@Override
		public void onClick(InventoryClickEvent arg0) {
			
		}
	}
	
}
