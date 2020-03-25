package de.deminosa.lobby.main.shop.Items.effecte;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.cache.CorePlayerData;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.lobby.main.shop.ShopHandler;
import de.deminosa.lobby.main.shop.Items.effecte.api.EFFECT_TYPE;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import de.deminosa.lobby.main.shop.api.ShopType;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	19:02:13 # 15.03.2020
*
*/

public class EffectHerz implements ShopItemBuilder{

	@Override
	public int getPrice() {
		return 1500;
	}

	@Override
	public String getItemName() {
		return "Herzen";
	}

	@Override
	public void getAction(Player player) {
		CorePlayerData.setData(CoreCache.getCorePlayer(player), "lobby", "effect", EFFECT_TYPE.Herz.name());
	}

	@Override
	public ItemStack getIcon(CorePlayer player) {
		return new ItemBuilder(Material.REDSTONE).setName("§6Herzen")
				.addLoreLine("")
				.addLoreLine(ShopHandler.hasBought(ShopType.EFFECT, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
				.build();
	}

	@Override
	public int getItemID() {
		return 41812978;
	}

	@Override
	public int getSlot() {
		return 11;
	}

	@Override
	public int getItemLevel() {
		return 0;
	}

	@Override
	public boolean canBuying() {
		return true;
	}
}
