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
import de.deminosa.core.utils.gui.GUI;
import de.deminosa.core.utils.gui.GUIButton;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.core.utils.mysql.ColumType;
import de.deminosa.core.utils.mysql.MySQL;
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
	private static String table = "LobbyShop_", ItemID = "ItemID", buy = "Bought", InUse = "InUse";
	
	public static void init() {
		String[] columName = {"UUID", "ItemID", "Bought", "Amount", "InUse"};
		ColumType[] columType = {ColumType.VARCHAR_128, ColumType.INT, ColumType.INT, ColumType.INT, ColumType.INT};
		
		for(ShopType type : ShopType.values()) {
			MySQL.createTable("LobbyShop_"+type.name(), columName, columType);
		}
		
		initItems();
	}
	
	private static void initItems() {
		
	}
	
	public static void openInit(CorePlayer player) {
		GUI gui = new GUI(player, "§6Shop §0-> §eSelect...", 9);
		
		gui.setButton(0, new ShopInfo.Balance(player.getBukkitPlayer()));

		gui.setButton(1, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.STAINED_GLASS_PANE)
						.setDurability((short)0)
						.setName(" ")
						.build();
			}
		});
		
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
		
		gui.setButton(4, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				//openShop(ShopType.NONE, cplayer);
			}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.BARRIER)
						.setName("§c§lDemnächst")
						.build();
			}
		});
		
		gui.setButton(5, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				//openShop(ShopType.NONE, cplayer);
			}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.BARRIER)
						.setName("§c§lDemnächst")
						.build();
			}
		});
		
		gui.setButton(6, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				//openShop(ShopType.NONE, cplayer);
			}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.BARRIER)
						.setName("§c§lDemnächst")
						.build();
			}
		});
		
		gui.setButton(7, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				//openShop(ShopType.NONE, cplayer);
			}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.BARRIER)
						.setName("§c§lDemnächst")
						.build();
			}
		});
		
		gui.setButton(8, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				//openShop(ShopType.NONE, cplayer);
			}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.BARRIER)
						.setName("§c§lDemnächst")
						.build();
			}
		});
		
		gui.open();
	}
	
	private static void openShop(ShopType type, CorePlayer cplayer) {
		GUI gui = new GUI(cplayer, "§b"+type.getInventoryName());
		for(int i = 45-9; i < 45; i++) {
			gui.setButton(i, new GUIButton() {
				@Override
				public void onClick(InventoryClickEvent arg0) {}
				
				@Override
				public ItemStack getIcon() {
					return new ItemBuilder(Material.STAINED_GLASS_PANE)
							.setDurability((short)0)
							.setName(" ")
							.build();
				}
			});
		}
		gui.setButton(45, new ShopInfo.Balance(cplayer.getBukkitPlayer()));

		gui.setButton(46, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.STAINED_GLASS_PANE)
						.setDurability((short)0)
						.setName(" ")
						.build();
			}
		});
		
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
		
		gui.setButton(49, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				//openShop(ShopType.NONE, cplayer);
			}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.BARRIER)
						.setName("§c§lDemnächst")
						.build();
			}
		});
		
		gui.setButton(50, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				//openShop(ShopType.NONE, cplayer);
			}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.BARRIER)
						.setName("§c§lDemnächst")
						.build();
			}
		});
		
		gui.setButton(51, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				//openShop(ShopType.NONE, cplayer);
			}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.BARRIER)
						.setName("§c§lDemnächst")
						.build();
			}
		});
		
		gui.setButton(52, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				//openShop(ShopType.NONE, cplayer);
			}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.BARRIER)
						.setName("§c§lDemnächst")
						.build();
			}
		});
		
		gui.setButton(53, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				//openShop(ShopType.NONE, cplayer);
			}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.BARRIER)
						.setName("§c§lDemnächst")
						.build();
			}
		});

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
									int freeSlots = 0;
									Player player = (Player)event.getWhoClicked();
//									String uuid = player.getUniqueId().toString();
									if(Coins.getCoins(player) >= item.getPrice()) {
										for(int i = 0; i < player.getInventory().getContents().length; i++) {
											if(player.getInventory().getContents()[i] == null ||
													player.getInventory().getContents()[i].getType() == Material.AIR) {
												freeSlots++;
											}
										}
										if(freeSlots > 0) {
												//if() {
													Coins.action(CoinAction.REMOVE, player, item.getPrice());
													
													cplayer.sendMessage("Shop", "§aVielen dank für ihren Einkauf! §8(§7"+item.getIcon().getItemMeta().getDisplayName()+"§8)");
													cplayer.playsound(Sound.ORB_PICKUP);
												//}
										}else {
											cplayer.sendMessage("Shop", "§cSorry, du hast aktuell kein Platz im Inventar.");
										}
									}else {
										cplayer.sendMessage("Shop", "§cBitte überprüfe ob du genügen coins hast und das dein Level geeignet  ist.");
									}
								}

								@Override
								public ItemStack getIcon() {
									return item.getIcon();
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
