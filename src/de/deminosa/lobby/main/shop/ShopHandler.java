package de.deminosa.lobby.main.shop;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;

import de.deminosa.core.utils.IDManager.IDManager;
import de.deminosa.core.utils.mysql.MySQL;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import de.deminosa.lobby.main.shop.api.ShopType;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	19:21:32 # 22.12.2019
 *
 */

public class ShopHandler {

	@SuppressWarnings("unused")
	private static String table = "LobbyShop_", ItemID = "ItemID", buy = "Bought", InUse = "InUse";

	public static boolean hasAmount(ShopType shopType, UUID uuid) {
		int i = MySQL.getInt(table+shopType.name(), "UUID", uuid.toString(), "amount");
		if(i > 0) {
			return true;
		}
		return false;
	}

	public static boolean hasBought(ShopType shopType, UUID uuid, ShopItemBuilder item) {
		ArrayList<String> ItemIDs = MySQL.getArrayList(table+shopType.name(), "UUID", uuid.toString(), ItemID);
		if(ItemIDs != null) {
			if(ItemIDs.contains(String.valueOf(item.getItemID()))) {
				
				return true;
			}
			return false;
		}
		return false;
	}

	public static void setAmount(ShopType shopType, UUID uuid, int i) {
		MySQL.update(table+shopType.name(), "amount", String.valueOf(i), "UUID", uuid.toString());
	}

	public static void setBought(ShopType shopType, ShopItemBuilder item, UUID uuid) {
		String ID_OK = IDManager.generateIDWithSlpit(200, 8);
		MySQL.set(table+shopType.name(), "OK", ID_OK);
		MySQL.update(table+shopType.name(), "UUID", uuid.toString(), "OK", ID_OK);
		MySQL.update(table+shopType.name(), ItemID, ""+item.getItemID(), "OK", ID_OK);
		MySQL.update(table+shopType.name(), "amount", String.valueOf(1), "OK", ID_OK);
		MySQL.update(table+shopType.name(), buy, String.valueOf(1), "OK", ID_OK);
	}
	
	public static boolean editBought(ShopType shopType, String uuid, String uid, int buyed) {
		ArrayList<String> ID_OKs = MySQL.getArrayList(table+shopType.name(), "UUID", uuid.toString(), "OK");
		for(String id : ID_OKs) {
			ArrayList<String> ItemIDs = MySQL.getArrayList(table+shopType.name(), "OK", id, ItemID);
			for(String uids : ItemIDs) {
				if(uids.contains(uid)) {
					MySQL.update(table+shopType.name(), buy, String.valueOf(buyed), "OK", id);
					Bukkit.broadcastMessage("Item Found!");
					return true;
				}
			}
			Bukkit.broadcastMessage(ItemIDs+"");
			Bukkit.broadcastMessage(ID_OKs+"");
		}
		return false;
	}

}
