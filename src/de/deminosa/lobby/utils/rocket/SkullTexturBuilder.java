package de.deminosa.lobby.utils.rocket;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	09:50:03 # 19.04.2020
*
*/

public class SkullTexturBuilder {

	public static ItemStack createCustomHead(String value) {
	    String signature = "H116D5fhmj/7BVWqiRQilXmvoJO6wJzXH4Dvou6P2o9YMb+HaJT8s9+zt03GMYTipzK+NsW2D2JfzagnxLUTuiOtrCHm6V2udOM0HG0JeL4zR0Wn5oHmu+S7kUPUbt7HVlKaRXry5bobFQ06nUf7hOV3kPfpUJsfMajfabmoJ9RGMRVot3uQszjKOHQjxyAjfHP2rjeI/SktBrSscx0DzwBW9LCra7g/6Cp7/xPQTIZsqz2Otgp6i2h3YpXJPy02j4pIk0H4biR3CaU7FB0V4/D1Hvjd08giRvUpqF0a1w9rbpIWIH5GTUP8eLFdG/9SnHqMCQrTj4KkQiN0GdBO18JvJS/40LTn3ZLag5LBIa7AyyGus27N3wdIccvToQ6kHHRVpW7cUSXjircg3LOsSQbJmfLoVJ/KAF/m+de4PxIjOJIcbiOkVyQfMQltPg26VzRiu3F0qRvJNAAydH8AHdaqhkpSf6yjHqPU3p3BHFJld5o59WoD4WNkE3wOC//aTpV/f9RJ0JQko08v2mGBVKx7tpN7vHD1qD5ILzV1nDCV1/qbKgiOK9QmdXqZw9J3pM/DHtZ6eiRKni9BuGWlbWFN/qfFO2xY+J7SYFqTxBbffmvwvuF83QP5UdRTNVLYoV5S+yR5ac7fVWUZmLbq7tawyuCu0Dw24M9E1BSnpSc=";

	    GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
	    gameProfile.getProperties().put("textures", new Property("textures", value, signature));

	    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
	    Field profileField = null;
	    try {
	        profileField = skullMeta.getClass().getDeclaredField("profile");
	        profileField.setAccessible(true);
	        profileField.set(skullMeta, gameProfile);
	    } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException exception) {
	        exception.printStackTrace();
	    }
	    skull.setItemMeta(skullMeta);

	    return skull;
	}
	
}
