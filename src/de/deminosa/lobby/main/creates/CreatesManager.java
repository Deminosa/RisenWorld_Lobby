package de.deminosa.lobby.main.creates;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import de.deminosa.coinmanager.Coins;
import de.deminosa.coinmanager.command.CoinsCommand.CoinAction;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.gui.GUI;
import de.deminosa.core.utils.gui.GUIButton;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.lobby.main.creates.builder.Creates;
import de.deminosa.lobby.regedit.Toroku;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	20:40:26 # 23.03.2020
 *
 */

public class CreatesManager implements Listener{

	public static HashMap<Player, Location> creats = new HashMap<>();
	
	@EventHandler
	public void quit(PlayerQuitEvent event) {
		if(creats.containsKey(event.getPlayer())) {
			creats.get(event.getPlayer()).getBlock().setType(Material.ENCHANTMENT_TABLE);
			new Creates(creats.get(event.getPlayer()));
			creats.remove(event.getPlayer());
		}
	}

	@EventHandler
	public void move(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if(creats.containsKey(player)) {
			if(player.getLocation().distance(creats.get(player)) > 1 &&
					creats.get(player).distance(player.getLocation()) > 0) {
				player.teleport(creats.get(player).clone().add(0.5,0,0.5));
			}
		}else {
			for(Player players : creats.keySet()) {
				if(player.getLocation().distance(creats.get(players)) <= 2.5) {
					Vector plV = player.getLocation().toVector();
					Vector spV = creats.get(players).toVector();
					Vector v = spV.clone().add(plV).multiply(1.0 / spV.distance(plV));
					player.setVelocity(v);
					player.playSound(player.getLocation(), Sound.ZOMBIE_WOODBREAK, 1, 1);
				}
			}
		}
	}

	@EventHandler
	public void interact(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();

		if(block != null && block.getType() == Material.ENCHANTMENT_TABLE) {
			GUI gui = new GUI(CoreCache.getCorePlayer(event.getPlayer()), "§6Kisten spiel", InventoryType.HOPPER);

			gui.setButton(1, new GUIButton() {
				@Override
				public void onClick(InventoryClickEvent e) {
					if(e.getAction() != InventoryAction.MOVE_TO_OTHER_INVENTORY) {
						GUI chest = new GUI(getCorePlayer(), "§6Kiste Wählen", InventoryType.HOPPER);
						
						chest.setButton(0, new GUIButton() {
							@Override
							public void onClick(InventoryClickEvent arg0) {
								if(Coins.hasEnoughChest(event.getPlayer(), 1)) {
									if(!creats.containsValue(block.getLocation())) {
										Coins.chestAction(CoinAction.REMOVE, event.getPlayer(), 1);
										Creates c = new Creates(getCorePlayer(), block);
										
										c.setJeckpot(0.3);
										c.setSpezial(0.7);
										c.setArmor(4);
										c.setEffecte(4);
										c.setPet(4);
										c.setToy(4);
										c.setToken(5);
										c.setCoins(50);
										c.setMagic(4);
										c.setHeads(4);
										
										Toroku.addEvent(c);
									}
								}
							}
							
							@Override
							public ItemStack getIcon() {
								// > 50 §a
								// > 10 §e
								// < 10 §c 
								return new ItemBuilder(Material.CHEST)
										.setName("§6Standard Kiste")
										.addLoreLine("§7Du hast §b" + Coins.getChest(getCorePlayer().getBukkitPlayer()) + "/1 §7Kisten")
										.addLoreLine("")
										.addLoreLine("§c500 Coins §b0.3%")
										.addLoreLine("§aCoins §b50%")
										.addLoreLine("§cToken §b5%")
										.addLoreLine("§cAusergewöhnlich §b0.7%")
										.addLoreLine("§cRüstung §b4%")
										.addLoreLine("§cEffekte §b4%")
										.addLoreLine("§cHaustiere §b4%")
										.addLoreLine("§cSpielzeuge §b4%")
										.addLoreLine("§cVerwandlungszauber §b4%")
										.addLoreLine("§aNieten §b28%")
										.addLoreLine("")
										.build();
							}
						});
						
						chest.setButton(1, new GUIButton() {
							@Override
							public void onClick(InventoryClickEvent arg0) {
								if(Coins.hasEnoughChest(event.getPlayer(), 5)) {
									if(!creats.containsValue(block.getLocation())) {
										Coins.chestAction(CoinAction.REMOVE, event.getPlayer(), 5);
										Creates c = new Creates(getCorePlayer(), block);
										
										c.setJeckpot(1);
										c.setSpezial(10);
										c.setArmor(10);
										c.setEffecte(10);
										c.setPet(10);
										c.setToy(10);
										c.setToken(10);
										c.setCoins(25);
										c.setMagic(10);
										c.setHeads(10);
										
										Toroku.addEvent(c);
									}
								}
							}
							
							@Override
							public ItemStack getIcon() {
								// > 50 §a
								// > 10 §e
								// < 10 §c 
								return new ItemBuilder(Material.CHEST)
										.setName("§6Hans im Glück")
										.addLoreLine("§7Du hast §b" + Coins.getChest(getCorePlayer().getBukkitPlayer()) + "/5 §7Kisten")
										.addLoreLine("")
										.addLoreLine("§c500 Coins §b1%")
										.addLoreLine("§aCoins §b25%")
										.addLoreLine("§eToken §b10%")
										.addLoreLine("§eAusergewöhnlich §b10%")
										.addLoreLine("§eRüstung §b10%")
										.addLoreLine("§eEffekte §b10%")
										.addLoreLine("§eHaustiere §b10%")
										.addLoreLine("§eSpielzeuge §b10%")
										.addLoreLine("§eVerwandlungszauber §b10%")
										.addLoreLine("§cNieten §b4%")
										.addLoreLine("")
										.build();
							}
						});
						
						chest.setButton(3, new GUIButton() {
							@Override
							public void onClick(InventoryClickEvent arg0) {
								if(Coins.hasEnoughToke(event.getPlayer(), 10)) {
									if(!creats.containsValue(block.getLocation())) {
										Coins.tokenAction(CoinAction.REMOVE, event.getPlayer(), 10);
										Creates c = new Creates(getCorePlayer(), block);
										
										c.setJeckpot(0.1);
										c.setSpezial(15);
										c.setArmor(15);
										c.setEffecte(15);
										c.setPet(15);
										c.setToy(15);
										c.setToken(0.9);
										c.setCoins(8);
										c.setMagic(15);
										c.setHeads(15);
										
										Toroku.addEvent(c);
									}
								}
							}
							
							@Override
							public ItemStack getIcon() {
								// > 50 §a
								// > 10 §e
								// < 10 §c 
								return new ItemBuilder(Material.ENDER_CHEST)
										.setName("§6Der Shop gehört mir!")
										.addLoreLine("§7Du hast §b" + Coins.getToken(getCorePlayer().getBukkitPlayer()) + "/10 §7Tokens")
										.addLoreLine("")
										.addLoreLine("§c500 Coins §b0.1%")
										.addLoreLine("§cCoins §b8%")
										.addLoreLine("§cToken §b0.9%")
										.addLoreLine("§aAusergewöhnlich §b15%")
										.addLoreLine("§aRüstung §b15%")
										.addLoreLine("§aEffekte §b15%")
										.addLoreLine("§aHaustiere §b15%")
										.addLoreLine("§aSpielzeuge §b15%")
										.addLoreLine("§aVerwandlungszauber §b15%")
										.addLoreLine("§cNieten §b1%")
										.addLoreLine("")
										.build();
							}
						});
						
						chest.setButton(4, new GUIButton() {
							@Override
							public void onClick(InventoryClickEvent arg0) {
								if(Coins.hasEnoughToke(event.getPlayer(), 15)) {
									if(!creats.containsValue(block.getLocation())) {
										Coins.tokenAction(CoinAction.REMOVE, event.getPlayer(), 15);
										Creates c = new Creates(getCorePlayer(), block);
										
										c.setJeckpot(15);
										c.setSpezial(2);
										c.setArmor(2);
										c.setEffecte(2);
										c.setPet(2);
										c.setToy(2);
										c.setToken(3);
										c.setCoins(60);
										c.setMagic(2);
										c.setHeads(2);
										
										Toroku.addEvent(c);
									}
								}
							}
							
							@Override
							public ItemStack getIcon() {
								// > 50 §a
								// > 10 §e
								// < 10 §c 
								return new ItemBuilder(Material.ENDER_CHEST)
										.setName("§6Der Zocker")
										.addLoreLine("§7Du hast §b" + Coins.getToken(getCorePlayer().getBukkitPlayer()) + "/15 §7Tokens")
										.addLoreLine("")
										.addLoreLine("§a500 Coins §b15%")
										.addLoreLine("§aCoins §b50%")
										.addLoreLine("§cToken §b4%")
										.addLoreLine("§cAusergewöhnlich §b4%")
										.addLoreLine("§cRüstung §b4%")
										.addLoreLine("§cEffekte §b4%")
										.addLoreLine("§cHaustiere §b4%")
										.addLoreLine("§cSpielzeuge §b4%")
										.addLoreLine("§cVerwandlungszauber §b4%")
										.addLoreLine("§cNieten §b7%")
										.addLoreLine("")
										.build();
							}
						});
						
						chest.open();
					}else {
						GUI gui = new GUI(getCorePlayer(), "§6ChangeLog");

						int i = 0;
						gui.getInventory().setItem(i, new ItemBuilder(Material.BOOK_AND_QUILL)
								.setName("§6Update am: 20.04.2020")
								.addLoreLine("§a- §7Lottoscheine")
								.addLoreLine("§a+ §7Token")
								.addLoreLine("§6! §7Änderungen am Chance")
								.addLoreLine("§a+ §7Neue Kisten")
								.addLoreLine("")
								.addLoreLine("§8§m----------------")
								.addLoreLine("§a+ §7Hinzugefügt")
								.addLoreLine("§c- §7Entfernt")
								.addLoreLine("§6! §7Veränderung")
								.build());

						i++;
						
						gui.getInventory().setItem(i, new ItemBuilder(Material.BOOK)
								.setName("§6Update am: 19.04.2020")
								.addLoreLine("§a+ §7Köpfe")
								.addLoreLine("§6! §7Änderungen am Chance")
								.addLoreLine("")
								.addLoreLine("§8§m----------------")
								.addLoreLine("§a+ §7Hinzugefügt")
								.addLoreLine("§c- §7Entfernt")
								.addLoreLine("§6! §7Veränderung")
								.build());

						i++;
						
						gui.getInventory().setItem(i, new ItemBuilder(Material.BOOK)
								.setName("§6Update am: 12.04.2020")
								.addLoreLine("§a+ §7Verwandlung")
								.addLoreLine("§6! §7Änderungen am Chance")
								.addLoreLine("")
								.addLoreLine("§8§m----------------")
								.addLoreLine("§a+ §7Hinzugefügt")
								.addLoreLine("§c- §7Entfernt")
								.addLoreLine("§6! §7Veränderung")
								.build());

						i++;
						
						gui.getInventory().setItem(i, new ItemBuilder(Material.BOOK)
								.setName("§6Update am: 05.04.2020")
								.addLoreLine("§c- §7Shop Items")
								.addLoreLine("§a+ §7Haustiere")
								.addLoreLine("§a+ §7Spielzeuge")
								.addLoreLine("§a+ §7Rüstung")
								.addLoreLine("§a+ §7Effekte")
								.addLoreLine("")
								.addLoreLine("§8§m----------------")
								.addLoreLine("§a+ §7Hinzugefügt")
								.addLoreLine("§c- §7Entfernt")
								.addLoreLine("§6! §7Veränderung")
								.build());

						i++;
						
						gui.getInventory().setItem(i, new ItemBuilder(Material.BOOK)
								.setName("§6Update am: 27.03.2020")
								.addLoreLine("§a+ §7CoinBomb (Shop Spielzeug)")
								.addLoreLine("§c- §7Unendliche Kisten")
								.addLoreLine("§a+ §7Kisten Kauf")
								.addLoreLine("§a+ §7Special Items")
								.addLoreLine("§6! §7Coins 60% > 39%")
								.addLoreLine("§6! §7Nieten 29.7% > 50%")
								.addLoreLine("")
								.addLoreLine("§8§m----------------")
								.addLoreLine("§a+ §7Hinzugefügt")
								.addLoreLine("§c- §7Entfernt")
								.addLoreLine("§6! §7Veränderung")
								.build());

						i++;
						gui.getInventory().setItem(i, new ItemBuilder(Material.BOOK)
								.setName("§6Update am: 26.03.2020")
								.addLoreLine("§6! §7Shop Items 5% > 4%")
								.addLoreLine("§6! §7Lottoscheine 8% > 6%")
								.addLoreLine("§6! §7Coins 80% > 60%")
								.addLoreLine("§6! §7Nieten 6.7% > 29.7%")
								.addLoreLine("")
								.addLoreLine("§8§m----------------")
								.addLoreLine("§a+ §7Hinzugefügt")
								.addLoreLine("§c- §7Entfernt")
								.addLoreLine("§6! §7Veränderung")
								.build());

						i++;
						gui.getInventory().setItem(i, new ItemBuilder(Material.BOOK)
								.setName("§6Update am: 25.03.2020")
								.addLoreLine("§a+ §7ChangeLog.")
								.addLoreLine("§c- §71000 Coins.")
								.addLoreLine("§a+ §7500 Coins.")
								.addLoreLine("§a+ §7Wolf Haustier.")
								.addLoreLine("§a+ §7Pulzkuh Haustier.")
								.addLoreLine("§a+ §7Kaninchen Haustier.")
								.addLoreLine("")
								.addLoreLine("§8§m----------------")
								.addLoreLine("§a+ §7Hinzugefügt")
								.addLoreLine("§c- §7Entfernt")
								.addLoreLine("§6! §7Veränderung")
								.build());

						i++;
						gui.getInventory().setItem(i, new ItemBuilder(Material.BOOK)
								.setName("§6Update am: 24.03.2020")
								.addLoreLine("§6! §7Veröffentlicht für das Team")
								.addLoreLine("§6! §71000 Chance, 0.5% zu 0.3%")
								.addLoreLine("")
								.addLoreLine("§8§m----------------")
								.addLoreLine("§a+ §7Hinzugefügt")
								.addLoreLine("§c- §7Entfernt")
								.addLoreLine("§6! §7Veränderung")
								.build());

						i++;
						gui.getInventory().setItem(i, new ItemBuilder(Material.BOOK)
								.setName("§6Update am: 23.03.2020")
								.addLoreLine("§a+ §7Gegenstände")
								.addLoreLine("§a+ §7Unendlich Kisten")
								.addLoreLine("")
								.addLoreLine("§8§m----------------")
								.addLoreLine("§a+ §7Hinzugefügt")
								.addLoreLine("§c- §7Entfernt")
								.addLoreLine("§6! §7Veränderung")
								.build());

						i++;
						gui.getInventory().setItem(i, new ItemBuilder(Material.BOOK)
								.setName("§6Update am: 21.03.2020")
								.addLoreLine("§a+ §7Prototype")
								.addLoreLine("")
								.addLoreLine("§8§m----------------")
								.addLoreLine("§a+ §7Hinzugefügt")
								.addLoreLine("§c- §7Entfernt")
								.addLoreLine("§6! §7Veränderung")
								.build());

						gui.open();
					}
				}

				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.CHEST).setName("§6Kisten Öffnen")
							.addLoreLine("§7Klicke hier um Kisten Zu Öffnen§b")
							.addLoreLine(" ")
							.addLoreLine("§eSchift + Linksklick §8>> §7Changelogs")
							.build();
				}
			});

			gui.setButton(3, new GUIButton() {
				@Override
				public void onClick(InventoryClickEvent arg0) {
					GUI chest = new GUI(getCorePlayer(), "§6Kisten Kaufen", InventoryType.HOPPER);

					chest.setButton(0, new GUIButton() {
						@Override
						public void onClick(InventoryClickEvent arg0) {
							if(Coins.hasEnoughCoins(event.getPlayer(), 250)) {
								Coins.action(CoinAction.REMOVE, event.getPlayer(), 250);
								Coins.chestAction(CoinAction.ADD, event.getPlayer(), 1);
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.NOTE_PLING);
							}else {
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.NOTE_SNARE_DRUM);
							}
						}

						@Override
						public ItemStack getIcon() {
							return new ItemBuilder(Material.CHEST)
									.setName("§61x Kiste")
									.addLoreLine("§7Preis: §b250 Coins")
									.build();
						}
					});

					chest.setButton(1, new GUIButton() {
						@Override
						public void onClick(InventoryClickEvent arg0) {
							if(Coins.hasEnoughCoins(event.getPlayer(), 500)) {
								Coins.action(CoinAction.REMOVE, event.getPlayer(), 500);
								Coins.chestAction(CoinAction.ADD, event.getPlayer(), 3);
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.NOTE_PLING);
							}else {
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.NOTE_SNARE_DRUM);
							}
						}

						@Override
						public ItemStack getIcon() {
							return new ItemBuilder(Material.CHEST, 3)
									.setName("§63x Kiste")
									.addLoreLine("§7Preis: §b500 Coins")
									.addLoreLine("")
									.addLoreLine("§7Du Sparst §b250 Coins")
									.build();
						}
					});

					chest.setButton(2, new GUIButton() {
						@Override
						public void onClick(InventoryClickEvent arg0) {
							if(Coins.hasEnoughCoins(event.getPlayer(), 750)) {
								Coins.action(CoinAction.REMOVE, event.getPlayer(), 750);
								Coins.chestAction(CoinAction.ADD, event.getPlayer(), 5);
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.NOTE_PLING);
							}else {
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.NOTE_SNARE_DRUM);
							}
						}

						@Override
						public ItemStack getIcon() {
							return new ItemBuilder(Material.CHEST, 5)
									.setName("§65x Kiste")
									.addLoreLine("§7Preis: §b750 Coins")
									.addLoreLine("")
									.addLoreLine("§7Du Sparst §b500 Coins")
									.build();
						}
					});

					chest.setButton(3, new GUIButton() {
						@Override
						public void onClick(InventoryClickEvent arg0) {
							if(Coins.hasEnoughCoins(event.getPlayer(), 1900)) {
								Coins.action(CoinAction.REMOVE, event.getPlayer(), 1900);
								Coins.chestAction(CoinAction.ADD, event.getPlayer(), 10);
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.NOTE_PLING);
							}else {
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.NOTE_SNARE_DRUM);
							}
						}

						@Override
						public ItemStack getIcon() {
							return new ItemBuilder(Material.CHEST, 10)
									.setName("§610x Kiste")
									.addLoreLine("§7Preis: §b1.900 Coins")
									.addLoreLine("")
									.addLoreLine("§7Du Sparst §b600 Coins")
									.build();
						}
					});

					chest.setButton(4, new GUIButton() {
						@Override
						public void onClick(InventoryClickEvent arg0) {
							if(Coins.hasEnoughCoins(event.getPlayer(), 10000)) {
								Coins.action(CoinAction.REMOVE, event.getPlayer(), 10000);
								Coins.chestAction(CoinAction.ADD, event.getPlayer(), 50);
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.NOTE_PLING);
							}else {
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.NOTE_SNARE_DRUM);
							}
						}

						@Override
						public ItemStack getIcon() {
							return new ItemBuilder(Material.CHEST, 50)
									.setName("§650x Kiste")
									.addLoreLine("§7Preis: §b10.000 Coins")
									.addLoreLine("")
									.addLoreLine("§7Du Sparst §b2.500 Coins")
									.build();
						}
					});

					chest.open();
				}

				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.WORKBENCH)
							.setName("§6Kisten Kaufen")
							.addLoreLine("§7Klicke um die Gewünschte")
							.addLoreLine("§7menge zu kaufen.")
							.build();
				}
			});

			gui.open();
		}
	}

}
