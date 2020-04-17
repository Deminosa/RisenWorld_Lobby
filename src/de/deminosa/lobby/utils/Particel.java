package de.deminosa.lobby.utils;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	19:12:58 # 05.01.2020
*
*/

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import de.deminosa.lobby.RisenWorld_Lobby;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class Particel {

	EnumParticle particletype;
	boolean longdistance;
	Location location;
	float offsetx;
	float offsety;
	float offsetz;
	float speed;
	int amount;
	RisenWorld_Lobby plugin;


	public Particel(RisenWorld_Lobby plugins,EnumParticle particletype,Location location,boolean longdistance,float offsetx,float offsety,float offsetz,float speed,int amount) {
		this.particletype = particletype;
		this.location = location;
		this.longdistance = longdistance;
		this.offsetx = offsetx;
		this.offsety = offsety;
		this.offsetz = offsetz;
		this.speed = speed;
		this.amount = amount;
		this.plugin = plugins;
	}
	
	public void draw() {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particletype, longdistance, (float)location.getX(), (float)location.getY(), (float)location.getZ(), offsetx, offsety, offsetz, speed, amount, 0);
		for(Player player : Bukkit.getOnlinePlayers()){
			((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
		}
	}
	
	
	public Location getLocation() {
		return location;
	}
	
	public void drawBan(EnumParticle pat2, EnumParticle pat3) {
    	Location loc = this.location;
    	EnumParticle pat = this.particletype;
    	new BukkitRunnable() {
			double t = Math.PI/4;
    		@Override
			public void run() {
				t = t + 0.1*Math.PI;
				for(double theta = 0; theta <= 2*Math.PI; theta = theta + Math.PI/64) {
					double x = t*Math.cos(theta);
					double y = 2*Math.exp(-0.1*t) * Math.sin(t) + 1.5;
					double z = t*Math.sin(theta);
					loc.add(x,y,z);
					PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(pat, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), 0, 0, 0, 0, 1, 0);
					for(Player player : Bukkit.getOnlinePlayers()){
						((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
					}
					loc.subtract(x,y,z);
					
					theta = theta + Math.PI/64;
					
					x = t*Math.cos(theta);
					y = 2*Math.exp(-0.1*t) * Math.sin(t) + 1.5;
					z = t*Math.sin(theta);
					loc.add(x,y,z);
					PacketPlayOutWorldParticles packet2 = new PacketPlayOutWorldParticles(pat2, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), 0, 0, 0, 0, 1, 0);
					for(Player player : Bukkit.getOnlinePlayers()){
						((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet2);
					}
					loc.subtract(x,y,z);
					
					theta = theta + Math.PI/64;
					
					x = t*Math.cos(theta);
					y = 2*Math.exp(-0.1*t) * Math.sin(t) + 1.5;
					z = t*Math.sin(theta);
					loc.add(x,y,z);
					PacketPlayOutWorldParticles packet3 = new PacketPlayOutWorldParticles(pat3, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), 0, 0, 0, 0, 1, 0);
					for(Player player : Bukkit.getOnlinePlayers()){
						((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet3);
					}
					loc.subtract(x,y,z);
					if(t > 20) {
						this.cancel();
					}
				}
			}
		}.runTaskTimer(plugin, 1, 1);
    }
	
	public void drawRandom(float radius){
		Random random = new Random();
		float x = random.nextFloat();
		float z = random.nextFloat();
		int i = random.nextInt(4);
		if(i == 0) {
			PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(this.particletype, this.longdistance, (float)this.location.getX() - x, (float)this.location.getY(), (float)this.location.getZ() - z, this.offsetx, this.offsety, this.offsetz, this.speed, this.amount, 0);
			for(Player player : Bukkit.getOnlinePlayers()){
				((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
			}
		}else if(i == 1) {
			PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(this.particletype, this.longdistance, (float)this.location.getX() + x, (float)this.location.getY(), (float)this.location.getZ() + z, this.offsetx, this.offsety, this.offsetz, this.speed, this.amount, 0);
			for(Player player : Bukkit.getOnlinePlayers()){
				((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
			}
		}else if(i == 2) {
			PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(this.particletype, this.longdistance, (float)this.location.getX() - x, (float)this.location.getY(), (float)this.location.getZ() + z, this.offsetx, this.offsety, this.offsetz, this.speed, this.amount, 0);
			for(Player player : Bukkit.getOnlinePlayers()){
				((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
			}
		}else if(i == 3) {
			PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(this.particletype, this.longdistance, (float)this.location.getX() + x, (float)this.location.getY(), (float)this.location.getZ() - z, this.offsetx, this.offsety, this.offsetz, this.speed, this.amount, 0);
			for(Player player : Bukkit.getOnlinePlayers()){
				((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
			}
		}
    }
	
	public void drawHelixCircel() {
    	Location loc = this.location;
    	EnumParticle pat = this.particletype;
    	new BukkitRunnable() {
    		double t = 0;
			double r = 1;
			@Override
			public void run() {
				t = t + Math.PI/16;
				double x = r*Math.cos(t);
				double y = r*Math.cos(t);
				double z = r*Math.sin(t);
				loc.add(x,y,z);
				PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(pat, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), 0, 0, 0, 0, 1, 0);
				for(Player player : Bukkit.getOnlinePlayers()){
					((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
				}
				loc.subtract(x,y,z);
				if(t > Math.PI*8) {
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, 0, 1);
		new BukkitRunnable() {
    		double t = 0;
			double r = 1;
			@Override
			public void run() {
				t = t + Math.PI/16;
				double x = r*Math.sin(t);
				double y = r*Math.cos(t);
				double z = r*Math.cos(t);
				loc.add(x,y,z);
				PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(pat, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), 0, 0, 0, 0, 1, 0);
				for(Player player : Bukkit.getOnlinePlayers()){
					((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
				}
				loc.subtract(x,y,z);
				if(t > Math.PI*8) {
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, 0, 1);
    }
	
    @SuppressWarnings("unused")
	private Location getLocationAroundCircle(Location center, double radius, double angleInRadian) {
        double x = center.getX() + radius * Math.cos(angleInRadian);
        double z = center.getZ() + radius * Math.sin(angleInRadian);
        double y = center.getY();

        Location loc = new Location(center.getWorld(), x, y, z);
        Vector difference = center.toVector().clone().subtract(loc.toVector()); // this sets the returned location's direction toward the center of the circle
        loc.setDirection(difference);

        return loc;
    }
}