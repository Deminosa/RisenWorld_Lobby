package de.deminosa.lobby.main.shop.Items.special;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.shop.ShopHandler;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorLether;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorLetherRainbowTimer;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import de.deminosa.lobby.main.shop.api.ShopType;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	19:04:47 # 23.02.2020
 *
 */

public class ShopArmorLetherRainbow implements ShopItemBuilder{

	private static int uid = 105;
	ArrayList<String> players = new ArrayList<String>();

	@Override
	public int getPrice() {
		return 10000;
	}

	@Override
	public String getItemName() {
		return "Regenbogen Rüstung";
	}

	@Override
	public void getAction(Player player) {
		if(ShopArmorLether.hasAllLether(player)) {
			if(!players.contains(player.getName())) {
				players.add(player.getName());
				int b = 0;int g = 0;int r = 255;

				int first = 0;
				int second = 0;
				int third = 0;
				int fourth = 0;
				int fifth = 0;
				int sixth = 0;

				ShopArmorLetherRainbowTimer armor = new ShopArmorLetherRainbowTimer(this.players, player, b, g, r, first, second, third, fourth, fifth, sixth);

				armor.runTaskTimer(RisenWorld_Lobby.getInstance(), 0L, 1L);
				player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
			}else {
				players.remove(player.getName());
				player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
			}
		}
	}

	@Override
	public ItemStack getIcon(CorePlayer player) {
		if(!ShopArmorLether.hasAllLether(player.getBukkitPlayer())) {
			return new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short)15)
					.setName("§6")
					.addLoreLine("")
					.build();
		}
		return new ItemBuilder(Material.NETHER_STAR)
				.setName("§6"+getItemName())
				.addLoreLine("§7Regenbogen Rüstung. Leder Rüstung.")
				.addLoreLine("")
				.addLoreLine(ShopHandler.hasBought(ShopType.SPECIAL, player.getUUID(), this) ? "§aIm besitzt" : "§cNicht Kaufbar")
				.build();
	}

	@Override
	public int getItemID() {
		return uid;
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
	public boolean canBuying() {return false;}

}
