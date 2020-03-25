package de.deminosa.lobby.main.shop;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import de.deminosa.coinmanager.Coins;
import de.deminosa.coinmanager.command.CoinsCommand.CoinAction;
import de.deminosa.core.Core;
import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CorePlayerData;
import de.deminosa.core.utils.gui.GUI;
import de.deminosa.core.utils.gui.GUIButton;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.core.utils.mysql.ColumType;
import de.deminosa.core.utils.mysql.MySQL;
import de.deminosa.lobby.main.shop.Items.effecte.EffectBarierre;
import de.deminosa.lobby.main.shop.Items.effecte.EffectFlame;
import de.deminosa.lobby.main.shop.Items.effecte.EffectHerz;
import de.deminosa.lobby.main.shop.Items.pets.PetChicken;
import de.deminosa.lobby.main.shop.Items.pets.PetCow;
import de.deminosa.lobby.main.shop.Items.pets.PetPig;
import de.deminosa.lobby.main.shop.Items.pets.PetPilzkuh;
import de.deminosa.lobby.main.shop.Items.pets.PetRabbit;
import de.deminosa.lobby.main.shop.Items.pets.PetSheep;
import de.deminosa.lobby.main.shop.Items.pets.PetUitls;
import de.deminosa.lobby.main.shop.Items.pets.PetWolf;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorChain;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorDiamond;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorGold;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorIron;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorLether;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorLetherRainbow;
import de.deminosa.lobby.main.shop.Items.toy.ToyJumpStick;
import de.deminosa.lobby.main.shop.Items.toy.ToyKnockBack;
import de.deminosa.lobby.main.shop.api.ShopInfo;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import de.deminosa.lobby.main.shop.api.ShopType;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	17:28:17 # 22.12.2019
 *
 */

public class Shop {

	private static HashMap<ShopItemBuilder, ShopType> items = new HashMap<>();
	@SuppressWarnings("unused")
	private static String table = "LobbyShop_", ItemID = "ItemID", buy = "Bought", InUse = "InUse", OK = "OK";

	public static void init() {
		String[] columName = {"UUID", "ItemID", "Bought", "Amount", "InUse", "OK"};
		ColumType[] columType = {ColumType.VARCHAR_128, ColumType.INT, ColumType.INT, ColumType.INT, ColumType.INT, ColumType.VARCHAR_256};

		for(ShopType type : ShopType.values()) {
			MySQL.createTable("LobbyShop_"+type.name(), columName, columType);
		}

		initItems();
	}

	private static void initItems() {
		items.put(new ShopArmorLether.Helmet(), ShopType.ARMOR);
		items.put(new ShopArmorLether.Chestplate(), ShopType.ARMOR);
		items.put(new ShopArmorLether.Boots(), ShopType.ARMOR);
		items.put(new ShopArmorLether.Leggins(), ShopType.ARMOR);
		
		items.put(new ShopArmorIron.Helmet(), ShopType.ARMOR);
		items.put(new ShopArmorIron.Chestplate(), ShopType.ARMOR);
		items.put(new ShopArmorIron.Boots(), ShopType.ARMOR);
		items.put(new ShopArmorIron.Leggins(), ShopType.ARMOR);
		
		items.put(new ShopArmorGold.Helmet(), ShopType.ARMOR);
		items.put(new ShopArmorGold.Chestplate(), ShopType.ARMOR);
		items.put(new ShopArmorGold.Boots(), ShopType.ARMOR);
		items.put(new ShopArmorGold.Leggins(), ShopType.ARMOR);
		
		items.put(new ShopArmorChain.Helmet(), ShopType.ARMOR);
		items.put(new ShopArmorChain.Chestplate(), ShopType.ARMOR);
		items.put(new ShopArmorChain.Boots(), ShopType.ARMOR);
		items.put(new ShopArmorChain.Leggins(), ShopType.ARMOR);
		
		items.put(new ShopArmorDiamond.Helmet(), ShopType.ARMOR);
		items.put(new ShopArmorDiamond.Chestplate(), ShopType.ARMOR);
		items.put(new ShopArmorDiamond.Boots(), ShopType.ARMOR);
		items.put(new ShopArmorDiamond.Leggins(), ShopType.ARMOR);
		
		items.put(new ShopArmorLetherRainbow(), ShopType.ARMOR);
		
		items.put(new ToyKnockBack(), ShopType.TOY);
		items.put(new ToyJumpStick(), ShopType.TOY);
		
		items.put(new EffectHerz(), ShopType.EFFECT);
		items.put(new EffectFlame(), ShopType.EFFECT);
		items.put(new EffectBarierre(), ShopType.EFFECT);
		
		items.put(new PetSheep(), ShopType.PET);
		items.put(new PetCow(), ShopType.PET);
		items.put(new PetChicken(), ShopType.PET);
		items.put(new PetPig(), ShopType.PET);
		items.put(new PetWolf(), ShopType.PET);
		items.put(new PetPilzkuh(), ShopType.PET);
		items.put(new PetRabbit(), ShopType.PET);
	}

	public static void openInit(CorePlayer player) {
		GUI gui = new GUI(player, "§6Shop §0-> §eSelect...", 9);

		gui.setButton(0, new ShopInfo.Balance(player.getBukkitPlayer()));
		gui.setButton(8, new ShopInfo.Balance(player.getBukkitPlayer()));

		gui.setButton(2, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.ARMOR, player);
			}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.LEATHER_CHESTPLATE)
						.setName("§6Rüstung")
						.build();
			}
		});

		gui.setButton(3, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.EFFECT, player);
			}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.POTION)
						.setName("§6Effecte")
						.build();
			}
		});

//		gui.setButton(4, new GUIButton() {
//			@Override
//			public void onClick(InventoryClickEvent arg0) {
//				//openShop(ShopType.NONE, cplayer);
//			}
//
//			@Override
//			public ItemStack getIcon() {
//				return new ItemBuilder(Material.REDSTONE)
//						.setName("§c§lDemnächst")
//						.addLoreLine("§7Geplant: §6Eintellungen")
//						.build();
//			}
//		});

		gui.setButton(5, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.PET, player);
			}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.MONSTER_EGG)
						.setName("§6Haustiere")
						.build();
			}
		});

		gui.setButton(6, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.TOY, player);
			}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.STICK)
						.setName("§6Spielzeuge")
						.build();
			}
		});

//		gui.setButton(8, new GUIButton() {
//			@Override
//			public void onClick(InventoryClickEvent arg0) {
//				//openShop(ShopType.NONE, cplayer);
//			}
//
//			@Override
//			public ItemStack getIcon() {
//				return new ItemBuilder(Material.CHEST)
//						.setName("§c§lDemnächst")
//						.addLoreLine("§7Geplant: §6Lotto")
//						.build();
//			}
//		});

		gui.open();
	}

	private static void openShop(ShopType type, CorePlayer cplayer) {
		GUI gui = new GUI(cplayer, "§b"+type.getInventoryName());
//		for(int i = 45-9; i < 45; i++) {
//			gui.setButton(i, new GUIButton() {
//				@Override
//				public void onClick(InventoryClickEvent arg0) {}
//
//				@Override
//				public ItemStack getIcon() {
//					return new ItemBuilder(Material.STAINED_GLASS_PANE)
//							.setDurability((short)0)
//							.setName(" ")
//							.build();
//				}
//			});
//		}
		if(type == ShopType.PET) {
			gui.setButton(40, new GUIButton() {
				@Override
				public void onClick(InventoryClickEvent arg0) {
					PetUitls.stopFollow(cplayer.getBukkitPlayer());
				}
				
				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.BARRIER).setName("§cHaustier Entfernen").build();
				}
			});
		}
		
		if(type == ShopType.EFFECT) {
			gui.setButton(40, new GUIButton() {
				@Override
				public void onClick(InventoryClickEvent arg0) {
					CorePlayerData.setData(cplayer, "lobby", "effect", null);
				}
				
				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.BARRIER).setName("§cEffecte Entfernen").build();
				}
			});
		}
		
		gui.setButton(45, new ShopInfo.Balance(cplayer.getBukkitPlayer()));
		gui.setButton(53, new ShopInfo.Balance(cplayer.getBukkitPlayer()));

		gui.setButton(47, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.ARMOR, cplayer);
			}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.LEATHER_CHESTPLATE)
						.setName("§6Rüstung")
						.build();
			}
		});

		gui.setButton(48, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.EFFECT, cplayer);
			}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.POTION)
						.setName("§6Effecte")
						.build();
			}
		});

//		gui.setButton(49, new GUIButton() {
//			@Override
//			public void onClick(InventoryClickEvent arg0) {
//				//openShop(ShopType.NONE, cplayer);
//			}
//
//			@Override
//			public ItemStack getIcon() {
//				return new ItemBuilder(Material.REDSTONE)
//						.setName("§c§lDemnächst")
//						.addLoreLine("§7Geplant: §6Eintellungen")
//						.build();
//			}
//		});

		gui.setButton(50, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.PET, cplayer);
			}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.MONSTER_EGG)
						.setName("§6Haustiere")
						.build();
			}
		});

		gui.setButton(51, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.TOY, cplayer);
			}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.STICK)
						.setName("§6Spielzeuge")
						.build();
			}
		});

//		gui.setButton(53, new GUIButton() {
//			@Override
//			public void onClick(InventoryClickEvent arg0) {
//				//openShop(ShopType.NONE, cplayer);
//			}
//
//			@Override
//			public ItemStack getIcon() {
//				return new ItemBuilder(Material.CHEST)
//						.setName("§c§lDemnächst")
//						.addLoreLine("§7Geplant: §6Lotto")
//						.build();
//			}
//		});

		int t = 0;

		for(ShopItemBuilder item : items.keySet()) {
			if(items.get(item) == type) {
				if(gui.getInventory().getItem(item.getSlot()).getType() == Material.STAINED_GLASS_PANE) {
					t++;
					new BukkitRunnable() {
						@Override
						public void run() {
							cplayer.playsound(Sound.CLICK);

							gui.setButton(item.getSlot(), new GUIButton() {
								@Override
								public void onClick(InventoryClickEvent event) {
									Player player = (Player)event.getWhoClicked();
									if(item.canBuying()) {
										if(Coins.getCoins(player) >= item.getPrice()) {
											if(!ShopHandler.hasBought(type, cplayer.getUUID(), item)) {
												if(item.getItemID() != 105) {
													Coins.action(CoinAction.REMOVE, player, item.getPrice());

													cplayer.sendMessage("Shop", "§aVielen dank für ihren Einkauf! §8(§7"+item.getIcon(cplayer).getItemMeta().getDisplayName()+"§8)");
													cplayer.playsound(Sound.ORB_PICKUP);
													ShopHandler.setBought(type, item, player.getUniqueId());
													openShop(type, cplayer);
												}else {
													if(ShopArmorLether.hasAllLether(player)) {
														Coins.action(CoinAction.REMOVE, player, item.getPrice());

														cplayer.sendMessage("Shop", "§aVielen dank für ihren Einkauf! §8(§7Regenbogen Rüstung. Leder.§8)");
														cplayer.playsound(Sound.ORB_PICKUP);
														ShopHandler.setBought(type, item, player.getUniqueId());
														openShop(type, cplayer);
													}else {
														cplayer.sendMessage("Shop", "§cBitte Kaufe zuerst alle Leder Rüstungen!");
													}
												}
											}else {
												item.getAction(player);
												//player.closeInventory();
											}
										}else {
											if(ShopHandler.hasBought(type, cplayer.getUUID(), item)) {
												item.getAction(player);
											}else {
												cplayer.sendMessage("Shop", "§cBitte überprüfe ob du genügen coins hast.");
											}
										}
									}else {
										cplayer.sendMessage("Shop", "§cDieses Item kann nicht gekauft werden!");
									}
								}

								@Override
								public ItemStack getIcon() {
									return item.getIcon(cplayer);
								}
							});
						}
					}.runTaskLater(Core.getInstance(), 2*t);
				}
			}
		}
		gui.open();
	}

}
