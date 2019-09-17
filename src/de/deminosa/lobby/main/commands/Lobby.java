package de.deminosa.lobby.main.commands;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.builders.command.CoreCommand;

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
		}
	}

	@Override
	public boolean runNoPerms(CorePlayer arg0, String[] arg1) {
		return false;
	}

}
