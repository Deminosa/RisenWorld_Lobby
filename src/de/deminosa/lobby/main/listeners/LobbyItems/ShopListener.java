package de.deminosa.lobby.main.listeners.LobbyItems;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.lobby.main.shop.Shop;
import de.deminosa.lobby.main.timers.ParticelTimer;
import de.deminosa.lobby.utils.Utils;
import jump.Jump;
import jump.JumpGenerateEvent;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	14:56:47 # 17.10.2019
 *
 */

public class ShopListener implements Listener{

	private HashMap<CorePlayer, Float> jumpNumber = new HashMap<>();
	
	@EventHandler
	public void onGenerateBlock(JumpGenerateEvent event) {
		ParticelTimer.jumpType = event.getGenerateJumpType();
	}
	
	@EventHandler
	public void onKlick(InventoryClickEvent event) {
		if(event.getInventory().getType() == InventoryType.CRAFTING) {
			if(event.getAction() == InventoryAction.NOTHING) return;
			if(event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) return;
			if(event.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD) return;
			if(event.getAction() == InventoryAction.UNKNOWN) return;
			if(event.getRawSlot() <= 35) {
				if(event.getClickedInventory().getContents()[event.getRawSlot()] != null && event.getClickedInventory().getContents()[event.getRawSlot()].getType() == Material.GLOWSTONE) {
					Utils.connectTo((Player)event.getWhoClicked(), "Event-1");
				}
				
				if(event.getClickedInventory().getContents()[event.getRawSlot()] != null && event.getClickedInventory().getContents()[event.getRawSlot()].getType() == Material.CHEST) {
					
				}
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getItem() != null && event.getItem().equals(Utils.getSHOP())) {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				CorePlayer player = CoreCache.getCorePlayer(event.getPlayer());

				Shop.openInit(player);
			}
		}
		
		if(event.getItem() != null && event.getItem().getType() == Material.BLAZE_ROD) {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				CorePlayer player = CoreCache.getCorePlayer(event.getPlayer());

				if(!jumpNumber.containsKey(player)) {
					jumpNumber.put(player, 1.0f);
				}
				
				if(player.getLocation().subtract(0,1,0).getBlock().getType() != Material.AIR
						&& !Jump.sucBlocks.containsKey(player.getBukkitPlayer())) {
					player.editVelocity(0f, jumpNumber.get(player), 0f);
				}else {
					player.sendMessage("Shop", "Du bist nicht auf dem Boden!");
				}
			}else {
				CorePlayer player = CoreCache.getCorePlayer(event.getPlayer());
				
				if(!jumpNumber.containsKey(player)) {
					jumpNumber.put(player, 1.0f);
				}
				
				if(jumpNumber.get(player) > 1.75) {
					jumpNumber.put(player, 1.0f);
				}else {
					jumpNumber.put(player, jumpNumber.get(player)+0.25f);
				}
				player.getBukkitPlayer().getInventory().setItem(7, new ItemBuilder(Material.BLAZE_ROD).setName("§6Jump §8[§b"+jumpNumber.get(player)+"§8]")
						.build());
			}
		}
	}

}
