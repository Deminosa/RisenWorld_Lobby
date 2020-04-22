package de.deminosa.lobby.main.shop.Items.heads.emoji;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.lobby.main.shop.ShopHandler;
import de.deminosa.lobby.main.shop.api.EconomyType;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import de.deminosa.lobby.main.shop.api.ShopType;
import de.deminosa.lobby.utils.rocket.SkullTexturBuilder;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	10:04:18 # 19.04.2020
*
*/

public class HeadCrying implements ShopItemBuilder{

	String value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzMyZmUxMjFhNjNlYWFiZDk5Y2VkNmQxYWNjOTE3OTg2NTJkMWVlODA4NGQyZjkxMjdkOGEzMTVjYWQ1Y2U0In19fQ==";
	
	@Override
	public int getPrice() {
		return 6900;
	}

	@Override
	public String getItemName() {
		return "Weinend (Emotion)";
	}

	@Override
	public void getAction(Player player) {
		if(player.getInventory().getHelmet() == null ||
				player.getInventory().getHelmet().getType() == Material.AIR) {
			player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
			player.getInventory().setHelmet(SkullTexturBuilder.createCustomHead(value));
		}else {
			player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
			player.getInventory().setHelmet(null);
		}
	}

	@Override
	public ItemStack getIcon(CorePlayer player) {
		ItemStack item = SkullTexturBuilder.createCustomHead(value);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§6" + getItemName());
		ArrayList<String> list = new ArrayList<>();
		list.add("");
		list.add(ShopHandler.hasBought(ShopType.HEAD, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice());
		meta.setLore(list);
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public int getItemID() {
		return 29110062;
	}

	@Override
	public int getSlot() {
		return 25;
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
