package de.deminosa.lobby.main.shop.Items.toy;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.lobby.main.shop.ShopHandler;
import de.deminosa.lobby.main.shop.api.EconomyType;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import de.deminosa.lobby.main.shop.api.ShopType;
import de.deminosa.lobby.utils.Utils;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	14:03:23 # 19.04.2020
*
*/

public class ToyTnt implements ShopItemBuilder{

	@Override
	public int getPrice() {
		return 2300;
	}

	@Override
	public String getItemName() {
		return "TNT";
	}

	@Override
	public void getAction(Player player) {
		if(player.getInventory().getItem(7).getType() == Material.BARRIER) {
			player.getInventory().setItem(7, new ItemBuilder(Material.TNT).setName("§6TNT")
				.addLoreLine("")
				.build());
		}else {
			player.getInventory().setItem(7, Utils.getTOY());
		}
	}

	@Override
	public ItemStack getIcon(CorePlayer player) {
		return new ItemBuilder(Material.TNT).setName("§6TNT")
				.addLoreLine("§7Zerstöre die Lobby und")
				.addLoreLine("§7hinterlasse ein zeichen der")
				.addLoreLine("§7verwüstung.")
				.addLoreLine("")
				.addLoreLine(ShopHandler.hasBought(ShopType.TOY, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
				.build();
	}

	@Override
	public int getItemID() {
		return 89280832;
	}

	@Override
	public int getSlot() {
		return 12;
	}

	@Override
	public EconomyType getEconomyType() {return EconomyType.COINS;}
	
	@Override
	public boolean canBuying() {return true;}

}
