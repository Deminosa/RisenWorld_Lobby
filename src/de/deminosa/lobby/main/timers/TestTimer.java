package de.deminosa.lobby.main.timers;

import java.util.HashMap;

import org.bukkit.Location;

import net.minecraft.server.v1_8_R3.EntityVillager;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	18:20:46 # 15.03.2020
 *
 */

public class TestTimer implements Runnable{

	private static HashMap<EntityVillager, Location> v = new HashMap<>();
	int t = 0;

	public static void freezz(EntityVillager vi, Location loc) {
		if(!v.containsKey(vi)) {
			v.put(vi, loc);
		}
	}

	@Override
	public void run() {
		for(EntityVillager vi : v.keySet()) {
			vi.getBukkitEntity().teleport(v.get(vi));
		}
	}

}
