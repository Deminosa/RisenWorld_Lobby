package de.deminosa.lobby.main.commands;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.builders.command.CoreCommand;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	20:43:00 # 25.11.2019
 *
 */

public class ReedemCommand implements CoreCommand{

	private String prefix = "Reedem";

	@Override
	public String command() {
		return "reedem";
	}

	@Override
	public String desception() {
		return "";
	}

	@Override
	public boolean external() {
		return true;
	}

	@Override
	public String permission() {
		return "System.command.reedem";
	}

	@Override
	public void run(CorePlayer player, String[] args) {
		if(args.length == 1) {
			runNoPerms(player, args);
		}
		if(args.length == 2) {
			if(args[1].equalsIgnoreCase("help")) {
				// /reedem create <player|name> <action> <value>
				player.sendMessage(prefix, "/reedem create <player|name> <type/Action> <value>");
				player.sendMessage(prefix, "/reedem action §7Zeigt dir eine liste an.");
				player.sendMessage(prefix, "/reedem delete <UID|ID> [value]");
			}else if(args[1].equalsIgnoreCase("action")) {
				player.sendMessage(prefix, "§e========[§bAction§e]========");
				
			}else {
				runNoPerms(player, args);
			}
		}
		if(args.length == 4) {

		}
		if(args.length == 5) {

		}
	}

	@Override
	public boolean runNoPerms(CorePlayer arg0, String[] arg1) {
		return false;
	}

}
