package de.deminosa.lobby.main.listeners.LobbyItems;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.lobby.regedit.Toroku;
import de.deminosa.lobby.utils.Utils;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	14:56:47 # 17.10.2019
*
*/

public class Shop implements Listener{

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getItem().equals(Utils.getSHOP())) {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				CorePlayer player = CoreCache.getCorePlayer(event.getPlayer());
				
				player.sendMessage(Toroku.PREFIX, "§cDemnächst verfügbar!");
			}else if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
				CorePlayer player = CoreCache.getCorePlayer(event.getPlayer());
				
				player.sendMessage(Toroku.PREFIX, "§7Wird bestätigt...");
				if(event.getPlayer().getName().equals("Deminosa")) {
					player.sendMessage(Toroku.PREFIX, "§aBestätigt.");
				}else {
					player.sendMessage(Toroku.PREFIX, "§cAbgelehnt!");
				}
			}
		}
	}
	
}
