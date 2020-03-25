package de.deminosa.lobby.main.commands;

import java.util.HashSet;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.builders.command.CoreCommand;
import de.deminosa.core.utils.UUIDFetcher;
import de.deminosa.core.utils.mysql.MySQL;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	16:11:30 # 07.03.2020
*
*/

public class DatenBankCommand implements CoreCommand{

	private HashSet<CorePlayer> allow = new HashSet<>();
	private String PW = "+4559";
	
	@Override
	public String command() {
		return "db";
	}

	@Override
	public String desception() {
		return "DatenBank";
	}

	@Override
	public boolean external() {
		return true;
	}

	@Override
	public String permission() {
		return "RisenWorld.DataBase.Command";
	}

	@Override
	public void run(CorePlayer player, String[] args) {
		if(args.length == 1) {
			if(!allow.contains(player)) {
				pwNeed(player);
			}else {
				player.sendMessage("DB", "§6/db -l §7Logout");
				player.sendMessage("DB", "§6/db update \n§7<table> <colum> <value> <where> <match[P=<name>]>");
				player.sendMessage("DB", "§6/db set <table> <colum> <value>");
				player.sendMessage("DB", "§6/db delete <table> <where> <match[P=<name>]>");
			}
		}else if(args.length == 2) {
			if(args[1].equals(PW)) {
				if(!allow.contains(player)) {
					allow.add(player);
					player.sendMessage("DB", "§aErfolgreich!");
					player.sendTitle(5, 20*5, 5, "§9DB §8- §aOK", "§7Verbindung erfolgreich!");
				}else {
					player.sendMessage("DB", "§6/db -l §7Logout");
				}
			}else if(args[1].equalsIgnoreCase("-l")) {
				if(allow.contains(player)) {
					allow.remove(player);
					player.sendTitle(5, 20*5, 5, "§9DB §8- §aOK", "§7Verbindung erfolgreich getrennt!");
				}else {
					pwNeed(player);
				}
			}else {
				player.sendMessage("DB", "§cEs ist ein Fehler aufgetreten!");
				pwNeed(player);
			}
		}else if(args.length == 7) {
			//db shop type player uid buy
			if(allow.contains(player)) {
				if(args[1].equalsIgnoreCase("update")) {
					String table = getUppercaseCheck(args[2]);
					String colum = getUppercaseCheck(args[3]);
					String value = getUppercaseCheck(args[4]);
					String where = getUppercaseCheck(args[5]);
					String match = getUppercaseCheck(args[6]);
					if(match.startsWith("p=")) {
						String name = match.replace("p=", "");
						match = UUIDFetcher.getUUID(name);
					}
					
					player.sendMessage("DB", "§7§o" + table + " " + colum + " " + value + " " + where + " " + match);
					MySQL.update(table, colum, value, where, match);
					player.sendMessage("DB", "§aBefehl gesendet!");
				}
			}else {
				pwNeed(player);
			}
		}else if(args.length == 5) {
			// /db table colum value
			if(allow.contains(player)) {
				if(args[1].equalsIgnoreCase("set")){
					String table = getUppercaseCheck(args[2]);
					String colum = getUppercaseCheck(args[3]);
					String value = getUppercaseCheck(args[4]);
					
					player.sendMessage("DB", "§7§o" + table + " " + colum + " " + value);
					MySQL.set(table, colum, value);
					player.sendMessage("DB", "§aBefehl gesendet!");
				}else if(args[1].equalsIgnoreCase("delete")){
					String table = getUppercaseCheck(args[2]);
					String where = getUppercaseCheck(args[3]);
					String match = getUppercaseCheck(args[4]);
					if(match.startsWith("p=")) {
						String name = match.replace("p=", "");
						match = UUIDFetcher.getUUID(name);
					}
					
					player.sendMessage("DB", "§7§o" + table + " " + where + " " + match);
					MySQL.deleteRow(table, where, match);
					player.sendMessage("DB", "§aBefehl gesendet!");
				}
			}else {
				pwNeed(player);
			}
		}else {
			player.sendMessage("DB", "size:"+args.length);
		}
	}

	@Override
	public boolean runNoPerms(CorePlayer arg0, String[] arg1) {
		return false;
	}

	private void pwNeed(CorePlayer player) {
		player.sendMessage("DB", "Bitte verifizieren sie sich!");
		player.sendTitle(5, 20*5, 5, "§9DB §8- §cERROR", "§7Verbindung Ungültig! Passwort Benötigt!");
	}
	
	private String getUppercaseCheck(String s){
		s = s.replace("daylogin", "DayLogin");
		s = s.replace("uuid", "UUID");
		s = s.replace("lastday", "LastDay");
		s = s.replace("lastmonth", "LastMonth");
		
		return s;
	}
	
}
