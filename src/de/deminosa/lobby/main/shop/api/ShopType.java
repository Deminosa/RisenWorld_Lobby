package de.deminosa.lobby.main.shop.api;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	18:04:29 # 17.07.2019
*
*/

public enum ShopType {

	ARMOR("Rüstung"),
	EFFECT("Effekte"),
	TOY("Spielzeuge"),
	PET("Haustiere"),
	SPECIAL("§6Ausergewöhnlich"),
	MAGIC("§6Verwandlungszauber"),
	NONE("§6Shop");
	
	String invName;
	
	private ShopType(String name) {
		invName = name;
	}
	
	public String getInventoryName() {
		return invName;
	}
}
