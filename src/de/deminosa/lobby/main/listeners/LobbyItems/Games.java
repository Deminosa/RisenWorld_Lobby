package de.deminosa.lobby.main.listeners.LobbyItems;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import de.deminosa.coinmanager.Coins;
import de.deminosa.coinmanager.command.CoinsCommand.CoinAction;
import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.gui.GUI;
import de.deminosa.core.utils.gui.GUIButton;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.core.utils.mysql.MySQL;
import de.deminosa.core.utils.warps.WarpManager;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.daylogin.DayLoginHandler;
import de.deminosa.lobby.utils.Utils;
import jump.Jump;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	17:49:17 # 25.09.2019
*
*/

public class Games implements Listener{

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getItem() != null) {
			CorePlayer player = CoreCache.getCorePlayer(event.getPlayer());
			if(event.getItem().equals(Utils.getREWARD())) {
				if(DayLoginHandler.isRewardAvabile(player)) {
					DayLoginHandler.updateReward(player);
					player.sendMessage("Tagesbonus", "Du hast §a+50 Coins §7erhalten!");
					Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 50);
					
					String getStreak = MySQL.getString("DayLogin", "UUID", player.getUUIDAsString(), "Streak");
					player.sendMessage("Tagesbonus", "Streak: " + getStreak);
				}else {
					player.sendMessage("Tagesbonus", "§cDu hast bereits deine Belohnung erhalten!");
				}
			}
			if(event.getItem().equals(Utils.getJUMP())) {
				if(!Jump.sucBlocks.containsKey(event.getPlayer())) {
					Jump.start(event.getPlayer());
				}else {
					player.sendMessage("§bEmmy", "§cJ'n'R Konnte nicht gestartet werden!");
				}
			}
			if(event.getItem().equals(Utils.getGAMES())) {
				GUI gui = new GUI(player, "§6Games", 54-9);
				
				gui.setButton(0, new GUIButton() {
					String warp = "SkyPvP";
					@Override
					public void onClick(InventoryClickEvent event) {
						Player bukkitPlayer = (Player)event.getWhoClicked();
						teleport(CoreCache.getCorePlayer(bukkitPlayer), warp);
					}
					
					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.IRON_SWORD).setName("§6"+warp+"+").build();
					}
				});
				
				gui.setButton(8, new GUIButton() {
					String warp = "SkyBlock";
					@Override
					public void onClick(InventoryClickEvent event) {
						if(!warp.equalsIgnoreCase("")) {
							Player bukkitPlayer = (Player)event.getWhoClicked();
							teleport(CoreCache.getCorePlayer(bukkitPlayer), warp);
						}
					}
					
					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.GRASS).setName("§6"+warp+"+")
								.addLoreLine("§7Derzeitig nur für das Team")
								.addLoreLine("")
								.addLoreLine("§940% Fertig")
								.build();
					}
				});
				
				gui.setButton(11, new GUIButton() {
					String warp = "";
					@Override
					public void onClick(InventoryClickEvent event) {
						if(!warp.equalsIgnoreCase("")) {
							Player bukkitPlayer = (Player)event.getWhoClicked();
							teleport(CoreCache.getCorePlayer(bukkitPlayer), warp);
						}
					}
					
					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.BARRIER).setName("§6"+warp).build();
					}
				});
				
				gui.setButton(15, new GUIButton() {
					String warp = "";
					@Override
					public void onClick(InventoryClickEvent event) {
						if(!warp.equalsIgnoreCase("")) {
							Player bukkitPlayer = (Player)event.getWhoClicked();
							teleport(CoreCache.getCorePlayer(bukkitPlayer), warp);
						}
					}
					
					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.BARRIER).setName("§6"+warp).build();
					}
				});
				
				gui.setButton(18, new GUIButton() {
					@Override
					public void onClick(InventoryClickEvent event) {
						String warp = "KnockFFA";
						if(!warp.equalsIgnoreCase("")) {
							Player bukkitPlayer = (Player)event.getWhoClicked();
							teleport(CoreCache.getCorePlayer(bukkitPlayer), warp);
						}
					}
					
					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.STICK).addEnchant(Enchantment.KNOCKBACK, 1).setName("§6KnockFFA").build();
					}
				});
				
				gui.setButton(22, new GUIButton() {
					@Override
					public void onClick(InventoryClickEvent event) {
						String warp = "spawn";
						if(!warp.equalsIgnoreCase("")) {
							Player bukkitPlayer = (Player)event.getWhoClicked();
							teleport(CoreCache.getCorePlayer(bukkitPlayer), warp);
						}
					}
					
					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.EYE_OF_ENDER).setName("§6Spawn").build();
					}
				});
				
				gui.setButton(26, new GUIButton() {
					String warp = "TNT-Run";
					@Override
					public void onClick(InventoryClickEvent event) {
						if(!warp.equalsIgnoreCase("")) {
							Player bukkitPlayer = (Player)event.getWhoClicked();
							teleport(CoreCache.getCorePlayer(bukkitPlayer), warp);
						}
					}
					
					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.TNT).setName("§6"+warp)
								.addLoreLine("§eOpen Beta Programm")
								.build();
					}
				});
				
				gui.setButton(29, new GUIButton() {
					String warp = "";
					@Override
					public void onClick(InventoryClickEvent event) {
						if(!warp.equalsIgnoreCase("")) {
							Player bukkitPlayer = (Player)event.getWhoClicked();
							teleport(CoreCache.getCorePlayer(bukkitPlayer), warp);
						}
					}
					
					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.BARRIER).setName("§6"+warp).build();
					}
				});
				
				gui.setButton(33, new GUIButton() {
					String warp = "";
					@Override
					public void onClick(InventoryClickEvent event) {
						if(!warp.equalsIgnoreCase("")) {
							Player bukkitPlayer = (Player)event.getWhoClicked();
							teleport(CoreCache.getCorePlayer(bukkitPlayer), warp);
						}
					}
					
					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.BARRIER).setName("§6"+warp).build();
					}
				});
				
				gui.setButton(36, new GUIButton() {
					String warp = "JL";
					@Override
					public void onClick(InventoryClickEvent event) {
						if(!warp.equalsIgnoreCase("")) {
							Player bukkitPlayer = (Player)event.getWhoClicked();
//							teleport(CoreCache.getCorePlayer(bukkitPlayer), warp);
						}
					}
					
					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.FIREWORK).setName("§6JumpLegue").build();
					}
				});
				
				gui.setButton(44, new GUIButton() {
					String warp = "UHC";
					@Override
					public void onClick(InventoryClickEvent event) {
						if(!warp.equalsIgnoreCase("")) {
							Player bukkitPlayer = (Player)event.getWhoClicked();
//							teleport(CoreCache.getCorePlayer(bukkitPlayer), warp);
						}
					}
					
					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.NETHER_STAR).setName("§6"+warp).build();
					}
				});
				
				gui.open();
			}
		}
	}
	
	private static void teleport(CorePlayer player, String warp) {
		player.getBukkitPlayer().closeInventory();
		Vector v = player.getBukkitPlayer().getVelocity().multiply(2).setY(5);
		player.getBukkitPlayer().setVelocity(v);
		player.addPotionEffect(PotionEffectType.BLINDNESS, 10);
		player.playsound(Sound.PISTON_EXTEND);
		new BukkitRunnable() {
			@Override
			public void run() {
				player.teleport(WarpManager.getWarpLocation(warp));
				player.removePotionEffect(PotionEffectType.BLINDNESS);
				player.sendTitle(0, 20*2, 5, "§b➤ §6Games", "§7"+warp);
				player.playsound(Sound.ENDERMAN_TELEPORT);
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 20);
	}
}
