package de.deminosa.lobby.regedit;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import de.deminosa.core.Core;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.mysql.ColumType;
import de.deminosa.core.utils.mysql.MySQL;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.border.LobbyBorder;
import de.deminosa.lobby.main.commands.DatenBankCommand;
import de.deminosa.lobby.main.commands.Lobby;
import de.deminosa.lobby.main.creates.CreatesManager;
import de.deminosa.lobby.main.daylogin.listeners.VillagerListener;
import de.deminosa.lobby.main.listeners.Join;
import de.deminosa.lobby.main.listeners.LobbyItems.Games;
import de.deminosa.lobby.main.listeners.LobbyItems.ShopListener;
import de.deminosa.lobby.main.listeners.players.PlayerInteractHandler;
import de.deminosa.lobby.main.listeners.secure.BlockedListeners;
import de.deminosa.lobby.main.shop.Shop;
import de.deminosa.lobby.main.shop.Items.pets.PetUitls;
import de.deminosa.lobby.main.timers.ParticelTimer;
import de.deminosa.lobby.main.timers.TestTimer;
import de.deminosa.lobby.utils.EntityControll;

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
		addEvent(new ShopListener());
		addEvent(new PlayerInteractHandler());
		addEvent(new PetUitls());
		addEvent(new VillagerListener());
		addEvent(new CreatesManager());
		
		MySQLcon();
		
		EntityControll.loadSettings();
		System.out.print("=====================[CMD]==============================");
		Core.getInstance().registerCommand(new Lobby());
		Core.getInstance().registerCommand(new DatenBankCommand());
		System.out.print("=====================[END]==============================");
		
		CoreCache.regCoreTimer(new ParticelTimer());
		Bukkit.getScheduler().runTaskTimer(RisenWorld_Lobby.getInstance(), new TestTimer(), 20, 20);
		LobbyBorder.setWorldBoarder();
		
		Shop.init();
	}
	
	private static void addEvent(Listener listener) {
		RisenWorld_Lobby.getInstance().getServer().getPluginManager().registerEvents(listener, RisenWorld_Lobby.getInstance());
	}
	
	private static void MySQLcon() {
		String[] colum = {"UUID", "allow"};
		ColumType[] type = {ColumType.VARCHAR_128, ColumType.VARCHAR_32};
		MySQL.createTable("AGB", colum, type);
		
		String[] JumpColum = {"UUID", "reach"};
		ColumType[] JumpType = {ColumType.VARCHAR_128, ColumType.VARCHAR_32};
		MySQL.createTable("jump", JumpColum, JumpType);
		
		String[] DayLogincolum = {"UUID", "LastDay", "LastMonth", "Streak"};
		ColumType[] DayLogintype = {ColumType.VARCHAR_128, ColumType.VARCHAR_32, ColumType.VARCHAR_32, ColumType.VARCHAR_32};
		MySQL.createTable("DayLogin", DayLogincolum, DayLogintype);
		
		String[] ShopColum = {"UUID", "item", "amount"};
		ColumType[] ShopType = {ColumType.VARCHAR_128, ColumType.VARCHAR_32, ColumType.INT};
		MySQL.createTable("Shop", ShopColum, ShopType);
		
		String[] reedemColum = {"ID", "UUID", "UID", "Action", "arguments"};
		ColumType[] reedemType = {ColumType.INT, ColumType.VARCHAR_128, ColumType.VARCHAR_32, ColumType.VARCHAR_32, ColumType.VARCHAR_2048};
		MySQL.createTable("Reedem", reedemColum, reedemType);
	}
	
	
}
