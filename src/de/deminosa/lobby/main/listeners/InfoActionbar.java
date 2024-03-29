package de.deminosa.lobby.main.listeners;

import org.bukkit.Bukkit;

import de.deminosa.core.Core;
import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.builders.CoreTimer;
import de.deminosa.core.cache.CoreCache;
import jump.Jump;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	20:42:49 # 17.09.2019
 *
 */

public class InfoActionbar implements CoreTimer{

	int t = 0, index = 0;

	@Override
	public void tick() {
		Bukkit.getOnlinePlayers().forEach((bukkitPlayer) ->{
			CorePlayer player = CoreCache.getCorePlayer(bukkitPlayer);

			if(!Jump.sucBlocks.containsKey(player.getBukkitPlayer())) {
				player.
				sendActionbar(IndexBuilder() + 
						" §6" + info[index].replace("%player%", bukkitPlayer.getName()) 
						+ " " + 
						IndexBuilder());
			}
		});
		t++;
		if(Core.getInstance().isDebug()) Bukkit.broadcastMessage("["+index+"] §a"+ (t/20) + "/" + getTimes() + "");
		if(t == 20*getTimes()) {
			t = 0;
			index++;
			if(index == info.length) {
				index = 0;
			}
		}
	}

	String[] info = {"Willkommen, §e%player%",
			"§2Lobby: §aUpdate in der gesammten Lobby",
			"§2Neue Games: §aTNT RUN §8- §aUHC §8- §aJumpLeague",
			"§2Tageslogin: §aErhalte eine Belohnung! Rechtsklick auf die Melone!"};

	private String IndexBuilder() {
		String s = "§8●";
		String builder = "";
		for(int i = 0; i < info.length; i++) {
			if(i == index) {
				builder += "§b●";
			}else {
				builder = builder + s;
			}
		}
		return builder;
	}
	
	private int getTimes() {
		int i = (info[index].replaceAll(" ", "").length() * 2)/3;
		return i;
	}
}
