package de.deminosa.lobby.main.listeners.players;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.gui.GUI;
import de.deminosa.core.utils.gui.GUIButton;
import de.deminosa.core.utils.itembuilder.ItemBuilder;

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
						.build();
			}
		});
		gui.open();
	}
}
