package de.deminosa.lobby.main.listeners;

import org.bukkit.Bukkit;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.builders.CoreTimer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.lobby.utils.Utils;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:42:49 # 17.09.2019
*
*/

public class InfoActionbar implements CoreTimer{

	@Override
	public void tick() {
		Bukkit.getOnlinePlayers().forEach((bukkitPlayer) ->{
			CorePlayer player = CoreCache.getCorePlayer(bukkitPlayer);
			
			if(player.getBukkitPlayer().getItemInHand().equals(Utils.getGAMES())) {
				player.sendActionbar("§6Games §8● §2Öffnet ein auswahl Menü");
			}else if(player.getBukkitPlayer().getItemInHand().equals(Utils.getJUMP())) {
				player.sendActionbar("§6Run and Jump §8● §2Startet ein Endless JnR");
			}else if(player.getBukkitPlayer().getItemInHand().equals(Utils.getSHOP())) {
				player.sendActionbar("§6Run and Jump §8● §2Öffne den Lobby Shop");
			}else {
				player.sendActionbar("§7<Bitte, Item auswählen>");
			}
		});
	}
	
}
