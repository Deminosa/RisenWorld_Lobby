package de.deminosa.lobby.main.shop.Items.verwandlung;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.inventory.ItemStack;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.lobby.main.shop.ShopHandler;
import de.deminosa.lobby.main.shop.api.EconomyType;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import de.deminosa.lobby.main.shop.api.ShopType;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:52:07 # 12.04.2020
*
*/

public class VerwandlungSheep implements ShopItemBuilder{

	@Override
	public int getPrice() {
		return 200;
	}

	@Override
	public String getItemName() {
		return "Schaf";
	}

	@Override
	public void getAction(Player player) {
		LivingEntity entity = player.getWorld().spawn(player.getLocation(), Sheep.class);
		VerwandlungsManager.stopFollow(player);
		
		VerwandlungsManager.follow(player, entity, 2.5d);
	}

	@Override
	public ItemStack getIcon(CorePlayer player) {
		return new ItemBuilder(Material.MONSTER_EGG).setDurability((short)91)
				.setName("�6"+getItemName())
				.build();
	}

	@Override
	public int getItemID() {
		return 3065268;
	}

	@Override
	public int getSlot() {
		return 15;
	}

	@Override
	public EconomyType getEconomyType() {return EconomyType.TOKENS;}

	@Override
	public boolean canBuying() {
		return false;
	}

}
