package de.deminosa.lobby.main.shop.Items.effecte;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.cache.CorePlayerData;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.lobby.main.shop.api.EconomyType;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	19:02:13 # 15.03.2020
*
*/

public class EffectAngry implements ShopItemBuilder{

	@Override
	public int getPrice() {
		return 2300;
	}

	@Override
	public String getItemName() {
		return "W�tend";
	}

	@Override
	public void getAction(Player player) {
		CorePlayerData.setData(CoreCache.getCorePlayer(player), "lobby", "effect", EnumParticle.VILLAGER_ANGRY.name());
	}

	@Override
	public ItemStack getIcon(CorePlayer player) {
		return new ItemBuilder(Material.STAINED_GLASS_PANE)
				.setDurability((short)14)
				.build();
	}

	@Override
	public int getItemID() {
		return 1367;
	}

	@Override
	public int getSlot() {
		return 16;
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
