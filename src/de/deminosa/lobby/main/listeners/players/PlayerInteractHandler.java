package de.deminosa.lobby.main.listeners.players;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

import de.deminosa.coinmanager.Coins;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.gui.GUI;
import de.deminosa.core.utils.gui.GUIButton;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.core.utils.mysql.MySQL;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	20:07:38 # 09.02.2020
 *
 */

public class PlayerInteractHandler implements Listener{

	@EventHandler
	public void onInteract(PlayerInteractAtEntityEvent event) {
		if(event.getRightClicked().getType() != EntityType.PLAYER) return; 
		
		GUI gui = new GUI(CoreCache.getCorePlayer(event.getPlayer()), "§6Spieler Menü", 9);
		gui.setButton(0, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.SKULL_ITEM)
						.setName("§6" + event.getRightClicked().getName())
						.setDurability((short)3)
						.setSkullOwner(event.getRightClicked().getName())
						.addLoreLine("")
						.addLoreLine("§7Coins: §b" + Coins.getCoins((Player)event.getRightClicked()))
						.addLoreLine("§7Token: §b" + Coins.getToken((Player)event.getRightClicked()))
						.addLoreLine("§7Kisten: §b" + Coins.getChest((Player)event.getRightClicked()))
						.build();
			}
		});
		
		gui.setButton(2, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {}

			@Override
			public ItemStack getIcon() {
				int sqlBlock = MySQL.getInt("jump", "reach", "UUID", event.getRightClicked().getUniqueId().toString());
				return new ItemBuilder(Material.BEACON)
						.setName("§6Jump and Run")
						.addLoreLine("")
						.addLoreLine("§7Jump Rekord §b" + sqlBlock)
						.build();
			}
		});
		
		gui.open();
	}
}
