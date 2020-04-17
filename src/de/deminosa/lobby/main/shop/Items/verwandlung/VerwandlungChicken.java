package de.deminosa.lobby.main.shop.Items.verwandlung;

import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.lobby.main.shop.ShopHandler;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import de.deminosa.lobby.main.shop.api.ShopType;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:52:07 # 12.04.2020
*
*/

public class VerwandlungChicken implements ShopItemBuilder{

	@Override
	public int getPrice() {
		return 15850;
	}

	@Override
	public String getItemName() {
		return "Huhn";
	}

	@Override
	public void getAction(Player player) {
		LivingEntity entity = player.getWorld().spawn(player.getLocation(), Chicken.class);
		
		VerwandlungsManager.follow(player, entity, 2.5d);
	}

	@Override
	public ItemStack getIcon(CorePlayer player) {
		return new ItemBuilder(Material.MONSTER_EGG).setDurability((short)93)
				.setName("§6"+getItemName())
				.addLoreLine("")
				.addLoreLine(ShopHandler.hasBought(ShopType.MAGIC, player.getUUID(), this) ? "§aIm besitzt" : "§cNicht Kaufbar")
				.build();
	}

	@Override
	public int getItemID() {
		return 8226014;
	}

	@Override
	public int getSlot() {
		return 12;
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
