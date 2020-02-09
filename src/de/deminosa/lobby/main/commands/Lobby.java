package de.deminosa.lobby.main.commands;

import org.bukkit.Bukkit;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.builders.command.CoreCommand;
import de.deminosa.core.utils.mysql.MySQL;
import de.deminosa.core.utils.warps.WarpManager;
import de.deminosa.lobby.utils.GameChange;

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
			player.sendMessage(prefix, "§6AGBReset §8| §6- §8| §7Reset AGB");
		}else if(args.length == 2) {
			if(args[1].equalsIgnoreCase("setSpawn")) {
				WarpManager.createWarp(player, "spawn");
				player.sendMessage(prefix, "§aSpawn Set/Change!");
			}else if(args[1].equalsIgnoreCase("test") || args[1].equalsIgnoreCase("-t")) {
				// https://www.spigotmc.org/threads/tutorial-gamestatechange-packet.143852/
				
			}else if(args[1].equalsIgnoreCase("setKnockFFA")) {
				WarpManager.createWarp(player, "KnockFFA");
				player.sendMessage(prefix, "§aKnockFFA Set/Change!");
			}else if(args[1].equalsIgnoreCase("setSkyPvP")) {
				WarpManager.createWarp(player, "SkyPvP");
				player.sendMessage(prefix, "§aSkyPvP Set/Change!");
			}else if(args[1].equalsIgnoreCase("AGBReset")) {
				player.sendMessage(prefix, "§7Wird Gelöscht...");
				MySQL.deleteRow("AGB", "UUID", player.getBukkitPlayer().getUniqueId().toString());
				player.sendMessage(prefix, "§7Gelöscht!");
			}else if(args[1].equalsIgnoreCase("setTNTRun")) {
				WarpManager.createWarp(player, "TNT-Run");
				player.sendMessage(prefix, "§aTNT Run Set/Change!");
			}else if(args[1].equalsIgnoreCase("setSkyBlock")) {
				WarpManager.createWarp(player, "SkyBlock");
				player.sendMessage(prefix, "§aSkyBlock Set/Change!");
			}else if(args[1].equalsIgnoreCase("setUHC")) {
				WarpManager.createWarp(player, "UHC");
				player.sendMessage(prefix, "§aUHC Set/Change!");
			}else if(args[1].equalsIgnoreCase("setJL")) {
				WarpManager.createWarp(player, "JL");
				player.sendMessage(prefix, "§aJL Set/Change!");
			}
		}else {
			GameChange.sendGameState(Bukkit.getPlayer(args[2]), Integer.valueOf(args[3]), Integer.valueOf(args[4]));
		}
	}

	@Override
	public boolean runNoPerms(CorePlayer player, String[] arg1) {
		player.sendMessage(prefix, "joa, warum nicht.");
		return true;
	}

}
