package de.deminosa.lobby.main.creates.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import de.deminosa.coinmanager.Coins;
import de.deminosa.coinmanager.command.CoinsCommand.CoinAction;
import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.hologramm.Hologram;
import de.deminosa.core.utils.mathmanager.CoreMath;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.creates.CreatesManager;
import de.deminosa.lobby.main.shop.Shop;
import de.deminosa.lobby.main.shop.ShopHandler;
import de.deminosa.lobby.main.shop.api.EconomyType;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import de.deminosa.lobby.main.shop.api.ShopType;
import de.deminosa.lobby.main.timers.TestTimer;
import de.deminosa.lobby.utils.rocket.RocketBuilder;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	20:50:41 # 23.03.2020
 *
 */

public class Creates implements Listener{

	CorePlayer player;
	Block block;
	Location loc;
	Hologram saveHolo;
	HashMap<Player, Location> creats;
	HashMap<Player, Integer> chest;
	HashMap<Player, Integer> fail;
	HashMap<Player, ArrayList<Location>> holos;
	HashMap<Player, Location> lastLoc;

	private double Jeckpot, spezial, Armor, effecte, pet, toy, token, coins, magic, heads;

	public Creates(CorePlayer player, Block block) {
		this.player = player;
		this.block = block;
		creats = new HashMap<>();
		chest = new HashMap<>();
		fail = new HashMap<>();
		holos = new HashMap<>();
		lastLoc = new HashMap<>();

		run();
		CreatesManager.creats.put(player.getBukkitPlayer(), block.getLocation());
		lastLoc.put(player.getBukkitPlayer(), player.getLocation().clone());
		creats.put(player.getBukkitPlayer(), block.getLocation());
		chest.put(player.getBukkitPlayer(), 4);
	}

	public Creates(Location loc) {
		this.loc = loc;

		end(0, 0, 2, 0);
		end(0, 0, -2, 0);

		end(2, 0, 0, 0);
		end(-2, 0, 0, 0);
	}

	public Creates() {}

	private void run() {
		block.setType(Material.AIR);
		player.teleport(block.getLocation().add(0.5,0,0.5));

		setChest(0, 0, 2, 10);
		setChest(0, 0, -2, 20);

		setChest(2, 0, 0, 30);
		setChest(-2, 0, 0, 40);

		new BukkitRunnable() {
			@Override
			public void run() {
				for(Location loc : TestTimer.holoLocs) {
					if(loc.distance(player.getLocation()) < 2) {
						saveHolo = Hologram.getByLocation(loc);
						saveHolo.remove();
					}
				}
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 2);
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
				block.getLocation().clone().add(x, y, z).getBlock().setType(Material.ENDER_PORTAL_FRAME);
				player.playsound(Sound.ANVIL_LAND);
			}
		}, tick);
	}

	@EventHandler
	public void quit(PlayerQuitEvent event) {
		if(CreatesManager.creats.containsKey(event.getPlayer())) {
			CreatesManager.creats.get(event.getPlayer()).getBlock().setType(Material.ENCHANTMENT_TABLE);
			new Creates(creats.get(event.getPlayer()));
			CreatesManager.creats.remove(event.getPlayer());
			removeHolos(event.getPlayer());
		}
	}

	@EventHandler
	public void move(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		if(CreatesManager.creats == null) return;

		if(CreatesManager.creats.containsKey(player)) {
			if(player.getLocation().distance(CreatesManager.creats.get(player)) > 1 &&
					chest.get(player) > 0) {
				player.teleport(CreatesManager.creats.get(player).clone().add(0.5,0,0.5));
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		if(block != null && block.getType() == Material.ENDER_PORTAL_FRAME) {
			if(CreatesManager.creats.containsKey(event.getPlayer())) {
				if(chest.get(event.getPlayer()) >= 1) {
					chest.put(event.getPlayer(), chest.get(event.getPlayer())-1);
					block.setType(Material.AIR);
					int coins = ThreadLocalRandom.current().nextInt(25)+1;
					Hologram holo = new Hologram(block.getLocation().add(0.5,0,0.5), "§6");
					holo.create();

					addHolos(event.getPlayer(), holo);
					if(!fail.containsKey(event.getPlayer())) {
						fail.put(event.getPlayer(), 0);
					}

					if(CoreMath.chance(getCoins())){
						holo.changeText("§6Coins: §b+"+coins);
						new BukkitRunnable() {
							@Override
							public void run() {
								Coins.action(CoinAction.ADD, event.getPlayer(), coins);
								CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Coins", "§a+"+coins);
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.LEVEL_UP);
							}
						}.runTaskLater(RisenWorld_Lobby.getInstance(), 2);
					}else if(CoreMath.chance(getToken())){
						int amount = ThreadLocalRandom.current().nextInt(3)+1;
						holo.changeText("§6Token: §b+"+amount);
						new BukkitRunnable() {
							@Override
							public void run() {
								rocked(event.getPlayer(), 1);
								Coins.tokenAction(CoinAction.ADD, event.getPlayer(), amount);
								CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Token", "§a+"+amount);
							}
						}.runTaskLater(RisenWorld_Lobby.getInstance(), 2);
					}else if(CoreMath.chance(getMagic())) {
						getItem(holo, event.getPlayer(), ShopType.MAGIC);
					}else if(CoreMath.chance(getSpezial())) {
						getItem(holo, event.getPlayer(), ShopType.SPECIAL);
					}else if(CoreMath.chance(getArmor())) {
						getItem(holo, event.getPlayer(), ShopType.ARMOR);
					}else if(CoreMath.chance(getEffecte())) {
						getItem(holo, event.getPlayer(), ShopType.EFFECT);
					}else if(CoreMath.chance(getPet())) {
						getItem(holo, event.getPlayer(), ShopType.PET);
					}else if(CoreMath.chance(getToy())) {
						getItem(holo, event.getPlayer(), ShopType.TOY);
					}else if(CoreMath.chance(getHeads())){
						getItem(holo, event.getPlayer(), ShopType.HEAD);
					}else if(CoreMath.chance(getJeckpot())) {
						holo.changeText("§6Coins: §b+500");
						new BukkitRunnable() {
							@Override
							public void run() {
								for(int i = 0; i < 50; i++) {
									rocked(event.getPlayer(), i);
								}
								Coins.action(CoinAction.ADD, event.getPlayer(), 500);
								CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Coins", "§a+500");
								Bukkit.broadcastMessage("§8§l[§b§l!§8§l] §6"+event.getPlayer().getName() + " §7hat beim Kisten Spiel, §b500 Coins §7gewonnen!");
							}
						}.runTaskLater(RisenWorld_Lobby.getInstance(), 5);
					}else {
						holo.changeText("§cNiete");
						CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.EXPLODE);
						fail.put(event.getPlayer(), fail.get(event.getPlayer())+1);
					}

					if(chest.get(event.getPlayer()) == 0) {
						new BukkitRunnable() {
							@Override
							public void run() {
								event.getPlayer().teleport(lastLoc.get(event.getPlayer()));
								try {
									if(CreatesManager.creats.containsKey(event.getPlayer())) {
										CreatesManager.creats.get(event.getPlayer()).getBlock().setType(Material.ENCHANTMENT_TABLE);
										CreatesManager.creats.remove(event.getPlayer());
										removeHolos(event.getPlayer());
										if(fail.get(event.getPlayer()) == 4) {
											Coins.chestAction(CoinAction.ADD, event.getPlayer(), 1);
											CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Chest", "§a+1");
										}
										if(fail.containsKey(event.getPlayer())) {
											fail.remove(event.getPlayer());
										}
										saveHolo.teleport(saveHolo.getLocation().add(0,1,0));
										saveHolo.create();
									}
								}catch (Exception e) {
									removeHolos(event.getPlayer());
								}
							}
						}.runTaskLater(RisenWorld_Lobby.getInstance(), 20*3);
					}
				}
			}
		}
	}

	private void addHolos(Player player, Hologram holo) {
		if(!holos.containsKey(player)) {
			ArrayList<Location> locs = new ArrayList<>();
			locs.add(holo.getLocation());
			holos.put(player, locs);
		}else {
			ArrayList<Location> locs = holos.get(player);
			locs.add(holo.getLocation());
			holos.put(player, locs);
		}
	}

	private void removeHolos(Player player) {
		if(holos.containsKey(player)) {
			for(Location locs : holos.get(player)) {
				Hologram hol = Hologram.getByLocation(locs);
				hol.remove();
			}
			holos.remove(player);
		}
	}

	private void getItem(Hologram holo, Player player, ShopType type) {
		ArrayList<ShopItemBuilder> items = new ArrayList<>();
		for(ShopItemBuilder item : Shop.items.keySet()) {
			if(Shop.items.get(item) == type) {
				items.add(item);
			}
		}

		int r = ThreadLocalRandom.current().nextInt(items.size());
		holo.changeText("§6"+type.getInventoryName());
		Hologram nextLine = new Hologram(holo.getLocation().add(0,0.75,0), "§b"+items.get(r).getItemName());
		nextLine.create();
		addHolos(player, nextLine);

		new BukkitRunnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10; i++) {
					rocked(player, i);
				}
				if(!ShopHandler.hasBought(type, player.getUniqueId(), items.get(r))) {
					ShopHandler.setBought(type, items.get(r), player.getUniqueId());
					CoreCache.getCorePlayer(player).sendMessage("Shop", "§aDu hast §6" + items.get(r).getItemName() + " §ageschenkt bekommen!");
				}else {
					if(items.get(r).getEconomyType() == EconomyType.COINS) {
						CoreCache.getCorePlayer(player).sendMessage("Shop", "Dir wurden §b" + (items.get(r).getPrice()/4) + " Coins §7gutgeschrieben!");
						Coins.action(CoinAction.ADD, player, (items.get(r).getPrice()/4));
						Hologram Line3 = new Hologram(holo.getLocation().add(0,0.50,0), "§6Coins: §b+"+(items.get(r).getPrice()/4));
						Line3.create();
						addHolos(player, Line3);
					}else {
						CoreCache.getCorePlayer(player).sendMessage("Shop", "Dir wurden §b" + (items.get(r).getPrice()/4) + " Tokens §7gutgeschrieben!");
						Coins.tokenAction(CoinAction.ADD, player, (items.get(r).getPrice()/4));
						Hologram Line3 = new Hologram(holo.getLocation().add(0,0.50,0), "§6Tokens: §b+"+(items.get(r).getPrice()/4));
						Line3.create();
						addHolos(player, Line3);
					}
				}
				Bukkit.broadcastMessage("§8§l[§b§l!§8§l] §6"+player.getName() 
				+ " §ahat §b" + items.get(r).getItemName() + 
				" §8(§7"+type.getInventoryName().replace("§6", "")+"§8) §agewonnen!");
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 5);
	}

	private void rocked(Player player, int i) {
		new BukkitRunnable() {
			@Override
			public void run() {
				RocketBuilder builder = new RocketBuilder(player.getWorld(), player.getLocation());
				builder.build(true, true, Type.BURST, Color.AQUA, Color.ORANGE, 1);
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 2*i);
	}

	/**
	 * @return the jeckpot
	 */
	public double getJeckpot() {
		return Jeckpot;
	}

	/**
	 * @param jeckpot the jeckpot to set
	 */
	public void setJeckpot(double jeckpot) {
		Jeckpot = jeckpot;
	}

	/**
	 * @return the spezial
	 */
	public double getSpezial() {
		return spezial;
	}

	/**
	 * @param spezial the spezial to set
	 */
	public void setSpezial(double spezial) {
		this.spezial = spezial;
	}

	/**
	 * @return the armor
	 */
	public double getArmor() {
		return Armor;
	}

	/**
	 * @param armor the armor to set
	 */
	public void setArmor(double armor) {
		Armor = armor;
	}

	/**
	 * @return the effecte
	 */
	public double getEffecte() {
		return effecte;
	}

	/**
	 * @param effecte the effecte to set
	 */
	public void setEffecte(double effecte) {
		this.effecte = effecte;
	}

	/**
	 * @return the pet
	 */
	public double getPet() {
		return pet;
	}

	/**
	 * @param pet the pet to set
	 */
	public void setPet(double pet) {
		this.pet = pet;
	}

	/**
	 * @return the toy
	 */
	public double getToy() {
		return toy;
	}

	/**
	 * @param toy the toy to set
	 */
	public void setToy(double toy) {
		this.toy = toy;
	}

	/**
	 * @return the lotto
	 */
	public double getToken() {
		return token;
	}

	/**
	 * @param lotto the lotto to set
	 */
	public void setToken(double token) {
		this.token = token;
	}

	/**
	 * @return the coins
	 */
	public double getCoins() {
		return coins;
	}

	/**
	 * @param coins the coins to set
	 */
	public void setCoins(double coins) {
		this.coins = coins;
	}

	/**
	 * @return the magic
	 */
	public double getMagic() {
		return magic;
	}

	/**
	 * @param magic the magic to set
	 */
	public void setMagic(double magic) {
		this.magic = magic;
	}

	/**
	 * @return the heads
	 */
	public double getHeads() {
		return heads;
	}

	/**
	 * @param heads the heads to set
	 */
	public void setHeads(double heads) {
		this.heads = heads;
	}
}
