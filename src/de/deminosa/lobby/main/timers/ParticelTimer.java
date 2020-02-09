package de.deminosa.lobby.main.timers;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.deminosa.core.Core;
import de.deminosa.core.builders.CoreTimer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.warps.WarpManager;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.utils.DateManager;
import de.deminosa.lobby.utils.Particel;
import jump.Jump;
import net.minecraft.server.v1_8_R3.EnumParticle;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	19:14:40 # 05.01.2020
*
*/

public class ParticelTimer implements CoreTimer{

	int ticks = 0;
	int s = 0;
	public static String jumpType = "";
	
	@Override
	public void tick() {
		ticks++;
		if(ticks == 20) {
			ticks = 0;
			s++;
			
			for(Player player : Bukkit.getOnlinePlayers()) {
				if(Jump.sucBlocks.containsKey(player)) {
					CoreCache.getCorePlayer(player).sendTitle(0, 20, 5, "", "§b" + Jump.getJumpedBlocks(player)+" §6Jump's");
				}
			}
			
			if(s == 7) {
				for(String warps : listDir(new File(Core.getInstance().getDataFolder() + "/warp/"))) {
					Particel pat = new Particel(RisenWorld_Lobby.getInstance(), EnumParticle.FLAME, WarpManager.getWarpLocation(warps).add(0,1,0), true, 0, 0, 0, 0, 1);
					pat.drawHelixCircel();
				}
				s = 0;
			}
		}
		
		if(DateManager.getTime().equals("23:55:00")) {Bukkit.broadcastMessage("§9Lobby §7Restart in 5min §8(§700:00 Uhr§8)");}
		if(DateManager.getTime().equals("23:57:00")) {Bukkit.broadcastMessage("§9Lobby §7Restart in 3min §8(§700:00 Uhr§8)");}
		if(DateManager.getTime().equals("23:59:00")) {Bukkit.broadcastMessage("§9Lobby §7Restart in 1min §8(§700:00 Uhr§8)");}
		if(DateManager.getTime().equals("23:59:30")) {Bukkit.broadcastMessage("§9Lobby §7Restart in 30sec §8(§700:00 Uhr§8)");}
		if(DateManager.getTime().equals("23:59:45")) {Bukkit.broadcastMessage("§9Lobby §7Restart in 15sec §8(§700:00 Uhr§8)");}
		if(DateManager.getTime().equals("23:59:55")) {Bukkit.broadcastMessage("§9Lobby §7Restart in 5sec §8(§700:00 Uhr§8)");}
		if(DateManager.getTime().equals("23:59:56")) {Bukkit.broadcastMessage("§9Lobby §7Restart in 4sec §8(§700:00 Uhr§8)");}
		if(DateManager.getTime().equals("23:59:57")) {Bukkit.broadcastMessage("§9Lobby §7Restart in 3sec §8(§700:00 Uhr§8)");}
		if(DateManager.getTime().equals("23:59:58")) {Bukkit.broadcastMessage("§9Lobby §7Restart in 2sec §8(§700:00 Uhr§8)");}
		if(DateManager.getTime().equals("23:59:59")) {Bukkit.broadcastMessage("§9Lobby §7Restart in 1sec §8(§700:00 Uhr§8)");}
		if(DateManager.getTime().equals("0:00:00") || DateManager.getTime().equals("00:00:00")) {Bukkit.shutdown();}
	}
	
	public ArrayList<String> listDir(File file) {
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
