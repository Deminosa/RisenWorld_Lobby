package de.deminosa.lobby.main.agb;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.cache.CorePlayerData;
import de.deminosa.core.utils.gui.GUI;
import de.deminosa.core.utils.gui.GUIButton;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.core.utils.mysql.MySQL;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	17:04:57 # 22.10.2019
 *
 */

public class AGB {

	public static void open(Player player) {

		CorePlayer cplayer = CoreCache.getCorePlayer(player);

		CorePlayerData.setData(cplayer, "AGB", "allow", 0);

		if((int)CorePlayerData.loadData(cplayer, "AGB", "allow") == 0) {
			if(!MySQL.exsistValue("AGB", "UUID", player.getUniqueId().toString(), "UUID")) {
				MySQL.set("AGB", "UUID", player.getUniqueId().toString());
				MySQL.update("AGB", "allow", "0", "UUID", player.getUniqueId().toString());
			}else if(MySQL.getString("AGB", "UUID", player.getUniqueId().toString(), "allow").equals("0")){
				GUI gui = new GUI(CoreCache.getCorePlayer(player), "§cNutzungsbedingung", 9);

				gui.setButton(0, new GUIButton() {
					@Override
					public void onClick(InventoryClickEvent arg0) {
						player.closeInventory();
						player.sendMessage("§7Wird bestätigt.....");
						MySQL.update("AGB", "allow", "1", "UUID", player.getUniqueId().toString());
						CorePlayerData.setData(cplayer, "AGP", "allow", 1);
						player.sendMessage("§aVielen dank das du unsere Nutzungsbedingungen akzeptierst!");
					}

					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte)5)
								.setName("§a§lNutzungsbedingungen akzeptieren")
								.build();
					}
				});

				gui.setButton(4, new GUIButton() {
					@Override
					public void onClick(InventoryClickEvent arg0) {}

					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.BOOK_AND_QUILL)
								.setName("§6Nutzungsbedingungen")
								.addLoreLine("§7Mit dem Klick auf der §aGrüne Glassplatte §7(Links)")
								.addLoreLine("§6akzeptierst §7du unsere §6Regeln §7und die §6AGB's")
								.addLoreLine("§7Wenn du damit §c§lnicht §7einverstanden bist,")
								.addLoreLine("§7Dann klicke bitte auf die §cRote Glassplatte §7(Rechts)")
								.addLoreLine("")
								.addLoreLine("§7URL: §e§nhttps://risenworld.de/agbs")
								.build();
					}
				});

				gui.setButton(8, new GUIButton() {
					@Override
					public void onClick(InventoryClickEvent arg0) {
						player.kickPlayer("§bEmmy §8| §7Du wurdest vom Server geworfen! \n"
								+ "\n"
								+ "§cGrund §7§oDie Nutzungsbedingungen wurden Abgelehnt!");
					}

					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte)14)
								.setName("§c§lNutzungsbedingungen ablehnen")
								.build();
					}
				});
				
				gui.open();
			}else {
				CorePlayerData.setData(cplayer, "AGP", "allow", 1);
			}
		}
	}

}
