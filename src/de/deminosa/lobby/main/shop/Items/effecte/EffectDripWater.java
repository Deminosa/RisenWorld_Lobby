package de.deminosa.lobby.main.shop.Items.effecte;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.cache.CorePlayerData;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.lobby.main.shop.ShopHandler;
import de.deminosa.lobby.main.shop.api.EconomyType;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import de.deminosa.lobby.main.shop.api.ShopType;
import net.minecraft.server.v1_8_R3.EnumParticle;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	19:02:13 # 15.03.2020
*
*/

public class EffectDripWater implements ShopItemBuilder{

	@Override
	public int getPrice() {
		return 400;
	}

	@Override
	public String getItemName() {
		return "Drip Wasser";
	}

	@Override
	public void getAction(Player player) {
		CorePlayerData.setData(CoreCache.getCorePlayer(player), "lobby", "effect", EnumParticle.DRIP_WATER.name());
	}

	@Override
	public ItemStack getIcon(CorePlayer player) {
		return new ItemBuilder(Material.WATER_BUCKET).setName("�6"+getItemName())
				.addLoreLine("")
				.addLoreLine(ShopHandler.hasBought(ShopType.EFFECT, player.getUUID(), this) ? "�aIm besitzt" : "�6Preis: �b" + getPrice())
				.build();
	}

	@Override
	public int getItemID() {
		return 24664386;
	}

	@Override
	public int getSlot() {
		return 22;
	}

	@Override
	public boolean canBuying() {
		return true;
	}

	@Override
	public EconomyType getEconomyType() {
		return EconomyType.COINS;
	}
}
