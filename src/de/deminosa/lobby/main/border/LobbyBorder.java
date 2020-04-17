package de.deminosa.lobby.main.border;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import de.deminosa.core.utils.warps.WarpManager;
import de.deminosa.lobby.RisenWorld_Lobby;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	15:10:25 # 20.10.2019
*
*/

public class LobbyBorder {

	private static int size = 110;

	public static void setWorldBoarder() {

		Location loc = WarpManager.getWarpLocation("spawn");
		new BukkitRunnable() {
			@Override
			public void run() {
				if(loc != null) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(p.getLocation().distance(loc) >= size) {
							Vector plV = p.getLocation().toVector();
							Vector spV = loc.toVector();
							Vector v = spV.clone().subtract(plV).multiply(1.0 / spV.distance(plV)).setY(0.5);
							p.setVelocity(v);
							p.playSound(p.getLocation(), Sound.ZOMBIE_WOODBREAK, 1, 1);
						}
					}
				}
			}
		}.runTaskTimer(RisenWorld_Lobby.getInstance(), 0l, 10);
//		new BukkitRunnable() {
//			@Override
//			public void run() {
//				if(loc != null) {
//					for(Player p : Bukkit.getOnlinePlayers()) {
//						if(p.getLocation().distance(loc) >= size-15) {
//							Location min = p.getLocation().add(-10,-10,-10);
//							Location max = p.getLocation().add(10,10,10);
//							for(int x = min.getBlockX(); x < max.getBlockX(); x++) {
//								for(int y = min.getBlockY(); y < max.getBlockY(); y++) {
//									for(int z = min.getBlockZ(); z < max.getBlockZ(); z++) {
//										Location loc2 = new Location(p.getWorld(), x, y, z);
//										if(loc2.distance(loc) > size && loc2.distance(loc) < size+1) {
//											if(random(0, 10) == 0) {
//												for(int i = 0; i < 10; i++) {
//													p.getWorld().playEffect(loc2, Effect.WITCH_MAGIC, 1);
//												}
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}else {
//					Bukkit.broadcastMessage("§cLocation §6Spawn§c not found!");
//				}
//			}
//		}.runTaskTimer(RisenWorld_Lobby.getInstance(), 0l, 15l);
	}

	private static int random(int min, int max) {
		Random random = new Random();
		return random.nextInt(max-min+1)+min;
	}
	
}
