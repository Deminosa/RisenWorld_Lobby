package de.deminosa.lobby.main.reedem;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.deminosa.coinmanager.Coins;
import de.deminosa.coinmanager.command.CoinsCommand.CoinAction;
import de.deminosa.coinmanager.command.LottoCommand.LottoAction;
import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.IDManager.IDManager;
import de.deminosa.core.utils.mysql.MySQL;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.shop.Shop;
import de.deminosa.lobby.main.shop.ShopHandler;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import de.deminosa.lobby.main.shop.api.ShopType;
import de.deminosa.lobby.utils.ChatAPI;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	15:12:26 # 11.04.2020
*
*/

public class ReedemHandler {

	public static void create(CorePlayer player, ReedemType reedemType, int value) {
		String ID = IDManager.generateIDWithSlpit(8, 4);
		String type = reedemType.toString();
		int v = value;
		
		if(!isExsist(ID)) {
			MySQL.set("Reedem", "UID", ID);
			MySQL.update("Reedem", "type", type, "UID", ID);
			if(reedemType != ReedemType.SHOP_RANDOM) {
				MySQL.update("Reedem", "args", ""+v, "UID", ID);
			}else {
				MySQL.update("Reedem", "args", "-1", "UID", ID);
			}
			player.sendMessage("Redeem", "Dein Geschenk-Code wurde erstellt!");
			ChatAPI.send(player.getBukkitPlayer(), 
					ChatAPI.sendMessage("§9Redeem §8| "),
					ChatAPI.SuggestCommand("§8[§bID§8]", ID, "§7Klicke um die ID im Chat Anzuzeigen!"));
		}else {
			player.sendMessage("Reedem", "§cERROR: §7ID exsist!");
		}
	}
	
	@SuppressWarnings("incomplete-switch")
	public static void use(CorePlayer player, String ID) {
		if(isExsist(ID)) {
			ReedemType type = ReedemType.valueOf(MySQL.getString("Reedem", "UID", ID, "type"));
			int value = MySQL.getInt("Reedem", "args", "UID", ID);
			
			MySQL.deleteRow("Reedem", "UID", ID);
			
			if(type != ReedemType.SHOP_RANDOM) {
				switch(type) {
				case CHEST:
					Coins.chestAction(CoinAction.ADD, player.getBukkitPlayer(), value);
					player.sendMessage("Redeem", "Du hast §b+"+value+" Kisten §7erhalten!");
					break;
				case COINS:
					Coins.action(CoinAction.ADD, player.getBukkitPlayer(), value);
					player.sendMessage("Redeem", "Du hast §b+"+value+" Coins §7erhalten!");
					break;
				case LOTTO:
					Coins.lottoAction(LottoAction.ADD, player.getBukkitPlayer(), value);
					player.sendMessage("Redeem", "Du hast §b+"+value+" Lottoscheine §7erhalten!");
					break;
				}
			}else {
				ShopType shopType;
				int r = ThreadLocalRandom.current().nextInt(ShopType.values().length);
				shopType = ShopType.values()[r];
				
				ArrayList<ShopItemBuilder> items = new ArrayList<>();
				for(ShopItemBuilder item : Shop.items.keySet()) {
					if(Shop.items.get(item) == shopType) {
						items.add(item);
					}
				}
				getItem(player.getBukkitPlayer(), items, shopType);
			}
		}else {
			player.sendMessage("Redeem", "§cID nicht vorhanden! §8(§7"+ID+"§8)");
		}
	}
	
	private static boolean isExsist(String id) {
		try {
			if(MySQL.exsistValue("Reedem", "UID", id, "UID")) {
				return true;
			}
			return false;
		}catch (Exception e) {
			return false;
		}
	}
	
	private static void getItem(Player player, ArrayList<ShopItemBuilder> items, ShopType type) {
		int r = ThreadLocalRandom.current().nextInt(items.size());
		new BukkitRunnable() {
			@Override
			public void run() {
				CoreCache.getCorePlayer(player).sendMessage("Shop", "Du hast §a" + items.get(r).getItemName() + " §7erhalten!");
				if(!ShopHandler.hasBought(type, player.getUniqueId(), items.get(r))) {
					ShopHandler.setBought(type, items.get(r), player.getUniqueId());
				}else {
					if((items.get(r).getPrice()/4) > 999) {
						CoreCache.getCorePlayer(player).sendMessage("Shop", "Dir wurden §b" + ((items.get(r).getPrice()/4)/1000) + " Lottoscheine §7gutgeschrieben!");
						Coins.lottoAction(LottoAction.ADD, player, ((items.get(r).getPrice()/4)/1000));
					}else {
						CoreCache.getCorePlayer(player).sendMessage("Shop", "Dir wurden §b" + (items.get(r).getPrice()/4) + " Coins §7gutgeschrieben!");
						Coins.action(CoinAction.ADD, player, (items.get(r).getPrice()/4));
					}

				}
//				Bukkit.broadcastMessage("§8§l[§b§l!§8§l] §6"+player.getName() + " §7hat beim Kisten Spiel, §b" + items.get(r).getItemName() + " §7gewonnen!");
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 5);
	}
}
