package de.deminosa.lobby.main.timers;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.deminosa.coinmanager.Coins;
import de.deminosa.coinmanager.command.CoinsCommand.CoinAction;
import de.deminosa.core.builders.CoreTimer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.hologramm.Hologram;
import de.deminosa.core.utils.warps.WarpManager;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.shop.Items.special.ToyCoinTNT;
import de.deminosa.lobby.utils.Particel;
import net.minecraft.server.v1_8_R3.EnumParticle;

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
	public static ArrayList<Location> holoLocs = new ArrayList<>();
	public static ArrayList<Particel> Particels = new ArrayList<>();
	
	public static void createHolo() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if(!isCreate) {
					holo.create();
					isCreate = true;
					
					Location loc = WarpManager.getWarpLocation("spawn");
					for(int x = -100; x < 100; x++) {
						loc = WarpManager.getWarpLocation("spawn");
						for(int y = 0; y < 10; y++) {
							for(int z = -100; z < 100; z++) {
								loc.add(x,y,z);
								if(loc.getBlock().getType() == Material.ENCHANTMENT_TABLE) {
									if(Hologram.getByLocation(loc) == null) {
										Hologram hol = new Hologram(loc.clone().subtract(0,0.5,0), "§b§k|||§6 Kisten Spiel §b§k|||");
										hol.create();
										holoLocs.add(hol.getLocation());
										Particel pat = new Particel(RisenWorld_Lobby.getInstance(), EnumParticle.FIREWORKS_SPARK, hol.getLocation(), true, 0, 2, 0, 0, 1);
										Particels.add(pat);
									}
								}else {
//									Bukkit.broadcastMessage("§cBlock: " + loc.getBlock().getType().name());
									System.out.print("Check: " + loc.getX() + " " + loc.getY() + " " + loc.getZ());
								}
								loc.subtract(x,y,z);
							}
						}
					}
				}
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 20*3);
	}
	
	@Override
	public void tick() {
		Bukkit.getWorld("world").setTime(0);
		
		t++;
		
		for(Particel pat : Particels) {
			pat.drawRandom(3);
		}
		
		if(t == 20*8) {
			try {
				t = 0;
				int r = ThreadLocalRandom.current().nextInt(texte.length);
				
				holo.changeText("§b§k|||§6 " + texte[r] + " §b§k|||§6");
			} catch (Exception e) {
			}
			
			for(Player player : Bukkit.getOnlinePlayers()) {
				if(ToyCoinTNT.coins.containsKey(player)) {
					int coin = ToyCoinTNT.coins.get(player);
					
					CoreCache.getCorePlayer(player).sendMessage("Coins", "Du hast §b+"+coin+" coins §7gesammelt!");
					
					new BukkitRunnable() {
						@Override
						public void run() {
							Coins.action(CoinAction.ADD, player, coin);
						}
					}.runTaskLater(RisenWorld_Lobby.getInstance(), 2*(ThreadLocalRandom.current().nextInt(5))+1);
					
					ToyCoinTNT.coins.remove(player);
				}
				
				if(ToyCoinTNT.chest.containsKey(player)) {
					int chest = ToyCoinTNT.chest.get(player);
					
					CoreCache.getCorePlayer(player).sendMessage("Chest", "Du hast §b+"+chest+" Kisten §7gesammelt!");
					
					new BukkitRunnable() {
						@Override
						public void run() {
							Coins.chestAction(CoinAction.ADD, player, chest);
						}
					}.runTaskLater(RisenWorld_Lobby.getInstance(), 2*(ThreadLocalRandom.current().nextInt(5))+1);
					
					ToyCoinTNT.chest.remove(player);
				}
			}
		}
	}
	
	private String[] texte = {
			"Tagesbonus",
			"Jeden Tag 2x Chest",
			"Ich warte auf dich",
			"Klick mich, Ich bin Bio",
			"Ökologisch & Ökonomisch Abbaubar",
			"Jage den 7 Tagen nach",
			"Ich versüße dein Tag",
			"6 & 7 Tag sind Lotto Tage",
			"Der Frühe Vogel Klaut dein Tagesbonus",
			"Ich stecke Geld in deinen Skin ;)"
	};

}
