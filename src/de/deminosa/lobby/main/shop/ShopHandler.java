package de.deminosa.lobby.main.shop;

import java.util.UUID;

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

	private static String table = "LobbyShop_", ItemID = "ItemID", buy = "Bought", InUse = "InUse";
	
	public static boolean hasAmount(ShopType shopType, UUID uuid) {
		int i = MySQL.getInt(table+shopType.name(), "UUID", uuid.toString(), "amount");
		if(i > 0) {
			return true;
		}
		return false;
	}
	
	public static boolean hasBought(ShopType shopType, UUID uuid) {
		int i = MySQL.getInt(table+shopType.name(), "UUID", uuid.toString(), buy);
		if(i == 1) {
			return true;
		}
		return false;
	}
	
	public static void setAmount(ShopType shopType, UUID uuid, int i) {
		MySQL.update(table+shopType.name(), "amount", String.valueOf(i), "UUID", uuid.toString());
	}
	
	public static void setBought(ShopType shopType, ShopItemBuilder item, UUID uuid) {
		MySQL.update(table+shopType.name(), "UUID", uuid.toString(), ItemID, String.valueOf(item.getItemID()));
		MySQL.update(table+shopType.name(), "amount", String.valueOf(1), ItemID, String.valueOf(item.getItemID()));
		MySQL.update(table+shopType.name(), buy, String.valueOf(1), ItemID, String.valueOf(item.getItemID()));
	}
	
}
