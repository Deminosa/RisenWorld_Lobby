package de.deminosa.lobby.utils.rocket;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class RocketBuilder {

	World world;
	Location loc;
	FireworkMeta fm;
	Firework fw;
	
	public RocketBuilder(World world, Location loc) {
		world.spawn(loc, Firework.class);
		fw = loc.getWorld().spawn(loc, Firework.class);
		fm = fw.getFireworkMeta();
	}
	
	public void build(boolean flicker, boolean trail, FireworkEffect.Type type, Color color, Color fadeColor, int power) {
		fm.addEffect(FireworkEffect.builder()
				.flicker(flicker)
				.trail(trail)
				.with(type)
				.withColor(color)
				.withFade(fadeColor)
				.build());
		fm.setPower(power);
		fw.setFireworkMeta(fm);
	}
	
}
