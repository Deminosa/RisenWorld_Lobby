package de.deminosa.lobby.main.shop.Items.pets;

import org.bukkit.Material;
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
*	Create at: 	13:50:18 # 16.03.2020
*
*/

public class PetPig implements ShopItemBuilder{

	@Override
	public int getPrice() {
		return 9850;
	}

	@Override
	public String getItemName() {
		return "Schwein";
	}

	@Override
	public void getAction(Player player) {
		PetUitls.stopFollow(player);
		LivingEntity entity = player.getWorld().spawn(player.getLocation(), Pig.class);
		entity.setCustomNameVisible(true);
		entity.setCustomName("§6" + player.getName() + "'s §7Haustier");
		
		Pig animal = (Pig) entity;
		animal.setBaby();
		
		PetUitls.followPlayer(player, entity, 1.75, "c", 2, 5);
	}

	@Override
	public ItemStack getIcon(CorePlayer player) {
		return new ItemBuilder(Material.MONSTER_EGG).setDurability((short)90)
				.setName("§6Schwein")
				.addLoreLine(ShopHandler.hasBought(ShopType.PET, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
				.addLoreLine("")
				.addLoreLine("§7Fähigkeit: §6Sammelt Coins")
				.addLoreLine("§7Chance: §b2%")
				.addLoreLine("§7Max: §b5 Coins")
				.build();
	}

	@Override
	public int getItemID() {
		return 90426389;
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
		return true;
	}
}
