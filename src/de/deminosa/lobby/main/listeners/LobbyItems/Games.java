package de.deminosa.lobby.main.listeners.LobbyItems;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.gui.GUI;
import de.deminosa.core.utils.gui.GUIButton;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.core.utils.warps.WarpManager;
import de.deminosa.lobby.utils.Utils;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	17:49:17 # 25.09.2019
*
*/

public class Games implements Listener{

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getItem().equals(Utils.getGAMES())) {
			CorePlayer player = CoreCache.getCorePlayer(event.getPlayer());
			
			GUI gui = new GUI(player, "§6Games", 54-9);
			
			gui.setButton(4, new GUIButton() {
				String warp = "";
				@Override
				public void onClick(InventoryClickEvent event) {
					player.getBukkitPlayer().closeInventory();
					player.teleport(WarpManager.getWarpLocation(warp));
					player.playsound(Sound.ENDERMAN_TELEPORT);
					player.sendTitle(0, 20*2, 5, "§b➤ §6Games", "§7"+warp);
				}
				
				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.BARRIER).setName("§6"+warp).build();
				}
			});
			
			gui.setButton(11, new GUIButton() {
				String warp = "";
				@Override
				public void onClick(InventoryClickEvent event) {
					player.getBukkitPlayer().closeInventory();
					player.teleport(WarpManager.getWarpLocation(warp));
					player.playsound(Sound.ENDERMAN_TELEPORT);
					player.sendTitle(0, 20*2, 5, "§b➤ §6Games", "§7"+warp);
				}
				
				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.BARRIER).setName("§6"+warp).build();
				}
			});
			
			gui.setButton(15, new GUIButton() {
				String warp = "";
				@Override
				public void onClick(InventoryClickEvent event) {
					player.getBukkitPlayer().closeInventory();
					player.teleport(WarpManager.getWarpLocation(warp));
					player.playsound(Sound.ENDERMAN_TELEPORT);
					player.sendTitle(0, 20*2, 5, "§b➤ §6Games", "§7"+warp);
				}
				
				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.BARRIER).setName("§6"+warp).build();
				}
			});
			
			gui.setButton(18, new GUIButton() {
				@Override
				public void onClick(InventoryClickEvent event) {
					player.getBukkitPlayer().closeInventory();
					player.teleport(WarpManager.getWarpLocation("KnockFFA"));
					player.playsound(Sound.ENDERMAN_TELEPORT);
					player.sendTitle(0, 20*2, 5, "§b➤ §6Games", "§7KnockFFA");
				}
				
				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.STICK).addEnchant(Enchantment.KNOCKBACK, 1).setName("§6KnockFFA").build();
				}
			});
			
			gui.setButton(22, new GUIButton() {
				@Override
				public void onClick(InventoryClickEvent event) {
					player.getBukkitPlayer().closeInventory();
					player.teleport(WarpManager.getWarpLocation("spawn"));
					player.playsound(Sound.ENDERMAN_TELEPORT);
					player.sendTitle(0, 20*2, 5, "§b➤ §6Games", "§7Spawn");
				}
				
				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.NETHER_STAR).setName("§6Spawn").build();
				}
			});
			
			gui.setButton(26, new GUIButton() {
				String warp = "";
				@Override
				public void onClick(InventoryClickEvent event) {
					player.getBukkitPlayer().closeInventory();
					player.teleport(WarpManager.getWarpLocation(warp));
					player.playsound(Sound.ENDERMAN_TELEPORT);
					player.sendTitle(0, 20*2, 5, "§b➤ §6Games", "§7"+warp);
				}
				
				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.BARRIER).setName("§6"+warp).build();
				}
			});
			
			gui.setButton(29, new GUIButton() {
				String warp = "";
				@Override
				public void onClick(InventoryClickEvent event) {
					player.getBukkitPlayer().closeInventory();
					player.teleport(WarpManager.getWarpLocation(warp));
					player.playsound(Sound.ENDERMAN_TELEPORT);
					player.sendTitle(0, 20*2, 5, "§b➤ §6Games", "§7"+warp);
				}
				
				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.BARRIER).setName("§6"+warp).build();
				}
			});
			
			gui.setButton(33, new GUIButton() {
				String warp = "";
				@Override
				public void onClick(InventoryClickEvent event) {
					player.getBukkitPlayer().closeInventory();
					player.teleport(WarpManager.getWarpLocation(warp));
					player.playsound(Sound.ENDERMAN_TELEPORT);
					player.sendTitle(0, 20*2, 5, "§b➤ §6Games", "§7"+warp);
				}
				
				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.BARRIER).setName("§6"+warp).build();
				}
			});
			
			gui.setButton(40, new GUIButton() {
				String warp = "";
				@Override
				public void onClick(InventoryClickEvent event) {
					player.getBukkitPlayer().closeInventory();
					player.teleport(WarpManager.getWarpLocation(warp));
					player.playsound(Sound.ENDERMAN_TELEPORT);
					player.sendTitle(0, 20*2, 5, "§b➤ §6Games", "§7"+warp);
				}
				
				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.BARRIER).setName("§6"+warp).build();
				}
			});
			
			gui.open();
		}
	}
	
}
