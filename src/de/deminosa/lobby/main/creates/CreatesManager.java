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
import de.deminosa.lobby.utils.rocket.RocketBuilder;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	20:40:26 # 23.03.2020
 *
 */

public class CreatesManager implements Listener{

	HashMap<Player, Location> creats = new HashMap<>();
	HashMap<Player, Integer> chest = new HashMap<>();
	HashMap<Player, Integer> fail = new HashMap<>();
	HashMap<Player, ArrayList<Location>> holos = new HashMap<>();
	HashMap<Player, Location> lastLoc = new HashMap<>();

	@EventHandler
	public void quit(PlayerQuitEvent event) {
		if(creats.containsKey(event.getPlayer())) {
			creats.get(event.getPlayer()).getBlock().setType(Material.ENCHANTMENT_TABLE);
			new Creates(creats.get(event.getPlayer()));
			creats.remove(event.getPlayer());
			removeHolos(event.getPlayer());
		}
	}
	
	@EventHandler
	public void move(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if(creats.containsKey(player)) {
			if(player.getLocation().distance(creats.get(player)) > 1 &&
					chest.get(player) > 0) {
				player.teleport(creats.get(player).clone().add(0.5,0,0.5));
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
						if(Coins.hasEnoughChest(event.getPlayer(), 1)) {
							if(!creats.containsValue(block.getLocation())) {
								event.getPlayer().closeInventory();
								lastLoc.put(event.getPlayer(), event.getPlayer().getLocation().clone());
								creats.put(event.getPlayer(), block.getLocation());
								new Creates(CoreCache.getCorePlayer(event.getPlayer()), block);
								chest.put(event.getPlayer(), 4);
								Coins.chestAction(CoinAction.REMOVE, event.getPlayer(), 1);
							}
						}
					}else {
						GUI gui = new GUI(getCorePlayer(), "§6ChangeLog");
						
						int i = 0;
						
						gui.getInventory().setItem(i, new ItemBuilder(Material.BOOK_AND_QUILL)
								.setName("§6Update am: 27.03.2020")
								.addLoreLine("§a+ §7CoinBomb (Shop Spielzeug)")
								.addLoreLine("§c- §7Unendliche Kisten")
								.addLoreLine("§a+ §7Kisten Kauf")
								.addLoreLine("§a+ §7Special Items")
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
					return new ItemBuilder(Material.ENDER_CHEST).setName("§6Kisten Öffnen")
							.addLoreLine("§7Du hast §b"+Coins.getChest(event.getPlayer())+"/1 §7Kisten im besitz.")
							.addLoreLine(" ")
							.addLoreLine("§6Gewinne:")
							.addLoreLine("§7500 Coins §b0.3%")
							.addLoreLine("§7Special Items §b0.7%")
							.addLoreLine("§7Shop Items §b4%")
							.addLoreLine("§7Lotto Scheine §b6%")
							.addLoreLine("§7Coins §b39%")
							.addLoreLine("§7Nieten §b50%")
							.addLoreLine(" ")
							.addLoreLine("§eSchift + Linksklick §8>> §7Changelogs")
							.build();
				}
			});

			gui.setButton(3, new GUIButton() {
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
					return new ItemBuilder(Material.WORKBENCH)
							.setName("§6Kisten Kaufen")
							.addLoreLine("§71x Kiste")
							.addLoreLine("§6Preis: §b250 Coins")
							.build();
				}
			});

			gui.open();
		}

		if(block != null && block.getType() == Material.TNT) {
			if(creats.containsKey(event.getPlayer())) {
				if(chest.get(event.getPlayer()) >= 1) {
					chest.put(event.getPlayer(), chest.get(event.getPlayer())-1);
					block.setType(Material.AIR);
					int coins = ThreadLocalRandom.current().nextInt(25)+1;
					Hologram holo = new Hologram(block.getLocation().add(0.5,0,0.5), "");
					holo.create();
					
					addHolos(event.getPlayer(), holo);
					if(!fail.containsKey(event.getPlayer())) {
						fail.put(event.getPlayer(), 0);
					}

					if(CoreMath.chance(0.3)) {
						holo.changeText("§6Coins: §b+500");
						new BukkitRunnable() {
							@Override
							public void run() {
								for(int i = 0; i < 50; i++) {
									rocked(event.getPlayer(), i);
								}
								Coins.action(CoinAction.ADD, event.getPlayer(), 500);
								CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Coins", "§a+500");
								Bukkit.broadcastMessage("§8§l[§b§l!§8§l] §6"+event.getPlayer().getName() + " §7hat beim Kisten Spiel, §b500 Coins §7gewonnen!");
							}
						}.runTaskLater(RisenWorld_Lobby.getInstance(), 5);
					}else if(CoreMath.chance(0.7)) {
						ShopItemBuilder[] items = {new ShopArmorLetherRainbow(), new EffectBarierre(), new ToyCoinTNT()};
						getItem(holo, event.getPlayer(), items, ShopType.SPECIAL);
					}else if(CoreMath.chance(4)) {
						int i = ThreadLocalRandom.current().nextInt(4);
						if(i == 0) {
							ShopItemBuilder[] items = {new ShopArmorDiamond.Helmet(),
									new ShopArmorDiamond.Chestplate(), new ShopArmorDiamond.Leggins(), 
									new ShopArmorDiamond.Boots(), new ShopArmorLether.Helmet(),
									new ShopArmorLether.Chestplate(), new ShopArmorLether.Leggins(),
									new ShopArmorLether.Boots(), new ShopArmorGold.Helmet(),
									new ShopArmorGold.Chestplate(), new ShopArmorGold.Leggins(),
									new ShopArmorGold.Boots(), new ShopArmorIron.Helmet(),
									new ShopArmorIron.Chestplate(), new ShopArmorIron.Leggins(),
									new ShopArmorIron.Boots(), new ShopArmorChain.Helmet(),
									new ShopArmorChain.Chestplate(), new ShopArmorChain.Leggins(),
									new ShopArmorChain.Boots()};
							getItem(holo, event.getPlayer(), items, ShopType.ARMOR);
						}else if(i == 1) {
							ShopItemBuilder[] items = {new EffectHerz(), new EffectFlame()};
							getItem(holo, event.getPlayer(), items, ShopType.EFFECT);
						}else if(i == 2) {
							ShopItemBuilder[] items = {new PetCow(), new PetChicken(), new PetPig(), new PetSheep(),
									new PetPilzkuh(), new PetRabbit(), new PetWolf()};
							getItem(holo, event.getPlayer(), items, ShopType.PET);
						}else if(i == 3) {
							ShopItemBuilder[] items = {new ToyJumpStick(), new ToyKnockBack()};
							getItem(holo, event.getPlayer(), items, ShopType.TOY);
						}
					}else if(CoreMath.chance(6)){
						int amount = ThreadLocalRandom.current().nextInt(3)+1;
						holo.changeText("§6Lotto: §b+"+amount);
						new BukkitRunnable() {
							@Override
							public void run() {
								rocked(event.getPlayer(), 1);
								Coins.lottoAction(LottoAction.ADD, event.getPlayer(), amount);
								CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Lotto", "§a+"+amount);
							}
						}.runTaskLater(RisenWorld_Lobby.getInstance(), 2);
					}else if(CoreMath.chance(39)){
						holo.changeText("§6Coins: §b+"+coins);
						new BukkitRunnable() {
							@Override
							public void run() {
								Coins.action(CoinAction.ADD, event.getPlayer(), coins);
								CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Coins", "§a+"+coins);
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.LEVEL_UP);
							}
						}.runTaskLater(RisenWorld_Lobby.getInstance(), 2);
					}else {
						holo.changeText("§cNiete");
						CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.EXPLODE);
						fail.put(event.getPlayer(), fail.get(event.getPlayer())+1);
					}

					if(chest.get(event.getPlayer()) == 0) {
						new BukkitRunnable() {
							@Override
							public void run() {
								event.getPlayer().teleport(lastLoc.get(event.getPlayer()));
								try {
									if(creats.containsKey(event.getPlayer())) {
										creats.get(event.getPlayer()).getBlock().setType(Material.ENCHANTMENT_TABLE);
										creats.remove(event.getPlayer());
										removeHolos(event.getPlayer());
										if(fail.get(event.getPlayer()) == 4) {
											Coins.chestAction(CoinAction.ADD, event.getPlayer(), 1);
											CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Chest", "§a+1");
										}
										if(fail.containsKey(event.getPlayer())) {
											fail.remove(event.getPlayer());
										}
									}
								}catch (Exception e) {
									removeHolos(event.getPlayer());
								}
							}
						}.runTaskLater(RisenWorld_Lobby.getInstance(), 20*3);
					}
				}
			}
		}
	}

	private void addHolos(Player player, Hologram holo) {
		if(!holos.containsKey(player)) {
			ArrayList<Location> locs = new ArrayList<>();
			locs.add(holo.getLocation());
			holos.put(player, locs);
		}else {
			ArrayList<Location> locs = holos.get(player);
			locs.add(holo.getLocation());
			holos.put(player, locs);
		}
	}
	
	private void removeHolos(Player player) {
		if(holos.containsKey(player)) {
			for(Location locs : holos.get(player)) {
				Hologram hol = Hologram.getByLocation(locs);
				hol.remove();
			}
			holos.remove(player);
		}
	}
	
	private void getItem(Hologram holo, Player player, ShopItemBuilder[] items, ShopType type) {
		int r = ThreadLocalRandom.current().nextInt(items.length);
		holo.changeText("§6"+type.getInventoryName());
		Hologram nextLine = new Hologram(holo.getLocation().add(0,0.75,0), "§b"+items[r].getItemName());
		nextLine.create();
		addHolos(player, nextLine);
		new BukkitRunnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10; i++) {
					rocked(player, i);
				}
				if(!ShopHandler.hasBought(type, player.getUniqueId(), items[r])) {
					ShopHandler.setBought(type, items[r], player.getUniqueId());
					CoreCache.getCorePlayer(player).sendMessage("Shop", "§aDu hast §6" + items[r].getItemName() + " §ageschenkt bekommen!");
				}else {
					if((items[r].getPrice()/4) > 999) {
						CoreCache.getCorePlayer(player).sendMessage("Shop", "Dir wurden §b" + ((items[r].getPrice()/4)/1000) + " Lottoscheine §7gutgeschrieben!");
						Coins.lottoAction(LottoAction.ADD, player, ((items[r].getPrice()/4)/1000));
						Hologram Line3 = new Hologram(holo.getLocation().add(0,0.50,0), "§6Lottoscheine: §b+"+((items[r].getPrice()/4)/1000));
						Line3.create();
						addHolos(player, Line3);
					}else {
						CoreCache.getCorePlayer(player).sendMessage("Shop", "Dir wurden §b" + (items[r].getPrice()/4) + " Coins §7gutgeschrieben!");
						Coins.action(CoinAction.ADD, player, (items[r].getPrice()/4));
						Hologram Line3 = new Hologram(holo.getLocation().add(0,0.50,0), "§6Coins: §b+"+(items[r].getPrice()/4));
						Line3.create();
						addHolos(player, Line3);
					}
					
				}
				Bukkit.broadcastMessage("§8§l[§b§l!§8§l] §6"+player.getName() + " §7hat beim Kisten Spiel, §b" + items[r].getItemName() + " §7gewonnen!");
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 5);
	}

	private void rocked(Player player, int i) {
		new BukkitRunnable() {
			@Override
			public void run() {
				RocketBuilder builder = new RocketBuilder(player.getWorld(), player.getLocation());
				builder.build(true, true, Type.BURST, Color.AQUA, Color.ORANGE, 1);
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 2*i);
	}

}
