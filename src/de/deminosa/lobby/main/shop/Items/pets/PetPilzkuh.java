package de.deminosa.lobby.main.shop.Items.pets;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Player;
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
*	Create at: 	13:50:18 # 16.03.2020
*
*/

public class PetPilzkuh implements ShopItemBuilder{

	@Override
	public int getPrice() {
		return 9850;
	}

	@Override
	public String getItemName() {
		return "PilzKuh";
	}

	@Override
	public void getAction(Player player) {
		PetUitls.stopFollow(player);
		LivingEntity entity = player.getWorld().spawn(player.getLocation(), MushroomCow.class);
		entity.setCustomNameVisible(true);
		entity.setCustomName("§6" + player.getName() + "'s §7Haustier");
		
		MushroomCow animal = (MushroomCow) entity;
		animal.setBaby();
		
		PetUitls.followPlayer(player, entity, 1.75, "a", 10, 2);
	}

	@Override
	public ItemStack getIcon(CorePlayer player) {
		return new ItemBuilder(Material.MONSTER_EGG).setDurability((short)96)
				.setName("§6PilzKuh")
				.addLoreLine(ShopHandler.hasBought(ShopType.PET, player.getUUID(), this) ? "§aIm besitzt" : "§cNicht Kaufbar")
				.addLoreLine("")
				.addLoreLine("§7Fähigkeit: §6Sammelt Kisten")
				.addLoreLine("§7Chance: §b10%")
				.addLoreLine("§7Max: §b2 Kisten")
				.build();
	}

	@Override
	public int getItemID() {
		return 39347047;
	}

	@Override
	public int getSlot() {
		return 30;
	}

	@Override
	public boolean canBuying() {
		return false;
	}

	@Override
	public EconomyType getEconomyType() {
		return EconomyType.COINS;
	}
}
