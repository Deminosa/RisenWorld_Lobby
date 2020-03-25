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
	EFFECT("Effecte"),
	TOY("Spielzeuge"),
	PET("Haustiere"),
	NONE("§6Sector Auswählen");
	
	String invName;
	
	private ShopType(String name) {
		invName = name;
	}
	
	public String getInventoryName() {
		return invName;
	}
}
