package de.deminosa.lobby;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.deminosa.lobby.regedit.Toroku;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	17:54:34 # 13.09.2019
*
*/

public class RisenWorld_Lobby extends JavaPlugin {

	private static RisenWorld_Lobby instance;

	@Override
	public void onEnable() {
		instance = this;
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Toroku.init();
			}
		}.runTaskLater(getInstance(), 2);
	}

	@Override
	public void onDisable() {
	}

	public static RisenWorld_Lobby getInstance() {
		return instance;
	}

}
