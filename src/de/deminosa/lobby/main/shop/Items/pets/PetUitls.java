package de.deminosa.lobby.main.shop.Items.pets;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import de.deminosa.coinmanager.Coins;
import de.deminosa.coinmanager.command.CoinsCommand.CoinAction;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.gui.GUI;
import de.deminosa.core.utils.gui.GUIButton;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.core.utils.mathmanager.CoreMath;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.utils.Particel;
import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EnumParticle;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	12:15:15 # 16.03.2020
 *
 */

public class PetUitls implements Listener{
	
	private static HashMap<Player, Integer> runningIDs = new HashMap<>();
	private static HashMap<Player, LivingEntity> pets = new HashMap<>();
	private static ArrayList<String> petCaress = new ArrayList<>();
	
	private static HashMap<Player, Integer> wait = new HashMap<>();

	@EventHandler
	public void onInteract(PlayerInteractAtEntityEvent event) {
		if(event.getRightClicked().getType() == EntityType.VILLAGER) return;
		
		if(!event.getPlayer().isSneaking()) {
			try {
				Entity entity = pets.get(event.getPlayer());
				
				String UUID = event.getRightClicked().getUniqueId().toString();
				if(entity.getUniqueId().toString().equals(UUID)) {
//					CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Haustier", "Du hast dein Haustier gestreichelt!");
					Particel pat = new Particel(RisenWorld_Lobby.getInstance(), EnumParticle.HEART, entity.getLocation().add(0,1,0), true, 0, 0, 0, 0, 1);
					pat.draw();
					if(petCaress.contains(UUID)) {
						petCaress.remove(UUID);
						if(CoreMath.chance(5)) {
							int r =ThreadLocalRandom.current().nextInt(3);
							if(r == 0) {
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.LEVEL_UP);
								CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Haustier", "Dein Haustier hat §6" + 5 + " Coin(s) §7gefunden");
								Coins.action(CoinAction.ADD, event.getPlayer(), 5);
							}else if(r == 1){
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.LEVEL_UP);
								CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Haustier", "Dein Haustier hat §6" + 1 + " Token(s) §7gefunden");
								Coins.tokenAction(CoinAction.ADD, event.getPlayer(), 1);
							}else {
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.LEVEL_UP);
								CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Haustier", "Dein Haustier hat §6" + 1 + " Kiste §7gefunden");
								Coins.chestAction(CoinAction.ADD, event.getPlayer(), 1);
							}
						}else {
							CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Haustier", "Dein Haustier ist wieder zufrieden!");
						}
					}
				}
			}catch (Exception e) {
				
			}
		}else {
			GUI gui = new GUI(CoreCache.getCorePlayer(event.getPlayer()), "§bHaustier §8- §aMenü", InventoryType.HOPPER);
			
			gui.setButton(2, new GUIButton() {
				@Override
				public void onClick(InventoryClickEvent arg0) {
					if(!wait.containsKey(event.getPlayer())) {
						event.getPlayer().closeInventory();
						wait.put(event.getPlayer(), (int) ((System.currentTimeMillis()/1000)+120));
						
						new BukkitRunnable() {
							@Override
							public void run() {
								if(CoreMath.chance(10)) {
									int r =ThreadLocalRandom.current().nextInt(3);
									if(r == 0) {
										CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.LEVEL_UP);
										CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Haustier", "Dein Haustier hat §6" + 5 + " Coin(s) §7gefunden");
										Coins.action(CoinAction.ADD, event.getPlayer(), 5);
									}else if(r == 1){
										CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.LEVEL_UP);
										CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Haustier", "Dein Haustier hat §6" + 1 + " Token(s) §7gefunden");
										Coins.tokenAction(CoinAction.ADD, event.getPlayer(), 1);
									}else {
										CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.LEVEL_UP);
										CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Haustier", "Dein Haustier hat §6" + 1 + " Kiste §7gefunden");
										Coins.chestAction(CoinAction.ADD, event.getPlayer(), 1);
									}
								}else {
									getCorePlayer().sendMessage("Haustier", "§cDein Haustier hat nichts gefunden!");
									
								}
							}
						}.runTaskLater(RisenWorld_Lobby.getInstance(), 20*(ThreadLocalRandom.current().nextInt(3)+1));
					}else {
						int sec = (int) (System.currentTimeMillis()/1000);
						int currentType = wait.get(event.getPlayer()) - sec;
						
						if(currentType <= 0) {
							wait.remove(event.getPlayer());
							getCorePlayer().sendMessage("Haustier", "§aAction wurde freigeschaltet!");
						}else {
							getCorePlayer().sendMessage("Haustier", "§cBitte warte noch §b" + (currentType >= 1 ? "2 min" : currentType+"s"));
							event.getPlayer().closeInventory();
						}
					}
				}
				
				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.COMPASS)
							.setName("§6Suche")
							.addLoreLine("§7Lass dein Haustier")
							.addLoreLine("§7manuell suchen.")
							.build();
				}
			});
			
			gui.open();
		}
	}
	
	public static void followPlayer(Player player, LivingEntity entity, double d, String type, double chance, int maxvalue) {
		final LivingEntity e = entity;
		final Player p = player;
		final float f = (float) d;
		final String t = type;
		final double c = chance;

		int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(RisenWorld_Lobby.getInstance(), new Runnable() {
			@Override
			public void run() {
				((EntityInsentient) ((CraftEntity) e).getHandle()).getNavigation().a(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), f);
				//Bukkit.broadcastMessage(""+e.getLocation().distance(p.getLocation()));
				if(e.getLocation().distance(p.getLocation()) > 17) {
					e.teleport(p);
					CoreCache.getCorePlayer(p).playsound(Sound.ENDERMAN_TELEPORT);
				}
				if(CoreMath.chance(c)) {
					int v = ThreadLocalRandom.current().nextInt(maxvalue)+1;
					if(t == "c") {
						if(CoreMath.chance(15)) {
							CoreCache.getCorePlayer(p).playsound(Sound.LEVEL_UP);
							CoreCache.getCorePlayer(p).sendMessage("Haustier", "Dein Haustier hat §6" + v + " Coin(s) §7gefunden");
							Coins.action(CoinAction.ADD, player, v);
						}else if(CoreMath.chance(25)){
							CoreCache.getCorePlayer(p).playsound(Sound.FIZZ);
							CoreCache.getCorePlayer(p).sendMessage("Haustier", "Dein Haustier möchte Aufmerksamkeit!");
							if(!petCaress.contains(e.getUniqueId().toString())) {
								petCaress.add(e.getUniqueId().toString());
							}
						}else if(CoreMath.chance(25)){
							CoreCache.getCorePlayer(p).sendMessage("Haustier", "Dein Haustier hat nichts gefunden!");
						}
					}else if(t == "k") {
						if(CoreMath.chance(15)) {
							CoreCache.getCorePlayer(p).playsound(Sound.LEVEL_UP);
							CoreCache.getCorePlayer(p).sendMessage("Haustier", "Dein Haustier hat §6" + v + " Lottoschein(e) §7gefunden");
							Coins.tokenAction(CoinAction.ADD, player, v);
						}else if(CoreMath.chance(25)){
							CoreCache.getCorePlayer(p).sendMessage("Haustier", "Dein Haustier möchte Aufmerksamkeit!");
							if(!petCaress.contains(e.getUniqueId().toString())) {
								petCaress.add(e.getUniqueId().toString());
							}
						}else if(CoreMath.chance(25)){
							CoreCache.getCorePlayer(p).sendMessage("Haustier", "Dein Haustier hast nichts gefunden!");
						}
					}else if(t == "a") {
						if(CoreMath.chance(15)) {
							CoreCache.getCorePlayer(p).playsound(Sound.LEVEL_UP);
							CoreCache.getCorePlayer(p).sendMessage("Haustier", "Dein Haustier hat §6" + v + " Kiste §7gefunden");
							Coins.chestAction(CoinAction.ADD, player, v);
						}else if(CoreMath.chance(25)){
							CoreCache.getCorePlayer(p).sendMessage("Haustier", "Dein Haustier möchte Aufmerksamkeit!");
							if(!petCaress.contains(e.getUniqueId().toString())) {
								petCaress.add(e.getUniqueId().toString());
							}
						}else if(CoreMath.chance(25)){
							CoreCache.getCorePlayer(p).sendMessage("Haustier", "Dein Haustier hat nichts gefunden!");
						}
					}
				}
			}
		}, 20, 20);

		runningIDs.put(player, id);
		pets.put(player, entity);
	}

	public static void stopFollow(Player player) {
		if(runningIDs.containsKey(player)) {
			Bukkit.getScheduler().cancelTask(runningIDs.get(player));
			runningIDs.remove(player);
			pets.get(player).remove();
			pets.remove(player);
		}
	}

}
