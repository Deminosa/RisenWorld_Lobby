package de.deminosa.lobby.main.creates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect.Type;
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
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import de.deminosa.coinmanager.Coins;
import de.deminosa.coinmanager.command.CoinsCommand.CoinAction;
import de.deminosa.coinmanager.command.LottoCommand.LottoAction;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.gui.GUI;
import de.deminosa.core.utils.gui.GUIButton;
import de.deminosa.core.utils.hologramm.Hologram;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.core.utils.mathmanager.CoreMath;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.creates.builder.Creates;
import de.deminosa.lobby.main.shop.ShopHandler;
import de.deminosa.lobby.main.shop.Items.effecte.EffectFlame;
import de.deminosa.lobby.main.shop.Items.effecte.EffectHerz;
import de.deminosa.lobby.main.shop.Items.pets.PetChicken;
import de.deminosa.lobby.main.shop.Items.pets.PetCow;
import de.deminosa.lobby.main.shop.Items.pets.PetPig;
import de.deminosa.lobby.main.shop.Items.pets.PetPilzkuh;
import de.deminosa.lobby.main.shop.Items.pets.PetRabbit;
import de.deminosa.lobby.main.shop.Items.pets.PetSheep;
import de.deminosa.lobby.main.shop.Items.pets.PetWolf;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorChain;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorDiamond;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorGold;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorIron;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorLether;
import de.deminosa.lobby.main.shop.Items.special.EffectBarierre;
import de.deminosa.lobby.main.shop.Items.special.ShopArmorLetherRainbow;
import de.deminosa.lobby.main.shop.Items.special.ToyCoinTNT;
import de.deminosa.lobby.main.shop.Items.toy.ToyJumpStick;
import de.deminosa.lobby.main.shop.Items.toy.ToyKnockBack;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import de.deminosa.lobby.main.shop.api.ShopType;
import de.deminosa.lobby.regedit.Toroku;
import de.deminosa.lobby.utils.rocket.RocketBuilder;

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

			gui.setButton(0, new GUIButton() {
				@Override
				public void onClick(InventoryClickEvent e) {
					if(e.getAction() != InventoryAction.MOVE_TO_OTHER_INVENTORY) {
						if(Coins.hasEnoughChest(event.getPlayer(), 1)) {
							if(!creats.containsValue(block.getLocation())) {
								event.getPlayer().closeInventory();
								Creates creates = new Creates(CoreCache.getCorePlayer(event.getPlayer()), block);
								
								creates.setJeckpot(0.2);
								creates.setMagic(0.3);
								creates.setSpezial(0.5);
								creates.setArmor(1);
								creates.setEffecte(1);
								creates.setPet(1);
								creates.setToy(1);
								creates.setLotto(6);
								creates.setCoins(39);
								
								Toroku.addEvent(creates);
								
								Coins.chestAction(CoinAction.REMOVE, event.getPlayer(), 1);
							}
						}
					}else {
						GUI gui = new GUI(getCorePlayer(), "§6ChangeLog");

						int i = 0;
						gui.getInventory().setItem(i, new ItemBuilder(Material.BOOK_AND_QUILL)
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
							.addLoreLine("§7Du hast §b"+Coins.getChest(event.getPlayer())+"/1 §7Kisten im besitz.")
							.addLoreLine(" ")
							.addLoreLine("§6Gewinne:")
							.addLoreLine("§7500 Coins §b0.2%")
							.addLoreLine("§7Verwandlung §b0.3%")
							.addLoreLine("§7Ausergewöhnlich §b0.5%")
							.addLoreLine("§7Rüstung §b1%")
							.addLoreLine("§7Effekte §b1%")
							.addLoreLine("§7Haustiere §b1%")
							.addLoreLine("§7Spielzeug §b1%")
							.addLoreLine("§7Lotto Scheine §b6%")
							.addLoreLine("§7Coins §b39%")
							.addLoreLine("§7Nieten §b50%")
							.addLoreLine(" ")
							.addLoreLine("§eSchift + Linksklick §8>> §7Changelogs")
							.build();
				}
			});

			gui.setButton(2, new GUIButton() {
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
			
			gui.setButton(4, new GUIButton() {	
				@Override
				public void onClick(InventoryClickEvent arg0) {
					if(Coins.hasEnoughChest(event.getPlayer(), 5)) {
						if(!creats.containsValue(block.getLocation())) {
							event.getPlayer().closeInventory();
							Creates creates = new Creates(CoreCache.getCorePlayer(event.getPlayer()), block);
							
							creates.setJeckpot(1);
							creates.setMagic(1);
							creates.setSpezial(2);
							creates.setArmor(3);
							creates.setEffecte(3);
							creates.setPet(3);
							creates.setToy(3);
							creates.setLotto(5);
							creates.setCoins(100);
							
							Toroku.addEvent(creates);
							
							Coins.chestAction(CoinAction.REMOVE, event.getPlayer(), 5);
						}
					}
				}
				
				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.ENDER_CHEST).setName("§6Kisten Öffnen")
							.addLoreLine("§7Du hast §b"+Coins.getChest(event.getPlayer())+"/5 §7Kisten im besitz.")
							.addLoreLine(" ")
							.addLoreLine("§6Gewinne:")
							.addLoreLine("§7500 Coins §b1%")
							.addLoreLine("§7Verwandlung §b1%")
							.addLoreLine("§7Ausergewöhnlich §b2%")
							.addLoreLine("§7Rüstung §b3%")
							.addLoreLine("§7Effekte §b3%")
							.addLoreLine("§7Haustiere §b3%")
							.addLoreLine("§7Spielzeug §b3%")
							.addLoreLine("§7Lotto Scheine §b5%")
							.addLoreLine("§7Coins §b79%")
							.addLoreLine("§7Nieten §b0%")
							.build();
				}
			});

			gui.open();
		}
	}

}
