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
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.core.utils.warps.WarpManager;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.agb.AGB;
import de.deminosa.lobby.utils.GameChange;
import de.deminosa.lobby.utils.Utils;
import de.deminosa.lobby.utils.rocket.RocketBuilder;
import jump.JumpEndEvent;

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
//				RocketBuilder builder = new RocketBuilder(event.getPlayer().getWorld(), event.getPlayer().getEyeLocation());
//				builder.build(false, true, Type.BALL_LARGE, Color.BLUE, Color.AQUA, 1);
				getItems(event.getPlayer());
				GameChange.sendGameState(event.getPlayer(), 3, -1);
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 2);
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
		player.getInventory().setItem(0, Utils.getGAMES());
		player.getInventory().setItem(2, Utils.getSHOP());
		player.getInventory().setItem(4, new ItemBuilder(Material.BARRIER).setName("§c§lDemnächst").build());
		player.getInventory().setItem(6, Utils.getEVENT());
		player.getInventory().setItem(8, Utils.getJUMP());
	}
	
	@EventHandler
	public void JumpEnd(JumpEndEvent event) {
		int block = event.getJumpedBlocks();
		
		int coins = block/5;
		Coins.action(CoinAction.ADD, event.getPlayer(), coins);
		event.setMessage("§9Coins §7Du hast §6" + coins + " coin(s) §7bekommen für §6"+block+" Blöcke§7!");
	}
	
}
