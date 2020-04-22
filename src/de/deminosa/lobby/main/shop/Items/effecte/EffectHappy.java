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

public class EffectHappy implements ShopItemBuilder{

	@Override
	public int getPrice() {
		return 2300;
	}

	@Override
	public String getItemName() {
		return "Fröhlich";
	}

	@Override
	public void getAction(Player player) {
		CorePlayerData.setData(CoreCache.getCorePlayer(player), "lobby", "effect", EnumParticle.VILLAGER_HAPPY.name());
	}

	@Override
	public ItemStack getIcon(CorePlayer player) {
		return new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§6"+getItemName())
				.setDurability((short)13)
				.addLoreLine("")
				.addLoreLine(ShopHandler.hasBought(ShopType.EFFECT, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
				.build();
	}

	@Override
	public int getItemID() {
		return 5743;
	}

	@Override
	public int getSlot() {
		return 15;
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
