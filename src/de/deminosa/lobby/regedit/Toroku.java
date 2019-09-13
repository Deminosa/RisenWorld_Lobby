package de.deminosa.lobby.regedit;

import org.bukkit.event.Listener;

import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.Join;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	17:55:33 # 13.09.2019
*
*/

public class Toroku {

	public static void init() {
		addEvent(new Join());
	}
	
	private static void addEvent(Listener listener) {
		RisenWorld_Lobby.getInstance().getServer().getPluginManager().registerEvents(listener, RisenWorld_Lobby.getInstance());
	}
	
}
