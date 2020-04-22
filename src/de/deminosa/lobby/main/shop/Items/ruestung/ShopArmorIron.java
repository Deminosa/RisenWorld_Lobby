package de.deminosa.lobby.main.shop.Items.ruestung;

import org.bukkit.Material;
import org.bukkit.Sound;
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
*	Create at: 	16:32:55 # 23.02.2020
*
*/

public class ShopArmorIron {

	private static int uid = 200;
	
	public static class Helmet implements ShopItemBuilder{

		@Override
		public int getPrice() {return 3250;}

		@Override
		public String getItemName() {return "Eisen Helm";}

		@Override
		public void getAction(Player player) {
			if(player.getInventory().getHelmet() == null ||
					player.getInventory().getHelmet().getType() == Material.AIR) {
				player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
				player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
			}else {
				player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
				player.getInventory().setHelmet(null);
			}
		}

		@Override
		public ItemStack getIcon(CorePlayer player) {
			return new ItemBuilder(Material.IRON_HELMET)
					.setName("§6"+getItemName())
					.addLoreLine("§7Helm")
					.addLoreLine("")
					.addLoreLine(ShopHandler.hasBought(ShopType.ARMOR, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
					.build();
		}

		@Override
		public int getItemID() {return uid+1;}

		@Override
		public int getSlot() {return 14;}

		@Override
		public EconomyType getEconomyType() {return EconomyType.COINS;}
		
		@Override
		public boolean canBuying() {return true;}
	}
	
	public static class Chestplate implements ShopItemBuilder{

		@Override
		public int getPrice() {return 3250;}

		@Override
		public String getItemName() {return "Eisen Brustplatte";}

		@Override
		public void getAction(Player player) {
			if(player.getInventory().getChestplate() == null ||
					player.getInventory().getChestplate().getType() == Material.AIR) {
				player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
				player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			}else {
				player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
				player.getInventory().setChestplate(null);
			}
		}

		@Override
		public ItemStack getIcon(CorePlayer player) {
			return new ItemBuilder(Material.IRON_CHESTPLATE)
					.setName("§6"+getItemName())
					.addLoreLine("§7Brustplatte")
					.addLoreLine("")
					.addLoreLine(ShopHandler.hasBought(ShopType.ARMOR, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
					.build();
		}

		@Override
		public int getItemID() {return uid+2;}

		@Override
		public int getSlot() {return 15;}

		@Override
		public EconomyType getEconomyType() {return EconomyType.COINS;}
		
		@Override
		public boolean canBuying() {return true;}
	}
	
	public static class Leggins implements ShopItemBuilder{

		@Override
		public int getPrice() {return 3250;}

		@Override
		public String getItemName() {return "Eisen Hose";}

		@Override
		public void getAction(Player player) {
			if(player.getInventory().getLeggings() == null ||
					player.getInventory().getLeggings().getType() == Material.AIR) {
				player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
				player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			}else {
				player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
				player.getInventory().setLeggings(null);
			}
		}

		@Override
		public ItemStack getIcon(CorePlayer player) {
			return new ItemBuilder(Material.IRON_LEGGINGS)
					.setName("§6"+getItemName())
					.addLoreLine("§7Hose")
					.addLoreLine("")
					.addLoreLine(ShopHandler.hasBought(ShopType.ARMOR, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
					.build();
		}

		@Override
		public int getItemID() {return uid+3;}

		@Override
		public int getSlot() {return 16;}

		@Override
		public EconomyType getEconomyType() {return EconomyType.COINS;}
		
		@Override
		public boolean canBuying() {return true;}
	}
	
	public static class Boots implements ShopItemBuilder{

		@Override
		public int getPrice() {return 3250;}

		@Override
		public String getItemName() {return "Eisen Schuhe";}

		@Override
		public void getAction(Player player) {
			if(player.getInventory().getBoots() == null ||
					player.getInventory().getBoots().getType() == Material.AIR) {
				player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
				player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
			}else {
				player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
				player.getInventory().setBoots(null);
			}
		}

		@Override
		public ItemStack getIcon(CorePlayer player) {
			return new ItemBuilder(Material.IRON_BOOTS)
					.setName("§6"+getItemName())
					.addLoreLine("§7Schuhe")
					.addLoreLine("")
					.addLoreLine(ShopHandler.hasBought(ShopType.ARMOR, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
					.build();
		}

		@Override
		public int getItemID() {return uid+4;}

		@Override
		public int getSlot() {return 17;}

		@Override
		public EconomyType getEconomyType() {return EconomyType.COINS;}
		
		@Override
		public boolean canBuying() {return true;}
	}
	
}
