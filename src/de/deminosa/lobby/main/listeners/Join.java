package de.deminosa.lobby.main.listeners;

import org.bukkit.Color;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import de.deminosa.coinmanager.Coins;
import de.deminosa.coinmanager.command.CoinsCommand.CoinAction;
import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.UUIDFetcher;
import de.deminosa.core.utils.Var;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.core.utils.warps.WarpManager;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.agb.AGB;
import de.deminosa.lobby.utils.GameChange;
import de.deminosa.lobby.utils.Utils;
import de.deminosa.lobby.utils.rocket.RocketBuilder;
import jump.JumpEndEvent;
import jump.var;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	18:18:34 # 13.09.2019
*
*/

public class Join implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage("");
		
		event.getPlayer().teleport(WarpManager.getWarpLocation("spawn"));
		
		new BukkitRunnable() {
			@Override
			public void run() {
				getItems(event.getPlayer());
				GameChange.sendGameState(event.getPlayer(), 3, -1);
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 2);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(!event.getPlayer().getName().equalsIgnoreCase("Deminosa")) {
					if(var.Chance(50)) {
//						event.getPlayer().kickPlayer("§bEmmy §8|\n"
//								+ "§7Du wurdest für auffälliges Verhalten vom Server Geworfen!\n"
//								+ "§6\n"
//								+ "§6Sollte dies ein Fehler sein, melde dich beim Support!");
					}
				}
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 20*10);
	}
	
	@EventHandler
	public void ItemHolder(PlayerItemHeldEvent event) {
		CorePlayer player = CoreCache.getCorePlayer(event.getPlayer());
		player.playsound(Sound.CLICK);
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		AGB.open(event.getPlayer());
	}
	
	@EventHandler
	public void onMove(InventoryCloseEvent event) {
		new BukkitRunnable() {
			@Override
			public void run() {
				AGB.open((Player)event.getPlayer());
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 40);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		event.setQuitMessage("");
	}
	
	private void getItems(Player player) {
		for(int i = 0; i < 9; i++) {
			player.getInventory().setItem(i, new ItemBuilder(Material.BARRIER).setName("§c§lLoading").build());
		}
		
		new BukkitRunnable() {
			@Override
			public void run() {
				player.getInventory().setItem(0, Utils.getGAMES());
				player.getInventory().setItem(8, Utils.getSHOP());
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 40);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				player.getInventory().setItem(7, Utils.getTOY());
				player.getInventory().setItem(1, Utils.getJUMP());
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 50);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				player.getInventory().setItem(6, null);
				player.getInventory().setItem(2, null);
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 60);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				player.getInventory().setItem(5, null);
				player.getInventory().setItem(3, null);
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 70);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				player.getInventory().setItem(4, Utils.getREWARD());
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 80);
		
		for(int i = 9; i < 36; i++) {
			player.getInventory().setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE)
					.setDurability((short)15)
					.setName("§c").build());	
		}
		
		player.getInventory().setItem(20, new ItemBuilder(Material.REDSTONE).setName("§c§lDemnächst")
				.addLoreLine("§7Geplant: §6Eintellungen").build());
		player.getInventory().setItem(22, new ItemBuilder(Material.GLOWSTONE).setName("§6Event Server")
				.addLoreLine("§7Verbinde dich mit dem Event Server!").build());
		player.getInventory().setItem(24, new ItemBuilder(Material.CHEST).setName("§c§lDemnächst")
				.addLoreLine("§7Geplant: §6Lotto").build());
	}
	
	@EventHandler
	public void JumpEnd(JumpEndEvent event) {
		int block = event.getJumpedBlocks();
		
		int coins = block/5;
		Coins.action(CoinAction.ADD, event.getPlayer(), coins);
		event.setMessage("§9Coins §7Du hast §6" + coins + " coin(s) §7bekommen für §6"+block+" Blöcke§7!");
	}
	
}
