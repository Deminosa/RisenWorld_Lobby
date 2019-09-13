package de.deminosa.lobby.main.listeners;

import org.bukkit.Color;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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
		RocketBuilder builder = new RocketBuilder(event.getPlayer().getWorld(), event.getPlayer().getEyeLocation());
		builder.build(false, true, Type.BALL_LARGE, Color.BLUE, Color.AQUA, 3);
	}
	
}
