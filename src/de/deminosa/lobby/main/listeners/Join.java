package de.deminosa.lobby.main.listeners;

import org.bukkit.Color;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import de.deminosa.core.utils.warps.WarpManager;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.utils.Utils;
import de.deminosa.lobby.utils.rocket.RocketBuilder;

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
				RocketBuilder builder = new RocketBuilder(event.getPlayer().getWorld(), event.getPlayer().getEyeLocation());
				builder.build(false, true, Type.BALL_LARGE, Color.BLUE, Color.AQUA, 1);
				getItems(event.getPlayer());
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 2);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		event.setQuitMessage("");
	}
	
	private void getItems(Player player) {
		player.getInventory().setItem(0, Utils.getGAMES());
		player.getInventory().setItem(4, Utils.getSHOP());
		player.getInventory().setItem(8, Utils.getJUMP());
	}
	
}
