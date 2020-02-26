package de.deminosa.lobby.main.shop.Items.ruestung;

import java.util.ArrayList;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class ShopArmorLetherRainbowTimer extends BukkitRunnable{
	Player player;
	int b;
	int g;
	int r;
	int first;
	int second;
	int third;
	int fourth;
	int fifth;
	int sixth;
	ArrayList<String> players;

	public ShopArmorLetherRainbowTimer(ArrayList<String> players, Player player, int b, int g, int r, int first, int second, int third, int fourth, int fifth, int sixth){
		this.player = player;
		this.b = b;
		this.g = g;
		this.r = r;
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.fifth = fifth;
		this.sixth = sixth;
		this.players = players;
	}

	public void run(){
		if (!this.players.contains(this.player.getName())){
			this.player.getInventory().setBoots(new ItemStack(Material.AIR));
			this.player.getInventory().setLeggings(new ItemStack(Material.AIR));
			this.player.getInventory().setChestplate(new ItemStack(Material.AIR));
			this.player.getInventory().setHelmet(new ItemStack(Material.AIR));
			cancel();
			return;
		}if (this.first <= 17){
			this.first += 1;
			this.g = ((this.first - 1) * 15);
			this.player.getInventory().setHelmet(helmet(this.b, this.g, this.r));
			this.player.getInventory().setChestplate(chestplate(this.b, this.g, this.r));
			this.player.getInventory().setLeggings(leggings(this.b, this.g, this.r));
			this.player.getInventory().setBoots(boots(this.b, this.g, this.r));
		}else if (this.second <= 17){
			this.second += 1;
			this.r = (255 - 15 * (this.second - 1));
			this.player.getInventory().setHelmet(helmet(this.b, this.g, this.r));
			this.player.getInventory().setChestplate(chestplate(this.b, this.g, this.r));
			this.player.getInventory().setLeggings(leggings(this.b, this.g, this.r));
			this.player.getInventory().setBoots(boots(this.b, this.g, this.r));
		}else if (this.third <= 17){
			this.third += 1;
			this.b = ((this.third - 1) * 15);
			this.player.getInventory().setHelmet(helmet(this.b, this.g, this.r));
			this.player.getInventory().setChestplate(chestplate(this.b, this.g, this.r));
			this.player.getInventory().setLeggings(leggings(this.b, this.g, this.r));
			this.player.getInventory().setBoots(boots(this.b, this.g, this.r));
		}else if (this.fourth <= 17){
			this.fourth += 1;
			this.g = (255 - 15 * (this.fourth - 1));
			this.player.getInventory().setHelmet(helmet(this.b, this.g, this.r));
			this.player.getInventory().setChestplate(chestplate(this.b, this.g, this.r));
			this.player.getInventory().setLeggings(leggings(this.b, this.g, this.r));
			this.player.getInventory().setBoots(boots(this.b, this.g, this.r));
		}else if (this.fifth <= 17){
			this.fifth += 1;
			this.r = ((this.fifth - 1) * 15);
			this.player.getInventory().setHelmet(helmet(this.b, this.g, this.r));
			this.player.getInventory().setChestplate(chestplate(this.b, this.g, this.r));
			this.player.getInventory().setLeggings(leggings(this.b, this.g, this.r));
			this.player.getInventory().setBoots(boots(this.b, this.g, this.r));
		}else if (this.sixth <= 17){
			this.sixth += 1;
			this.b = (255 - 15 * (this.sixth - 1));
			this.player.getInventory().setHelmet(helmet(this.b, this.g, this.r));
			this.player.getInventory().setChestplate(chestplate(this.b, this.g, this.r));
			this.player.getInventory().setLeggings(leggings(this.b, this.g, this.r));
			this.player.getInventory().setBoots(boots(this.b, this.g, this.r));
		}else{
			this.first = 0;
			this.second = 0;
			this.third = 0;
			this.fourth = 0;
			this.fifth = 0;
			this.sixth = 0;
		}
	}

	public ItemStack helmet(int b, int g, int r){
		ItemStack item = new ItemStack(Material.LEATHER_HELMET, 1);

		ItemMeta meta = item.getItemMeta();
		LeatherArmorMeta lmeta = (LeatherArmorMeta)meta;
		((LeatherArmorMeta)meta).setColor(Color.fromBGR(b, g, r));
		item.setItemMeta(lmeta);

		return item;
	}

	public ItemStack chestplate(int b, int g, int r){
		ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);

		ItemMeta meta = item.getItemMeta();
		LeatherArmorMeta lmeta = (LeatherArmorMeta)meta;
		((LeatherArmorMeta)meta).setColor(Color.fromBGR(b, g, r));
		item.setItemMeta(lmeta);

		return item;
	}

	public ItemStack leggings(int b, int g, int r){
		ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);

		ItemMeta meta = item.getItemMeta();
		LeatherArmorMeta lmeta = (LeatherArmorMeta)meta;
		((LeatherArmorMeta)meta).setColor(Color.fromBGR(b, g, r));
		item.setItemMeta(lmeta);

		return item;
	}

	public ItemStack boots(int b, int g, int r){
		ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);

		ItemMeta meta = item.getItemMeta();
		LeatherArmorMeta lmeta = (LeatherArmorMeta)meta;
		((LeatherArmorMeta)meta).setColor(Color.fromBGR(b, g, r));
		item.setItemMeta(lmeta);

		return item;
	}
}

