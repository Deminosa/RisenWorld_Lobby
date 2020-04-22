package de.deminosa.lobby.main.shop;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
import de.deminosa.lobby.main.shop.Items.effecte.EffectAngry;
import de.deminosa.lobby.main.shop.Items.effecte.EffectBlockCrake;
import de.deminosa.lobby.main.shop.Items.effecte.EffectCloud;
import de.deminosa.lobby.main.shop.Items.effecte.EffectCrit;
import de.deminosa.lobby.main.shop.Items.effecte.EffectDripWater;
import de.deminosa.lobby.main.shop.Items.effecte.EffectExplode;
import de.deminosa.lobby.main.shop.Items.effecte.EffectFlame;
import de.deminosa.lobby.main.shop.Items.effecte.EffectFootstep;
import de.deminosa.lobby.main.shop.Items.effecte.EffectHappy;
import de.deminosa.lobby.main.shop.Items.effecte.EffectHerz;
import de.deminosa.lobby.main.shop.Items.effecte.EffectMagicSpell;
import de.deminosa.lobby.main.shop.Items.effecte.EffectNotes;
import de.deminosa.lobby.main.shop.Items.effecte.EffectPortal;
import de.deminosa.lobby.main.shop.Items.effecte.EffectRedStone;
import de.deminosa.lobby.main.shop.Items.heads.emoji.HeadCool;
import de.deminosa.lobby.main.shop.Items.heads.emoji.HeadCrying;
import de.deminosa.lobby.main.shop.Items.heads.emoji.HeadHappy;
import de.deminosa.lobby.main.shop.Items.heads.emoji.HeadLove;
import de.deminosa.lobby.main.shop.Items.heads.emoji.HeadSurprised;
import de.deminosa.lobby.main.shop.Items.heads.emoji.HeadTroll;
import de.deminosa.lobby.main.shop.Items.heads.emoji.HeadWink;
import de.deminosa.lobby.main.shop.Items.heads.somthing.HeadChrome;
import de.deminosa.lobby.main.shop.Items.heads.somthing.HeadDemonKing;
import de.deminosa.lobby.main.shop.Items.heads.somthing.HeadGlumanda;
import de.deminosa.lobby.main.shop.Items.heads.somthing.HeadOre;
import de.deminosa.lobby.main.shop.Items.heads.somthing.HeadPikachu;
import de.deminosa.lobby.main.shop.Items.heads.somthing.HeadTails;
import de.deminosa.lobby.main.shop.Items.heads.somthing.HeadWizard;
import de.deminosa.lobby.main.shop.Items.heads.team.HeadAndre;
import de.deminosa.lobby.main.shop.Items.heads.team.HeadDeminosa;
import de.deminosa.lobby.main.shop.Items.heads.team.HeadDobby;
import de.deminosa.lobby.main.shop.Items.heads.team.HeadFlo;
import de.deminosa.lobby.main.shop.Items.heads.team.HeadLone;
import de.deminosa.lobby.main.shop.Items.heads.team.HeadPaddi;
import de.deminosa.lobby.main.shop.Items.heads.team.HeadStivi;
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
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorLetherBlue;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorLetherGreen;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorLetherRed;
import de.deminosa.lobby.main.shop.Items.special.EffectBarierre;
import de.deminosa.lobby.main.shop.Items.special.ShopArmorLetherRainbow;
import de.deminosa.lobby.main.shop.Items.special.ToyCoinTNT;
import de.deminosa.lobby.main.shop.Items.toy.ToyJumpStick;
import de.deminosa.lobby.main.shop.Items.toy.ToyKnockBack;
import de.deminosa.lobby.main.shop.Items.toy.ToyTnt;
import de.deminosa.lobby.main.shop.Items.verwandlung.VerwandlungChicken;
import de.deminosa.lobby.main.shop.Items.verwandlung.VerwandlungCow;
import de.deminosa.lobby.main.shop.Items.verwandlung.VerwandlungPig;
import de.deminosa.lobby.main.shop.Items.verwandlung.VerwandlungPilzkuh;
import de.deminosa.lobby.main.shop.Items.verwandlung.VerwandlungRabbit;
import de.deminosa.lobby.main.shop.Items.verwandlung.VerwandlungSheep;
import de.deminosa.lobby.main.shop.Items.verwandlung.VerwandlungsManager;
import de.deminosa.lobby.main.shop.api.EconomyType;
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

	public static HashMap<ShopItemBuilder, ShopType> items = new HashMap<>();
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

		items.put(new ShopArmorLetherBlue.Helmet(), ShopType.ARMOR);
		items.put(new ShopArmorLetherBlue.Chestplate(), ShopType.ARMOR);
		items.put(new ShopArmorLetherBlue.Boots(), ShopType.ARMOR);
		items.put(new ShopArmorLetherBlue.Leggins(), ShopType.ARMOR);

		items.put(new ShopArmorLetherGreen.Helmet(), ShopType.ARMOR);
		items.put(new ShopArmorLetherGreen.Chestplate(), ShopType.ARMOR);
		items.put(new ShopArmorLetherGreen.Boots(), ShopType.ARMOR);
		items.put(new ShopArmorLetherGreen.Leggins(), ShopType.ARMOR);

		items.put(new ShopArmorLetherRed.Helmet(), ShopType.ARMOR);
		items.put(new ShopArmorLetherRed.Chestplate(), ShopType.ARMOR);
		items.put(new ShopArmorLetherRed.Boots(), ShopType.ARMOR);
		items.put(new ShopArmorLetherRed.Leggins(), ShopType.ARMOR);

		items.put(new ToyKnockBack(), ShopType.TOY);
		items.put(new ToyJumpStick(), ShopType.TOY);
		items.put(new ToyTnt(), ShopType.TOY);

		items.put(new EffectHerz(), ShopType.EFFECT);
		items.put(new EffectFlame(), ShopType.EFFECT);
		items.put(new EffectMagicSpell(), ShopType.EFFECT);
		items.put(new EffectNotes(), ShopType.EFFECT);
		items.put(new EffectFootstep(), ShopType.EFFECT);
		items.put(new EffectHappy(), ShopType.EFFECT);
		items.put(new EffectAngry(), ShopType.EFFECT);
		items.put(new EffectExplode(), ShopType.EFFECT);
		items.put(new EffectCrit(), ShopType.EFFECT);
		items.put(new EffectPortal(), ShopType.EFFECT);
		items.put(new EffectDripWater(), ShopType.EFFECT);
		items.put(new EffectCloud(), ShopType.EFFECT);
		items.put(new EffectBlockCrake(), ShopType.EFFECT);
		items.put(new EffectRedStone(), ShopType.EFFECT);

		items.put(new PetSheep(), ShopType.PET);
		items.put(new PetCow(), ShopType.PET);
		items.put(new PetChicken(), ShopType.PET);
		items.put(new PetPig(), ShopType.PET);
		items.put(new PetWolf(), ShopType.PET);
		items.put(new PetPilzkuh(), ShopType.PET);
		items.put(new PetRabbit(), ShopType.PET);

		items.put(new EffectBarierre(), ShopType.SPECIAL);
		items.put(new ToyCoinTNT(), ShopType.SPECIAL);
		items.put(new ShopArmorLetherRainbow(), ShopType.SPECIAL);

		items.put(new VerwandlungCow(), ShopType.MAGIC);
		items.put(new VerwandlungChicken(), ShopType.MAGIC);
		items.put(new VerwandlungPig(), ShopType.MAGIC);
		items.put(new VerwandlungPilzkuh(), ShopType.MAGIC);
		items.put(new VerwandlungRabbit(), ShopType.MAGIC);
		items.put(new VerwandlungSheep(), ShopType.MAGIC);

		items.put(new HeadDemonKing(), ShopType.HEAD);
		items.put(new HeadGlumanda(), ShopType.HEAD);
		items.put(new HeadPikachu(), ShopType.HEAD);
		items.put(new HeadTails(), ShopType.HEAD);
		items.put(new HeadWizard(), ShopType.HEAD);
		items.put(new HeadChrome(), ShopType.HEAD);
		items.put(new HeadOre(), ShopType.HEAD);

		items.put(new HeadCool(), ShopType.HEAD);
		items.put(new HeadCrying(), ShopType.HEAD);
		items.put(new HeadHappy(), ShopType.HEAD);
		items.put(new HeadLove(), ShopType.HEAD);
		items.put(new HeadSurprised(), ShopType.HEAD);
		items.put(new HeadTroll(), ShopType.HEAD);
		items.put(new HeadWink(), ShopType.HEAD);

		items.put(new HeadAndre(), ShopType.HEAD);
		items.put(new HeadDeminosa(), ShopType.HEAD);
		items.put(new HeadDobby(), ShopType.HEAD);
		items.put(new HeadFlo(), ShopType.HEAD);
		items.put(new HeadLone(), ShopType.HEAD);
		items.put(new HeadPaddi(), ShopType.HEAD);
		items.put(new HeadStivi(), ShopType.HEAD);
	}

	public static void openInit(CorePlayer player) {
		GUI gui = new GUI(player, "§6Shop §0-> §eShop", 9);

		gui.setButton(0, new ShopInfo.Balance(player.getBukkitPlayer()));

		gui.setButton(1, new GUIButton() {
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

		gui.setButton(2, new GUIButton() {
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

		gui.setButton(3, new GUIButton() {
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

		gui.setButton(5, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.HEAD, player);
			}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.SKULL_ITEM)
						.setDurability((short)3)
						.setName(ShopType.HEAD.getInventoryName())
						.build();
			}
		});

		gui.setButton(7, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.MAGIC, player);
			}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.BLAZE_ROD)
						.setName(ShopType.MAGIC.getInventoryName())
						.build();
			}
		});

		gui.setButton(8, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.SPECIAL, player);
			}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.REDSTONE)
						.setName(ShopType.SPECIAL.getInventoryName())
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

			gui.getInventory().setItem(10, new ItemBuilder(Material.SIGN).setName("§6Coins Sammeln").build());
			gui.getInventory().setItem(19, new ItemBuilder(Material.SIGN).setName("§6Tokens Sammeln").build());
			gui.getInventory().setItem(28, new ItemBuilder(Material.SIGN).setName("§6Kisten Sammeln").build());
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

		if(type == ShopType.MAGIC) {
			gui.setButton(40, new GUIButton() {
				@Override
				public void onClick(InventoryClickEvent arg0) {
					VerwandlungsManager.stopFollow(cplayer.getBukkitPlayer());
				}

				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.BARRIER).setName("§cVerwandlung Aufheben").build();
				}
			});
		}

		gui.setButton(45, new ShopInfo.Balance(cplayer.getBukkitPlayer()));

		gui.setButton(46, new GUIButton() {
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

		gui.setButton(47, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.EFFECT, cplayer);
			}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.POTION)
						.setName("§6Effekte")
						.build();
			}
		});

		gui.setButton(48, new GUIButton() {
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

		gui.setButton(50, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.HEAD, cplayer);
			}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.SKULL_ITEM)
						.setDurability((short)3)
						.setName(ShopType.HEAD.getInventoryName())
						.build();
			}
		});

		gui.setButton(52, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.MAGIC, cplayer);
			}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.BLAZE_ROD)
						.setName(ShopType.MAGIC.getInventoryName())
						.build();
			}
		});

		gui.setButton(53, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.SPECIAL, cplayer);
			}

			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.REDSTONE)
						.setName("§6Ausergewöhnlich")
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
									if((item.getEconomyType() == EconomyType.COINS ? Coins.getCoins(player) : Coins.getToken(player)) >= item.getPrice()) {
										if(!ShopHandler.hasBought(type, cplayer.getUUID(), item)) {
											if(item.canBuying()) {
												if(item.getItemID() != 105) {
													if(item.getEconomyType() == EconomyType.COINS) {
														Coins.action(CoinAction.REMOVE, player, item.getPrice());
													}else {
														Coins.tokenAction(CoinAction.REMOVE, player, item.getPrice());
													}

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
												cplayer.sendMessage("Shop", "§cDieses Item kann nicht gekauft werden!");
											}
										}else {
											item.getAction(player);
										}
									}else {
										if(ShopHandler.hasBought(type, cplayer.getUUID(), item)) {
											item.getAction(player);
										}else {
											cplayer.sendMessage("Shop", "§cBitte überprüfe ob du genügen coins hast.");
										}
									}
								}

								@Override
								public ItemStack getIcon() {
									ItemStack stack = item.getIcon(cplayer);
									ItemMeta meta = stack.getItemMeta();
									
									meta.setDisplayName("§6" + item.getItemName());
									
									if(ShopHandler.hasBought(type, cplayer.getUUID(), item)) {
										ArrayList<String> lore = new ArrayList<>();
										lore.add("");
										lore.add("§aIm Besitzt");
										meta.setLore(lore);
										meta.addEnchant(Enchantment.THORNS, 1, true);
										meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
										stack.setItemMeta(meta);
									}else {
										ArrayList<String> lore = new ArrayList<>();
										String value = "<none>";
										if(item.getEconomyType() == EconomyType.COINS) {
											value = " Coins";
										}else {
											value = " Tokens";
										}
										lore.add("");
										if(item.canBuying()) {
											lore.add("§6Preis: §b" + item.getPrice() + value);
										}else {
											lore.add("§cNicht Kaufbar");
										}
										meta.setLore(lore);
										stack.setItemMeta(meta);
									}
									
									return stack;
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
