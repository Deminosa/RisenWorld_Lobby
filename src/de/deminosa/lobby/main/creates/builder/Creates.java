package de.deminosa.lobby.main.creates.builder;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.lobby.RisenWorld_Lobby;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:50:41 # 23.03.2020
*
*/

public class Creates{

	CorePlayer player;
	Block block;
	Location loc;
	
	public Creates(CorePlayer player, Block block) {
		this.player = player;
		this.block = block;
		
		run();
	}
	
	public Creates(Location loc) {
		this.loc = loc;
		
		end(0, 0, 2, 0);
		end(0, 0, -2, 0);
		
		end(2, 0, 0, 0);
		end(-2, 0, 0, 0);
	}
	
	private void run() {
		block.setType(Material.AIR);
		player.teleport(block.getLocation().add(0.5,0,0.5));
		
		setChest(0, 0, 2, 10);
		setChest(0, 0, -2, 20);
		
		setChest(2, 0, 0, 30);
		setChest(-2, 0, 0, 40);
	}
	
	public void end(int x, int y, int z, int tick) {
		Bukkit.getScheduler().runTaskLater(RisenWorld_Lobby.getInstance(), new Runnable() {
			@Override
			public void run() {
				loc.clone().add(x, y, z).getBlock().setType(Material.AIR);
			}
		}, tick);
	}
	
	private void setChest(int x, int y, int z, int tick) {
		Bukkit.getScheduler().runTaskLater(RisenWorld_Lobby.getInstance(), new Runnable() {
			@Override
			public void run() {
				block.getLocation().clone().add(x, y, z).getBlock().setType(Material.TNT);
				player.playsound(Sound.ANVIL_LAND);
			}
		}, tick);
	}
}
