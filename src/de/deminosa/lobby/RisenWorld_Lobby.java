package de.deminosa.lobby;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.lobby.regedit.Toroku;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	17:54:34 # 13.09.2019
*
*/

public class RisenWorld_Lobby extends JavaPlugin {

	private static RisenWorld_Lobby instance;

	@Override
	public void onEnable() {
		instance = this;
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		new BukkitRunnable() {
			@Override
			public void run() {
				Toroku.init();
				tabInitText();
				OnUpdater();
				Bukkit.getWorld("world").setStorm(false);
				Bukkit.getWorld("world").setThundering(false);
			}
		}.runTaskLater(getInstance(), 2);
	}

	@Override
	public void onDisable() {
	}

	public static RisenWorld_Lobby getInstance() {
		return instance;
	}

	int timer = 10;
	public void updateTablist(){
		timer++;
		if(timer%20==0){
			pushUString();
		}

		String top = "";
		String bot = "";

		top += "" + ucat + "";
		top += "\n";
		top += "§7";
		bot += "";
		String s = computeCurrent();
		bot += "§4■ §7" + s + " §4■";
		//bot += "§4> §7" + s + " §4<";
		for(Player p : getServer().getOnlinePlayers()){
			tablistUpdate(p, top, bot);
		}
	}

	public int stay = 150;
	public String current = "";
	public String from = "";
	public String to = "§9§lSYSTEM §8§l● §c§lLoading... Please wait a sec...";
	public boolean swap = false;
	public int swapc = 0;

	public String computeCurrent(){
		stay--;
		if(stay == 0){
			stay = 450;
			//stay = 200;
			from = to;
			to = getTab();
			swap = true;
		}
		if(swap){
			swapc++;
			if(swapc == to.length()+from.length()){
				swap = false;
				swapc = 0;
			}
			if(swapc < from.length()){
				return sub(from, swapc, 1);
			}else{
				return sub(to, swapc-from.length(), 0);
			}

		}
		return to;
	}

	public String sub(String s, int offset, int w){

		if(w == 0){
			try{
				if(offset >= s.length()) return s;
				return s.substring(0, offset);
			}catch(java.lang.Exception ex){
				return "";
			}
		}

		if(w == 1){
			try{
				return s.substring(offset, s.length());
			}catch(java.lang.Exception ex){
				return "";
			}
		}


		return "";
	}

	int tabpos = 0;
	String[] tab = new String[]{"..."};
	public String getTab(){
		tabpos++;
		if(tabpos == tab.length){
			tabpos = 0;
		}
		return tab[tabpos];
	}
	public int all = 3;
	public int repeat = 4;
	public void tabInitText(){
		
		/*
		 * {"Willkommen, §e%player%",
			"§2Lobby: §aUpdate in der gesammten Lobby",
			"§2Neue Games: §aTNT RUN §8- §aUHC §8- §aJumpLeague",
			"§2Tageslogin: §aErhalte eine Belohnung! Rechtsklick auf die Melone!"};
		 */
		tab = new String[5];
		tab[0] = "§bWillkommen, §e%player%";
		tab[1] = "INFO §8- §bLobby: §aUpdate in der gesammten Lobby";
		tab[2] = "INFO §8- §bNeue Games: §aTNT RUN §8- §aUHC §8- §aJumpLeague";
		tab[3] = "INFO §8- §bTageslogin: §aErhalte eine Belohnung! Rechtsklick auf die Melone!";
		tab[4] = "TIP §8- §bEs gibt Belohnungen bei Versteckten Messages.";
		for(int i = 0; i<tab.length; i++){
			all+=tab[i].length();
		}
	}

	public String ucat = "";
	int dm = 0;
	public void pushUString(){
		dm++;
		switch(dm){
		case 1: ucat = "§b§k|||||§6 Deminosa.de §b§k|||||\n\n";return;
		case 2: ucat = "§6§k|||||§b Deminosa.de §6§k|||||\n\n"; dm = 0;
		}
	}


	public void OnUpdater(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			public void run(){
				updateTablist();
			}

		}, 1, 1);
	}

	public static void tablistUpdate(Player bukkitplayer, String oben, String unten) {
		for(Player all : Bukkit.getOnlinePlayers()) {
			CorePlayer player = CoreCache.getCorePlayer(all);
			player.sendActionbar(unten.replace("%player%", player.getName()));
		}
	}
	
}
