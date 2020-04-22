package de.deminosa.lobby.main.shop.Items.verwandlung;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.lobby.RisenWorld_Lobby;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	21:20:33 # 12.04.2020
*
*/

public class VerwandlungsManager {

	private static HashMap<Player, Integer> runningIDs = new HashMap<>();
	private static HashMap<Player, LivingEntity> pets = new HashMap<>();
	
	public static void follow(Player player, LivingEntity entity, double d) {
		final LivingEntity e = entity;
		final Player p = player;
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20*9999, 1), true);
		
		int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(RisenWorld_Lobby.getInstance(), new Runnable() {
			@Override
			public void run() {
//				((EntityInsentient) ((CraftEntity) e).getHandle()).getNavigation().a(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), f);
				e.teleport(p);
				if(e.getLocation().distance(p.getLocation()) > 17) {
					e.teleport(p);
					CoreCache.getCorePlayer(p).playsound(Sound.ENDERMAN_TELEPORT);
				}
			}
		}, 1, 1);
		
		runningIDs.put(p, id);
		pets.put(p, e);
	}
	
	public static void stopFollow(Player player) {
		if(runningIDs.containsKey(player)) {
			if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
				player.removePotionEffect(PotionEffectType.INVISIBILITY);
			}
			Bukkit.getScheduler().cancelTask(runningIDs.get(player));
			runningIDs.remove(player);
			pets.get(player).remove();
			pets.remove(player);
		}
	}
}
