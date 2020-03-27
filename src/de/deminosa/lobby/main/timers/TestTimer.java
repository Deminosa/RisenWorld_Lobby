package de.deminosa.lobby.main.timers;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import de.deminosa.core.builders.CoreTimer;
import de.deminosa.core.utils.hologramm.Hologram;
import de.deminosa.lobby.RisenWorld_Lobby;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	18:20:46 # 15.03.2020
 *
 */

public class TestTimer implements CoreTimer{

	int t = 0;
	private static boolean isCreate = false;
	private static Hologram holo = new Hologram(new Location(Bukkit.getWorld("world"), 57.5, 81, -29.5), "§b§k|||§6 Tagesbonus §b§k|||");
	
	public static void createHolo() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if(!isCreate) {
					holo.create();
					isCreate = true;
				}
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 20*3);
	}
	
	@Override
	public void tick() {
		Bukkit.getWorld("world").setTime(0);
		
		t++;
		
		if(t == 20*8) {
			t = 0;
			int r = ThreadLocalRandom.current().nextInt(4);
			
			switch(r) {
			case 0:
				holo.changeText("§b§k|||§6 Tagesbonus §b§k|||");
				break;
			case 1:
				holo.changeText("§b§k|||§6 Jeden Tag 2x Chest §b§k|||");
				break;
			case 2:
				holo.changeText("§b§k|||§6 Ich warte auf dich §b§k|||");
				break;
			case 3:
				holo.changeText("§b§k|||§6 Klick mich, Ich bin Bio §b§k|||");
				break;
			}
		}
	}

}
