package de.deminosa.lobby.main.listeners.LobbyItems;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.gui.GUI;
import de.deminosa.core.utils.gui.GUIButton;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.core.utils.mysql.MySQL;
import de.deminosa.core.utils.warps.WarpManager;
import de.deminosa.lobby.RisenWorld_Lobby;
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
			if(event.getItem().equals(Utils.getJUMP())) {
				if(!Jump.sucBlocks.containsKey(event.getPlayer())) {
					Jump.start(event.getPlayer());
					int sqlBlock = MySQL.getInt("jump", "reach", "UUID", event.getPlayer().getUniqueId().toString());
					event.getPlayer().sendMessage("§9Jump §7Rekord: §6" + sqlBlock + " sprünge!");
				}else {
					player.sendMessage("§bEmmy", "§cJ'n'R Konnte nicht gestartet werden!");
				}
			}

			if(event.getItem().equals(Utils.getGAMES())) {
				GUI gui = new GUI(player, "§6Games", 9);

				gui.setButton(0, new GUIButton() {
					String warp = "SkyPvP";
					@Override
					public void onClick(InventoryClickEvent event) {
						Player bukkitPlayer = (Player)event.getWhoClicked();
						Utils.connectTo(bukkitPlayer, "SkyPvP-1");
					}

					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.IRON_SWORD).setName("§6"+warp+"+")
								.addLoreLine("§7Direkte Verbindung zum Server.")
								.build();
					}
				});

				gui.setButton(1, new GUIButton() {
					String warp = "SkyBlock";
					@Override
					public void onClick(InventoryClickEvent event) {
						if(!warp.equalsIgnoreCase("")) {
							Player bukkitPlayer = (Player)event.getWhoClicked();
							Utils.connectTo(bukkitPlayer, "SkyBlock-1");
						}
					}

					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.GRASS).setName("§6"+warp+"+")
								.addLoreLine("§7Direkte Verbindung zum Server.")
								.addLoreLine("")
								.addLoreLine("§cDerzeitig nur für das Team")
								.addLoreLine("")
								.addLoreLine("§942% Fertig")
								.build();
					}
				});

				gui.setButton(3, new GUIButton() {
					@Override
					public void onClick(InventoryClickEvent event) {
						if(event.getAction() != InventoryAction.MOVE_TO_OTHER_INVENTORY) {
							String warp = "KnockFFA";
							if(!warp.equalsIgnoreCase("")) {
								Player bukkitPlayer = (Player)event.getWhoClicked();
								teleport(CoreCache.getCorePlayer(bukkitPlayer), warp);
							}
						}else {
							Utils.connectTo(player.getBukkitPlayer(), "KnockFFA-1");
						}
					}

					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.STICK).addEnchant(Enchantment.KNOCKBACK, 1)
								.setName("§6KnockFFA")
								.addLoreLine("")
								.addLoreLine("§bShift + Rechtsklick:")
								.addLoreLine("§7Direkte Verbindung zum Server.")
								.build();
					}
				});

				gui.setButton(8, new GUIButton() {
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

				gui.setButton(4, new GUIButton() {
					String warp = "TNT-Run";
					@Override
					public void onClick(InventoryClickEvent event) {
						if(event.getAction() != InventoryAction.MOVE_TO_OTHER_INVENTORY) {
							if(!warp.equalsIgnoreCase("")) {
								Player bukkitPlayer = (Player)event.getWhoClicked();
								teleport(CoreCache.getCorePlayer(bukkitPlayer), warp);
							}
						}else {
							int i = ThreadLocalRandom.current().nextInt(5)+1;
							Utils.connectTo(player.getBukkitPlayer(), "TNT-Run-"+i);
						}
					}

					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.TNT).setName("§6"+warp)
								.addLoreLine("§eOpen Beta Programm")
								.addLoreLine(" ")
								.addLoreLine("§bShift + Rechtsklick:")
								.addLoreLine("§7Direkte Verbindung zum Server.")
								.build();
					}
				});

				gui.setButton(6, new GUIButton() {
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

				gui.setButton(5, new GUIButton() {
					String warp = "UHC";
					@Override
					public void onClick(InventoryClickEvent event) {
						if(event.getAction() != InventoryAction.MOVE_TO_OTHER_INVENTORY) {
							if(!warp.equalsIgnoreCase("")) {
								Player bukkitPlayer = (Player)event.getWhoClicked();
								teleport(CoreCache.getCorePlayer(bukkitPlayer), warp);
							}
						}else {
							int i = ThreadLocalRandom.current().nextInt(3)+1;
							Utils.connectTo(player.getBukkitPlayer(), "UHC-"+i);
						}
					}

					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.NETHER_STAR).setName("§6"+warp)
								.addLoreLine(" ")
								.addLoreLine("§bShift + Rechtsklick:")
								.addLoreLine("§7Direkte Verbindung zum Server.")
								.build();
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
