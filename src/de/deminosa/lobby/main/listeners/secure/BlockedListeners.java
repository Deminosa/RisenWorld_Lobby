package de.deminosa.lobby.main.listeners.secure;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import de.deminosa.coinmanager.Coins;
import de.deminosa.coinmanager.command.CoinsCommand.CoinAction;
import de.deminosa.coinmanager.command.LottoCommand.LottoAction;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.shop.Items.special.ToyCoinTNT;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	18:32:03 # 13.09.2019
*
*/

public class BlockedListeners implements Listener{

	@EventHandler
	public void onWeatherChangeEvent(WeatherChangeEvent event) {
		new BukkitRunnable() {
			@Override
			public void run() {
				event.getWorld().setStorm(false);
				event.getWorld().setThundering(false);
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 20);
	}
	
	@EventHandler
	public void onFoodLevelChangeEvent(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onInteractEvent(PlayerInteractEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {
		if(event.getItem().getType() == EntityType.DROPPED_ITEM) {
			for(String name : ToyCoinTNT.CoinUUID) {
				if(event.getItem().getUniqueId().toString().equals(name)) {
					int coins = ThreadLocalRandom.current().nextInt(3)+1;
					if(!ToyCoinTNT.coins.containsKey(event.getPlayer())) {
						ToyCoinTNT.coins.put(event.getPlayer(), coins);
					}else {
						ToyCoinTNT.coins.put(event.getPlayer(), ToyCoinTNT.coins.get(event.getPlayer()) + coins);
					}
					
					CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.ORB_PICKUP);
				}else {
					event.setCancelled(true);
				}
			}
			for(String name : ToyCoinTNT.LottoUUID) {
				if(event.getItem().getUniqueId().toString().equals(name)) {
					int coins = ThreadLocalRandom.current().nextInt(2)+1;
					if(!ToyCoinTNT.lotto.containsKey(event.getPlayer())) {
						ToyCoinTNT.lotto.put(event.getPlayer(), coins);
					}else {
						ToyCoinTNT.lotto.put(event.getPlayer(), ToyCoinTNT.lotto.get(event.getPlayer()) + coins);
					}
					
					CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.ORB_PICKUP);
				}else {
					event.setCancelled(true);
				}
			}
			for(String name : ToyCoinTNT.ChestUUID) {
				if(event.getItem().getUniqueId().toString().equals(name)) {
					int coins = ThreadLocalRandom.current().nextInt(2)+1;
					if(!ToyCoinTNT.chest.containsKey(event.getPlayer())) {
						ToyCoinTNT.chest.put(event.getPlayer(), coins);
					}else {
						ToyCoinTNT.chest.put(event.getPlayer(), ToyCoinTNT.chest.get(event.getPlayer()) + coins);
					}
					
					CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.ORB_PICKUP);
				}else {
					event.setCancelled(true);
				}
			}
			event.getItem().remove();
		}else {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event){
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event){
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		if(event.getEntityType() != EntityType.PLAYER) return;
		
		Player Damager = (Player)event.getDamager();
		Player getDamage = (Player)event.getEntity();
		
		if(Damager.getItemInHand().getType() == Material.STICK && 
				getDamage.getItemInHand().getType() == Material.STICK) {
			event.setCancelled(false);
		}else {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamageByBlock(EntityDamageByBlockEvent event){
		event.setCancelled(true);
	}
}
