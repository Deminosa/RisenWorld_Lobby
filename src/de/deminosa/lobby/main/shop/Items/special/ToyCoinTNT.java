package de.deminosa.lobby.main.shop.Items.special;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.utils.IDManager.IDManager;
import de.deminosa.core.utils.hologramm.Hologram;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.shop.ShopHandler;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import de.deminosa.lobby.main.shop.api.ShopType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	10:55:47 # 27.03.2020
*
*/

public class ToyCoinTNT implements ShopItemBuilder{

	public static ArrayList<String> items = new ArrayList<>();
	public static ArrayList<Location> locs = new ArrayList<>();
	
	@Override
	public int getPrice() {
		return 500;
	}

	@Override
	public String getItemName() {
		return "Coin Bomb";
	}

	@Override
	public void getAction(Player player) {
		Location loc = player.getLocation().clone();
		ShopHandler.removeBought(ShopType.TOY, player.getUniqueId().toString(), this);
		
		player.closeInventory();
		
		for(Player players : Bukkit.getOnlinePlayers()) {
			String ID = IDManager.generateID(8);
			TextComponent message = new TextComponent("§8§l[§b§l!§8§l] §6"+player.getName() + " §7hat eine §bCoinBomb§7 Plaziert!");
			message.setClickEvent(new ClickEvent(Action.RUN_COMMAND, ""));
			players.spigot().sendMessage(message);
		}
		loc.add(0,5,0);
		Hologram holo = new Hologram(loc, "§b§k|||||§6 Coins Bomb §b§k|||||");
		holo.create();
		new BukkitRunnable() {
			@Override
			public void run() {
				loc.add(0,2,0);
				for(int i = 0; i < 250; i++) {
					dropItems(loc, i);
				}
				holo.remove();
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 20*5);
	}

	@Override
	public ItemStack getIcon(CorePlayer player) {
		return new ItemBuilder(Material.CHEST).setName("§6Coin Bomb")
				.addLoreLine("§cNicht Kaufbar!")
				.addLoreLine(" ")
				.addLoreLine("§7Benutze dies um eine Bombe platzen")
				.addLoreLine("§7zu lassen. Jeder kann diese Einsammeln.")
				.addLoreLine("")
				.addLoreLine("§7Besitz " + (ShopHandler.hasBought(ShopType.SPECIAL, player.getUUID(), this) ? "§b1 §7von max §b1" : "§c0 §7von max §b1"))
				.build();
	}

	@Override
	public int getItemID() {
		return 89349099;
	}

	@Override
	public int getSlot() {
		return 11;
	}

	@Override
	public int getItemLevel() {
		return 0;
	}

	@Override
	public boolean canBuying() {
		return false;
	}

	public void dropItems(Location loc, int i) {
		new BukkitRunnable() {
			@Override
			public void run() {
				String name = IDManager.generateID(8);
				items.add(name);
				loc.getWorld().dropItemNaturally(loc, new ItemBuilder(Material.DIAMOND).setName(name).build());
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 2*i);
	}
}
