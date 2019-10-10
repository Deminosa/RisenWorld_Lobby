package de.deminosa.lobby.main.commands;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.builders.command.CoreCommand;
import de.deminosa.core.utils.warps.WarpManager;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	18:14:39 # 15.09.2019
*
*/

public class Lobby implements CoreCommand{

	private final String prefix = "Emmy";
	
	@Override
	public String command() {
		return "emmy";
	}

	@Override
	public String desception() {
		return "Rufe das Emmy System auf.";
	}

	@Override
	public boolean external() {
		return true;
	}

	@Override
	public String permission() {
		return "lobby.emmy";
	}

	@Override
	public void run(CorePlayer player, String[] args) {
		if(args.length == 1) {
			player.sendMessage(prefix, "§c/emmy <args>");
			player.sendMessage(prefix, "§7     args     |     alias     |     beschreibung");
			player.sendMessage(prefix, "§7");
			player.sendMessage(prefix, "§6test §8| §6-t §8| §7joa, warum nicht.");
			player.sendMessage(prefix, "§6setSpawn §8| §6- §8| §7Setze den Spawn!");
			player.sendMessage(prefix, "§6setKnockFFA §8| §6- §8| §7Setze den KnockFFA Warp!");
			player.sendMessage(prefix, "§6setSkyPvP §8| §6- §8| §7Setze den SkyPvP Warp!");
		}else if(args.length == 2) {
			if(args[1].equalsIgnoreCase("setSpawn")) {
				WarpManager.createWarp(player, "spawn");
				player.sendMessage(prefix, "§aSpawn Set/Change!");
			}else if(args[1].equalsIgnoreCase("test") || args[1].equalsIgnoreCase("-t")) {
				player.sendMessage(prefix, "joa, warum nicht.");
			}else if(args[1].equalsIgnoreCase("setKnockFFA")) {
				WarpManager.createWarp(player, "KnockFFA");
				player.sendMessage(prefix, "§aKnockFFA Set/Change!");
			}else if(args[1].equalsIgnoreCase("setSkyPvP")) {
				WarpManager.createWarp(player, "SkyPvP");
				player.sendMessage(prefix, "§aSkyPvP Set/Change!");
			}
		}else {
			
		}
	}

	@Override
	public boolean runNoPerms(CorePlayer player, String[] arg1) {
		player.sendMessage(prefix, "joa, warum nicht.");
		return true;
	}

}
