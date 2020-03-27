package de.deminosa.lobby.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import de.deminosa.core.Core;
import de.deminosa.core.utils.ymlhelper.YamlConfig;
import de.deminosa.lobby.main.timers.TestTimer;
import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.WorldServer;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	17:39:43 # 15.03.2020
 *
 */

public class EntityControll {

	public static void removeAI(Entity entity) {
		net.minecraft.server.v1_8_R3.Entity nmsEnt = ((CraftEntity) entity).getHandle();
		NBTTagCompound tag = nmsEnt.getNBTTag();

		if (tag == null) {
			tag = new NBTTagCompound();
		}

		nmsEnt.c(tag);
		tag.setInt("NoAI", 0);
		nmsEnt.f(tag);
	}

	public static void loadSettings() {
		for(String file : listDir(new File(Core.getInstance().getDataFolder() + "/entitys/"))) {
			YamlConfig c = new YamlConfig("/entitys/", file);
			
			String world = (String) c.get("loc.world");
			int x = (int) c.get("loc.x");
			int y = (int) c.get("loc.y");
			int z = (int) c.get("loc.z");
			String name = (String) c.get("display");
			
			Location loc = new Location(Bukkit.getWorld(world), x, y, z);

			spawnVillager(loc, name);
		}
	}

	public static void addEntity(Player player, String name) {
		YamlConfig config = new YamlConfig("/entitys/", name+"_settings");

		config.set("name", name);
		config.set("display", "§6Change in Config");
		config.set("loc.world", player.getWorld().getName());
		config.set("loc.x", player.getLocation().getBlockX());
		config.set("loc.y", player.getLocation().getBlockY());
		config.set("loc.z", player.getLocation().getBlockZ());
		
		config.save();
	}

	public static void spawnVillager(Location loc, String name) {
		
		WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
		EntityVillager NPC = new EntityVillager(world);

		NPC.setCustomName(name);
		NPC.setCustomNameVisible(true);

		//NoAI
		try{
			Field b = PathfinderGoalSelector.class.getDeclaredField("b");
			Field c = PathfinderGoalSelector.class.getDeclaredField("c");
			
			b.setAccessible(true);
			c.setAccessible(true);
			
			b.set(NPC.goalSelector, new UnsafeList<PathfinderGoalSelector>());
			b.set(NPC.targetSelector, new UnsafeList<PathfinderGoalSelector>());
			
			c.set(NPC.goalSelector, new UnsafeList<PathfinderGoalSelector>());
			c.set(NPC.targetSelector, new UnsafeList<PathfinderGoalSelector>());
		}catch(Exception e) {
			e.printStackTrace();
		}

		((CraftLivingEntity) NPC.getBukkitEntity()).setRemoveWhenFarAway(false);

		NPC.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		world.addEntity(NPC);
	}
	
	private static ArrayList<String> listDir(File file) {
		ArrayList<String> list = new ArrayList<>();
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				String Fname = files[i].getName().replace(".yml", "");
				list.add(Fname);
			}
		} 
		return list;
	}

}
