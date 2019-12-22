package de.deminosa.lobby.main.shop;

import java.util.HashMap;

import org.apache.commons.codec.language.bm.Lang;
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
	
	public static void init(CorePlayer player) {
		GUI gui = new GUI(player, "§6Shop §0-> §eSelect...", 9);
		
		gui.setButton(0, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.NONE, player);
			}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.LEATHER_CHESTPLATE)
						.setName("§6Rüstung")
						.build();
			}
		});
		
		gui.setButton(0, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				openShop(ShopType.NONE, player);
			}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.POTION)
						.setName("§6Effecte")
						.build();
			}
		});
		
		gui.open();
	}
	
	private static void openShop(ShopType type, CorePlayer cplayer) {
		GUI gui = new GUI(cplayer, "§b"+type.getInventoryName());
		gui.setButton(45, new ShopInfo.Balance(cplayer.getBukkitPlayer()));

		gui.setButton(18, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent event) {
				openShop(ShopType.ARMOR, cplayer);
			}

			@Override
			public ItemStack getIcon() {
				if(type == ShopType.ARMOR) {
					return new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte)5)
							.setName("§6Armor").addLoreLine("§cDE §7§oRüstung").build();
				}
				return new ItemBuilder(Material.IRON_CHESTPLATE)
						.setName("§6Armor").addLoreLine("§cDE §7§oRüstung").build();
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
												Coins.action(CoinAction.REMOVE, player, item.getPrice());
												player.getInventory().addItem(item.getItem(player));
												cplayer.sendMessage("Shop", "§aVielen dank für ihren Einkauf! §8(§7"+item.getIcon().getItemMeta().getDisplayName()+"§8)");
												cplayer.playsound(Sound.ORB_PICKUP);
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
