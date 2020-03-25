package de.deminosa.lobby.main.lotto;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.deminosa.coinmanager.Coins;
import de.deminosa.coinmanager.command.LottoCommand.LottoAction;
import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.utils.gui.GUI;
import de.deminosa.core.utils.gui.GUIButton;
import de.deminosa.core.utils.itembuilder.ItemBuilder;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:22:08 # 06.03.2020
*
*/

public class LottoQuestion {

	public static void open(CorePlayer player) {
		GUI gui = new GUI(player, "§6Frage!", 9);
		
		gui.setButton(1, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				if(Coins.hasEnoughLotto(player.getBukkitPlayer(), 1)) {
					Coins.lottoAction(LottoAction.REMOVE, player.getBukkitPlayer(), 1);
					new LottoInventory(player);
				}else {
					player.getBukkitPlayer().closeInventory();
					player.sendMessage("Lotto", "§cDu hast nicht genügen Lottoscheine!");
				}
			}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short)5)
						.setName("§aJa")
						.addLoreLine("")
						.addLoreLine("§7Du besitz §b" + Coins.getLotto(player.getBukkitPlayer()) + "/1 §7Lottoscheine!")
						.build();
			}
		});
		
		gui.setButton(4, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
			}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.SIGN)
						.setName("§6Frage")
						.addLoreLine("")
						.addLoreLine("§7Möchtest du 1 Lottoschein verwenden?")
						.build();
			}
		});
		
		gui.setButton(7, new GUIButton() {
			@Override
			public void onClick(InventoryClickEvent arg0) {
				player.getBukkitPlayer().closeInventory();
			}
			
			@Override
			public ItemStack getIcon() {
				return new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short)14)
						.setName("§cNein")
						.addLoreLine("")
						.addLoreLine("§7Das Inventar wird geschlossen!")
						.build();
			}
		});
		
		gui.open();
	}
	
}
