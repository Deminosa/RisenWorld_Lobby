package de.deminosa.lobby.main.shop.Items.ruestung;

import org.bukkit.Material;
import org.bukkit.Sound;
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
*	Create at: 	16:32:09 # 23.02.2020
*
*/

public class ShopArmorLether {

	private static int uid = 100;
	private static int move = 1;
	
	public static boolean hasAllLether(Player player) {
		if(ShopHandler.hasBought(ShopType.ARMOR, player.getUniqueId(), new Helmet()) &&
				ShopHandler.hasBought(ShopType.ARMOR, player.getUniqueId(), new Chestplate()) &&
				ShopHandler.hasBought(ShopType.ARMOR, player.getUniqueId(), new Leggins()) &&
				ShopHandler.hasBought(ShopType.ARMOR, player.getUniqueId(), new Boots())) {
			return true;
		}
		return false;
	}
	
	public static class Helmet implements ShopItemBuilder{

		@Override
		public int getPrice() {return 75;}

		@Override
		public String getItemName() {return "Leder Rüstung";}

		@Override
		public void getAction(Player player) {
			if(player.getInventory().getHelmet() == null ||
					player.getInventory().getHelmet().getType() == Material.AIR) {
				player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
				player.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
			}else {
				player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
				player.getInventory().setHelmet(null);
			}
		}

		@Override
		public ItemStack getIcon(CorePlayer player) {
			return new ItemBuilder(Material.LEATHER_HELMET)
					.setName("§6"+getItemName())
					.addLoreLine("§7Helm")
					.addLoreLine("")
					.addLoreLine(ShopHandler.hasBought(ShopType.ARMOR, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
					.build();
		}

		@Override
		public int getItemID() {return uid+1;}

		@Override
		public int getSlot() {return 0+move;}

		@Override
		public int getItemLevel() {return 0;}
		
	}
	
	public static class Chestplate implements ShopItemBuilder{

		@Override
		public int getPrice() {return 75;}

		@Override
		public String getItemName() {return "Leder Rüstung";}

		@Override
		public void getAction(Player player) {
			if(player.getInventory().getChestplate() == null ||
					player.getInventory().getChestplate().getType() == Material.AIR) {
				player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
				player.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
			}else {
				player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
				player.getInventory().setChestplate(null);
			}
		}

		@Override
		public ItemStack getIcon(CorePlayer player) {
			return new ItemBuilder(Material.LEATHER_CHESTPLATE)
					.setName("§6"+getItemName())
					.addLoreLine("§7Brustplatte")
					.addLoreLine("")
					.addLoreLine(ShopHandler.hasBought(ShopType.ARMOR, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
					.build();
		}

		@Override
		public int getItemID() {return uid+2;}

		@Override
		public int getSlot() {return 9+move;}

		@Override
		public int getItemLevel() {return 0;}
		
	}
	
	public static class Leggins implements ShopItemBuilder{

		@Override
		public int getPrice() {return 75;}

		@Override
		public String getItemName() {return "Leder Rüstung";}

		@Override
		public void getAction(Player player) {
			if(player.getInventory().getLeggings() == null ||
					player.getInventory().getLeggings().getType() == Material.AIR) {
				player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
				player.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
			}else {
				player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
				player.getInventory().setLeggings(null);
			}
		}

		@Override
		public ItemStack getIcon(CorePlayer player) {
			return new ItemBuilder(Material.LEATHER_LEGGINGS)
					.setName("§6"+getItemName())
					.addLoreLine("§7Hose")
					.addLoreLine("")
					.addLoreLine(ShopHandler.hasBought(ShopType.ARMOR, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
					.build();
		}

		@Override
		public int getItemID() {return uid+3;}

		@Override
		public int getSlot() {return 18+move;}

		@Override
		public int getItemLevel() {return 0;}
		
	}
	
	public static class Boots implements ShopItemBuilder{

		@Override
		public int getPrice() {return 75;}

		@Override
		public String getItemName() {return "Leder Rüstung";}

		@Override
		public void getAction(Player player) {
			if(player.getInventory().getBoots() == null ||
					player.getInventory().getBoots().getType() == Material.AIR) {
				player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
				player.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
			}else {
				player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
				player.getInventory().setBoots(null);
			}
		}

		@Override
		public ItemStack getIcon(CorePlayer player) {
			return new ItemBuilder(Material.LEATHER_BOOTS)
					.setName("§6"+getItemName())
					.addLoreLine("§7Schuhe")
					.addLoreLine("")
					.addLoreLine(ShopHandler.hasBought(ShopType.ARMOR, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
					.build();
		}

		@Override
		public int getItemID() {return uid+4;}

		@Override
		public int getSlot() {return 27+move;}

		@Override
		public int getItemLevel() {return 0;}
		
	}
	
}
