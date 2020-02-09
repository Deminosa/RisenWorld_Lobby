package de.deminosa.lobby.main.listeners.LobbyItems;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.lobby.main.shop.Shop;
import de.deminosa.lobby.main.timers.ParticelTimer;
import de.deminosa.lobby.regedit.Toroku;
import de.deminosa.lobby.utils.Utils;
import jump.JumpGenerateEvent;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	14:56:47 # 17.10.2019
 *
 */

public class ShopListener implements Listener{

	@EventHandler
	public void onGenerateBlock(JumpGenerateEvent event) {
		ParticelTimer.jumpType = event.getGenerateJumpType();
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getItem() != null && event.getItem().equals(Utils.getSHOP())) {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				CorePlayer player = CoreCache.getCorePlayer(event.getPlayer());

				player.sendMessage(Toroku.PREFIX, "§cDer Shop wird gerade bearbeitet! \n§7Versuch es Später erneut!");
			}else if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
				CorePlayer player = CoreCache.getCorePlayer(event.getPlayer());

				player.sendMessage(Toroku.PREFIX, "§7Wird bestätigt...");
				if(event.getPlayer().isOp()) {
					player.sendMessage(Toroku.PREFIX, "§aBestätigt.");
					Shop.openInit(player);
				}else {
					player.sendMessage(Toroku.PREFIX, "§cAbgelehnt!");
				}
			}
		}
	}

}
