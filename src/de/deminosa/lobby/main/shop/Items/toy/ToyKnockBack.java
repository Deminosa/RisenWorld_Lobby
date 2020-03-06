package de.deminosa.lobby.main.shop.Items.toy;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.lobby.main.shop.ShopHandler;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import de.deminosa.lobby.main.shop.api.ShopType;
import de.deminosa.lobby.utils.Utils;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	19:35:49 # 03.03.2020
*
*/

public class ToyKnockBack implements ShopItemBuilder{

	@Override
	public int getPrice() {
		return 300;
	}

	@Override
	public String getItemName() {
		return "KockBack";
	}

	@Override
	public void getAction(Player player) {
		if(player.getInventory().getItem(7).getType() == Material.BARRIER) {
			player.getInventory().setItem(7, new ItemBuilder(Material.STICK).setName("§6KnockBack")
				.addLoreLine("§7Schlage Nervige Spieler weg!")
				.addLoreLine("")
				.addEnchant(Enchantment.KNOCKBACK, 3)
				.build());
		}else {
			player.getInventory().setItem(7, Utils.getTOY());
		}
	}

	@Override
	public ItemStack getIcon(CorePlayer player) {
		return new ItemBuilder(Material.STICK).setName("§6KnockBack")
				.addLoreLine("§7Schlage Nervige Spieler weg!")
				.addLoreLine("")
				.addLoreLine("§cACHTUNG: §7Du kannst nur Spieler hitten,")
				.addLoreLine("§7die das Spielzeug auch Ausgewählt haben!")
				.addLoreLine("§6")
				.addLoreLine(ShopHandler.hasBought(ShopType.TOY, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
				.build();
	}

	@Override
	public int getItemID() {
		return 10;
	}

	@Override
	public int getSlot() {
		return 10;
	}

	@Override
	public int getItemLevel() {
		return 0;
	}

}
