package de.deminosa.lobby.main.shop.Items.ruestung;

import org.bukkit.Color;
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
*	Create at: 	16:32:09 # 23.02.2020
*
*/

public class ShopArmorLetherGreen {
	
	public static class Helmet implements ShopItemBuilder{

		@Override
		public int getPrice() {return 1500;}

		@Override
		public String getItemName() {return "Leder Helm (Grün)";}

		@Override
		public void getAction(Player player) {
			if(player.getInventory().getHelmet() == null ||
					player.getInventory().getHelmet().getType() == Material.AIR) {
				player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
				player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET)
						.setLeatherArmorColor(Color.GREEN).build());
			}else {
				player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
				player.getInventory().setHelmet(null);
			}
		}

		@Override
		public ItemStack getIcon(CorePlayer player) {
			return new ItemBuilder(Material.LEATHER_HELMET)
					.setName("§6"+getItemName())
					.setLeatherArmorColor(Color.GREEN)
					.addLoreLine("§7Helm")
					.addLoreLine("")
					.addLoreLine(ShopHandler.hasBought(ShopType.ARMOR, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
					.build();
		}

		@Override
		public int getItemID() {return 750470;}

		@Override
		public int getSlot() {return 18;}

		@Override
		public EconomyType getEconomyType() {return EconomyType.COINS;}
		
		@Override
		public boolean canBuying() {return true;}
	}
	
	public static class Chestplate implements ShopItemBuilder{

		@Override
		public int getPrice() {return 1500;}

		@Override
		public String getItemName() {return "Leder Brustplatte (Grün)";}

		@Override
		public void getAction(Player player) {
			if(player.getInventory().getChestplate() == null ||
					player.getInventory().getChestplate().getType() == Material.AIR) {
				player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
				player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE)
						.setLeatherArmorColor(Color.GREEN).build());
			}else {
				player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
				player.getInventory().setChestplate(null);
			}
		}

		@Override
		public ItemStack getIcon(CorePlayer player) {
			return new ItemBuilder(Material.LEATHER_CHESTPLATE)
					.setName("§6"+getItemName())
					.setLeatherArmorColor(Color.GREEN)
					.addLoreLine("§7Brustplatte")
					.addLoreLine("")
					.addLoreLine(ShopHandler.hasBought(ShopType.ARMOR, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
					.build();
		}

		@Override
		public int getItemID() {return 968439;}

		@Override
		public int getSlot() {return 19;}

		@Override
		public EconomyType getEconomyType() {return EconomyType.COINS;}
		
		@Override
		public boolean canBuying() {return true;}
	}
	
	public static class Leggins implements ShopItemBuilder{

		@Override
		public int getPrice() {return 1500;}

		@Override
		public String getItemName() {return "Leder Hose (Grün)";}

		@Override
		public void getAction(Player player) {
			if(player.getInventory().getLeggings() == null ||
					player.getInventory().getLeggings().getType() == Material.AIR) {
				player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
				player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS)
						.setLeatherArmorColor(Color.GREEN).build());
			}else {
				player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
				player.getInventory().setLeggings(null);
			}
		}

		@Override
		public ItemStack getIcon(CorePlayer player) {
			return new ItemBuilder(Material.LEATHER_LEGGINGS)
					.setLeatherArmorColor(Color.GREEN)
					.setName("§6"+getItemName())
					.addLoreLine("§7Hose")
					.addLoreLine("")
					.addLoreLine(ShopHandler.hasBought(ShopType.ARMOR, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
					.build();
		}

		@Override
		public int getItemID() {return 908243;}

		@Override
		public int getSlot() {return 20;}

		@Override
		public EconomyType getEconomyType() {return EconomyType.COINS;}
		
		@Override
		public boolean canBuying() {return true;}
	}
	
	public static class Boots implements ShopItemBuilder{

		@Override
		public int getPrice() {return 1500;}

		@Override
		public String getItemName() {return "Leder Schuhe (Grün)";}

		@Override
		public void getAction(Player player) {
			if(player.getInventory().getBoots() == null ||
					player.getInventory().getBoots().getType() == Material.AIR) {
				player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
				player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS)
						.setLeatherArmorColor(Color.GREEN).build());
			}else {
				player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
				player.getInventory().setBoots(null);
			}
		}

		@Override
		public ItemStack getIcon(CorePlayer player) {
			return new ItemBuilder(Material.LEATHER_BOOTS)
					.setLeatherArmorColor(Color.GREEN)
					.setName("§6"+getItemName())
					.addLoreLine("§7Schuhe")
					.addLoreLine("")
					.addLoreLine(ShopHandler.hasBought(ShopType.ARMOR, player.getUUID(), this) ? "§aIm besitzt" : "§6Preis: §b" + getPrice())
					.build();
		}

		@Override
		public int getItemID() {return 412366;}

		@Override
		public int getSlot() {return 21;}

		@Override
		public EconomyType getEconomyType() {return EconomyType.COINS;}
		
		@Override
		public boolean canBuying() {return true;}
	}
	
}
