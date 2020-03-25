package de.deminosa.lobby.main.shop.api;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	18:04:29 # 17.07.2019
*
*/

public enum ShopType {

	ARMOR("R�stung"),
	EFFECT("Effecte"),
	TOY("Spielzeuge"),
	PET("Haustiere"),
	NONE("�6Sector Ausw�hlen");
	
	String invName;
	
	private ShopType(String name) {
		invName = name;
	}
	
	public String getInventoryName() {
		return invName;
	}
}
