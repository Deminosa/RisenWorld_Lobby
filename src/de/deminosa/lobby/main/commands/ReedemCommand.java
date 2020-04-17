package de.deminosa.lobby.main.commands;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.builders.command.CoreCommand;
import de.deminosa.lobby.main.reedem.ReedemHandler;
import de.deminosa.lobby.main.reedem.ReedemType;
import de.deminosa.lobby.main.shop.api.ShopType;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	20:43:00 # 25.11.2019
 *
 */

public class ReedemCommand implements CoreCommand{

	private String prefix = "Redeem";

	@Override
	public String command() {
		return "code";
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
				// /reedem create <type> <value>
				player.sendMessage(prefix, "/code create <type> <value>");
				player.sendMessage(prefix, "/code types");
			}else if(args[1].equalsIgnoreCase("types")) {
				player.sendMessage(prefix, "§e========[§bTypes§e]========");
				for(ReedemType type : ReedemType.values()) {
					player.sendMessage(prefix, "§a" + type.name());
				}
			}else {
				runNoPerms(player, args);
			}
		}
		if(args.length == 4) {
			if(args[1].equalsIgnoreCase("create")) {
				ReedemType type = ReedemType.valueOf(args[2].toUpperCase());
				int value = Integer.valueOf(args[3]);
				
				ReedemHandler.create(player, type, value);
			}
		}
	}

	@Override
	public boolean runNoPerms(CorePlayer player, String[] args) {
		if(args.length == 1) {
			player.sendMessage("Redeem", "§c/code <id>");
		}
		if(args.length == 2) {
			ReedemHandler.use(player, args[1]);
		}
		return true;
	}

}
