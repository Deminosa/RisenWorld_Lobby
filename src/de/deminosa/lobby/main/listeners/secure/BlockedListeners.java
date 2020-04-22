package de.deminosa.lobby.main.listeners.secure;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.scheduler.BukkitRunnable;

import de.deminosa.core.cache.CoreCache;
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

	private HashMap<Player, Integer> wait = new HashMap<>();
	
	private boolean isWaiting(Player player) {
		return wait.containsKey(player);
	}
	
	private void setWaiting(Player player, int sec) {
		int time = (int) (System.currentTimeMillis()/1000)+sec;
		wait.put(player, time);
	}
	
	private boolean isTimerNotEnd(Player player) {
		int currentTime = (int) (System.currentTimeMillis()/1000);
		int timer = wait.get(player) - currentTime;
		if(timer >= 1) {
			return true;
		}else {
			removeWaiting(player);
			return false;
		}
	}
	
	private void removeWaiting(Player player) {
		wait.remove(player);
	}
	
	@EventHandler
	private void onEggland() {
		
	}
	
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
		if(event.getClickedBlock() != null) {
			if(event.getItem() != null && event.getItem().getType() == Material.TNT) {
				event.setCancelled(false);
			}else {
				event.setCancelled(true);
			}
		}else {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
//		Player p = event.getPlayer();
		Block b = event.getBlock();
		
		if(b.getType() == Material.TNT) {
			if(!isWaiting(event.getPlayer())) {
				setWaiting(event.getPlayer(), 30);
				b.setType(Material.AIR);
				b.getWorld().spawn(b.getLocation(), TNTPrimed.class).setFuseTicks(20*5);
			}else {
				if(isTimerNotEnd(event.getPlayer())) {
					CoreCache.getCorePlayer(event.getPlayer()).sendMessage("AC", "Bitte warte...");
				}else {
					CoreCache.getCorePlayer(event.getPlayer()).sendMessage("AC", "Bitte erneut versuchen...");
				}
			}
			event.setCancelled(true);
		}else {
			event.setCancelled(true);
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onExplode(EntityExplodeEvent event) {
		HashMap<Location, Material> blocks = new HashMap<>();
		HashMap<Location, Byte> subid = new HashMap<>();
		for(Block b : event.blockList()) {
			if(b.getType() != Material.BARRIER && b.getType() != Material.ENDER_PORTAL_FRAME &&
					b.getType() != Material.SIGN_POST && b.getType() != Material.SIGN &&
					b.getType() != Material.WOOD_DOOR && b.getType() != Material.FLOWER_POT) {
				blocks.put(b.getLocation(), b.getType());
				subid.put(b.getLocation(), b.getData());
			}
		}
		event.blockList().clear();
		for(Location l : blocks.keySet()) {
			l.getBlock().setType(Material.BARRIER);
		}
		
		new BukkitRunnable() {
			@Override
			public void run() {
				int i = 1;
				for(Location l : blocks.keySet()) {
					i++;
					new BukkitRunnable() {
						@Override
						public void run() {
							l.getBlock().setType(blocks.get(l));
							l.getBlock().setData(subid.get(l));
						}
					}.runTaskLater(RisenWorld_Lobby.getInstance(), 2*i);
				}
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 20*10);
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
		event.getItem().remove();
		event.setCancelled(true);
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
				}
			}
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
		if(event.getDamager().getType() == EntityType.PRIMED_TNT) return;
		
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
