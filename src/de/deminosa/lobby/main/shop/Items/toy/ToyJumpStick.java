package de.deminosa.lobby.main.shop.Items.toy;

import org.bukkit.Material;
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
*	Create at: 	16:50:57 # 06.03.2020
*
*/

public class ToyJumpStick implements ShopItemBuilder{

	@Override
	public int getPrice() {
		return 600;
	}

	@Override
	public String getItemName() {
		return "Jump Stick";
	}

	@Override
	public void getAction(Player player) {
		if(player.getInventory().getItem(7).getType() == Material.BARRIER) {
			player.getInventory().setItem(7, new ItemBuilder(Material.BLAZE_ROD).setName("§6Jump")
				.build());
		}else {
			player.getInventory().setItem(7, Utils.getTOY());
		}
	}

	@Override
	public ItemStack getIcon(CorePlayer player) {
		return new ItemBuilder(Material.BLAZE_ROD).setName("§6Jump Stick")
				.addLoreLine("§7Rechtsklick auf Item und Springe in die Luft!")
				.addLoreLine("")
				.addLoreLine(ShopHandler.hasBought(ShopType.TOY, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
				.build();
	}

	@Override
	public int getItemID() {
		return 84285637;
	}

	@Override
	public int getSlot() {
		return 11;
	}

	@Override
	public EconomyType getEconomyType() {return EconomyType.COINS;}
	
	@Override
	public boolean canBuying() {return true;}

}
