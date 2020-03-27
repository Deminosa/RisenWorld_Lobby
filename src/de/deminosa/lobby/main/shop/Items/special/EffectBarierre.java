package de.deminosa.lobby.main.shop.Items.special;

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

public class EffectBarierre implements ShopItemBuilder{

	@Override
	public int getPrice() {
		return 10000;
	}

	@Override
	public String getItemName() {
		return "Barrieren";
	}

	@Override
	public void getAction(Player player) {
		CorePlayerData.setData(CoreCache.getCorePlayer(player), "lobby", "effect", EFFECT_TYPE.Barierren.name());
	}

	@Override
	public ItemStack getIcon(CorePlayer player) {
		return new ItemBuilder(Material.BARRIER).setName("§6Barrieren")
				.addLoreLine("")
				.addLoreLine(ShopHandler.hasBought(ShopType.SPECIAL, player.getUUID(), this) ? "§aIm besitzt" : "§cNicht Kaufbar")
				.build();
	}

	@Override
	public int getItemID() {
		return 73209035;
	}

	@Override
	public int getSlot() {
		return 10;
	}

	@Override
	public int getItemLevel() {
		return 0;
	}

	@Override
	public boolean canBuying() {
		return false;
	}

}
