package de.deminosa.lobby.regedit;

import org.bukkit.event.Listener;

import de.deminosa.core.Core;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.border.LobbyBorder;
import de.deminosa.lobby.main.commands.Lobby;
import de.deminosa.lobby.main.listeners.InfoActionbar;
import de.deminosa.lobby.main.listeners.Join;
import de.deminosa.lobby.main.listeners.LobbyItems.Games;
import de.deminosa.lobby.main.listeners.LobbyItems.Shop;
import de.deminosa.lobby.main.listeners.secure.BlockedListeners;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	17:55:33 # 13.09.2019
*
*/

public class Toroku {

	public static final String PREFIX = "Emmy";
	
	public static void init() {
		addEvent(new Join());
		addEvent(new BlockedListeners());
		addEvent(new Games());
		addEvent(new Shop());
		
		Core.getInstance().registerCommand(new Lobby());
		CoreCache.regCoreTimer(new InfoActionbar());
		LobbyBorder.setWorldBoarder();
	}
	
	private static void addEvent(Listener listener) {
		RisenWorld_Lobby.getInstance().getServer().getPluginManager().registerEvents(listener, RisenWorld_Lobby.getInstance());
	}
	
	
}
