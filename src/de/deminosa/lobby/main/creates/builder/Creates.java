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
import org.bukkit.util.Vector;

import de.deminosa.coinmanager.Coins;
import de.deminosa.coinmanager.command.CoinsCommand.CoinAction;
import de.deminosa.coinmanager.command.LottoCommand.LottoAction;
import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.hologramm.Hologram;
import de.deminosa.core.utils.mathmanager.CoreMath;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.creates.CreatesManager;
import de.deminosa.lobby.main.shop.ShopHandler;
import de.deminosa.lobby.main.shop.Items.effecte.EffectFlame;
import de.deminosa.lobby.main.shop.Items.effecte.EffectHerz;
import de.deminosa.lobby.main.shop.Items.pets.PetChicken;
import de.deminosa.lobby.main.shop.Items.pets.PetCow;
import de.deminosa.lobby.main.shop.Items.pets.PetPig;
import de.deminosa.lobby.main.shop.Items.pets.PetPilzkuh;
import de.deminosa.lobby.main.shop.Items.pets.PetRabbit;
import de.deminosa.lobby.main.shop.Items.pets.PetSheep;
import de.deminosa.lobby.main.shop.Items.pets.PetWolf;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorChain;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorDiamond;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorGold;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorIron;
import de.deminosa.lobby.main.shop.Items.ruestung.ShopArmorLether;
import de.deminosa.lobby.main.shop.Items.special.EffectBarierre;
import de.deminosa.lobby.main.shop.Items.special.ShopArmorLetherRainbow;
import de.deminosa.lobby.main.shop.Items.special.ToyCoinTNT;
import de.deminosa.lobby.main.shop.Items.toy.ToyJumpStick;
import de.deminosa.lobby.main.shop.Items.toy.ToyKnockBack;
import de.deminosa.lobby.main.shop.Items.verwandlung.VerwandlungChicken;
import de.deminosa.lobby.main.shop.Items.verwandlung.VerwandlungCow;
import de.deminosa.lobby.main.shop.Items.verwandlung.VerwandlungPig;
import de.deminosa.lobby.main.shop.Items.verwandlung.VerwandlungPilzkuh;
import de.deminosa.lobby.main.shop.Items.verwandlung.VerwandlungRabbit;
import de.deminosa.lobby.main.shop.api.ShopItemBuilder;
import de.deminosa.lobby.main.shop.api.ShopType;
import de.deminosa.lobby.main.timers.TestTimer;
import de.deminosa.lobby.regedit.Toroku;
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
	
	private double Jeckpot, spezial, Armor, effecte, pet, toy, lotto, coins, magic;
	
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

					if(CoreMath.chance(getJeckpot())) {
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
					}else if(CoreMath.chance(getMagic())) {
						ShopItemBuilder[] items = {new VerwandlungCow(), new VerwandlungChicken(), 
								new VerwandlungPig(), new VerwandlungPilzkuh(), new VerwandlungRabbit()};
						getItem(holo, event.getPlayer(), items, ShopType.MAGIC);
					}else if(CoreMath.chance(getSpezial())) {
						ShopItemBuilder[] items = {new ShopArmorLetherRainbow(), new EffectBarierre(), new ToyCoinTNT()};
						getItem(holo, event.getPlayer(), items, ShopType.SPECIAL);
					}else if(CoreMath.chance(getArmor())) {
						ShopItemBuilder[] items = {new ShopArmorDiamond.Helmet(),
								new ShopArmorDiamond.Chestplate(), new ShopArmorDiamond.Leggins(), 
								new ShopArmorDiamond.Boots(), new ShopArmorLether.Helmet(),
								new ShopArmorLether.Chestplate(), new ShopArmorLether.Leggins(),
								new ShopArmorLether.Boots(), new ShopArmorGold.Helmet(),
								new ShopArmorGold.Chestplate(), new ShopArmorGold.Leggins(),
								new ShopArmorGold.Boots(), new ShopArmorIron.Helmet(),
								new ShopArmorIron.Chestplate(), new ShopArmorIron.Leggins(),
								new ShopArmorIron.Boots(), new ShopArmorChain.Helmet(),
								new ShopArmorChain.Chestplate(), new ShopArmorChain.Leggins(),
								new ShopArmorChain.Boots()};
						getItem(holo, event.getPlayer(), items, ShopType.ARMOR);
					}else if(CoreMath.chance(getEffecte())) {
						ShopItemBuilder[] items = {new EffectHerz(), new EffectFlame()};
						getItem(holo, event.getPlayer(), items, ShopType.EFFECT);
					}else if(CoreMath.chance(getPet())) {
						ShopItemBuilder[] items = {new PetCow(), new PetChicken(), new PetPig(), new PetSheep(),
								new PetPilzkuh(), new PetRabbit(), new PetWolf()};
						getItem(holo, event.getPlayer(), items, ShopType.PET);
					}else if(CoreMath.chance(getToy())) {
						ShopItemBuilder[] items = {new ToyJumpStick(), new ToyKnockBack()};
						getItem(holo, event.getPlayer(), items, ShopType.TOY);
					}else if(CoreMath.chance(getLotto())){
						int amount = ThreadLocalRandom.current().nextInt(3)+1;
						holo.changeText("§6Lotto: §b+"+amount);
						new BukkitRunnable() {
							@Override
							public void run() {
								rocked(event.getPlayer(), 1);
								Coins.lottoAction(LottoAction.ADD, event.getPlayer(), amount);
								CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Lotto", "§a+"+amount);
							}
						}.runTaskLater(RisenWorld_Lobby.getInstance(), 2);
					}else if(CoreMath.chance(getCoins())){
						holo.changeText("§6Coins: §b+"+coins);
						new BukkitRunnable() {
							@Override
							public void run() {
								Coins.action(CoinAction.ADD, event.getPlayer(), coins);
								CoreCache.getCorePlayer(event.getPlayer()).sendMessage("Coins", "§a+"+coins);
								CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.LEVEL_UP);
							}
						}.runTaskLater(RisenWorld_Lobby.getInstance(), 2);
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
	
	private void getItem(Hologram holo, Player player, ShopItemBuilder[] items, ShopType type) {
		int r = ThreadLocalRandom.current().nextInt(items.length);
		holo.changeText("§6"+type.getInventoryName());
		Hologram nextLine = new Hologram(holo.getLocation().add(0,0.75,0), "§b"+items[r].getItemName());
		nextLine.create();
		addHolos(player, nextLine);
		new BukkitRunnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10; i++) {
					rocked(player, i);
				}
				if(!ShopHandler.hasBought(type, player.getUniqueId(), items[r])) {
					ShopHandler.setBought(type, items[r], player.getUniqueId());
					CoreCache.getCorePlayer(player).sendMessage("Shop", "§aDu hast §6" + items[r].getItemName() + " §ageschenkt bekommen!");
				}else {
					if((items[r].getPrice()/4) > 999) {
						CoreCache.getCorePlayer(player).sendMessage("Shop", "Dir wurden §b" + ((items[r].getPrice()/4)/1000) + " Lottoscheine §7gutgeschrieben!");
						Coins.lottoAction(LottoAction.ADD, player, ((items[r].getPrice()/4)/1000));
						Hologram Line3 = new Hologram(holo.getLocation().add(0,0.50,0), "§6Lottoscheine: §b+"+((items[r].getPrice()/4)/1000));
						Line3.create();
						addHolos(player, Line3);
					}else {
						CoreCache.getCorePlayer(player).sendMessage("Shop", "Dir wurden §b" + (items[r].getPrice()/4) + " Coins §7gutgeschrieben!");
						Coins.action(CoinAction.ADD, player, (items[r].getPrice()/4));
						Hologram Line3 = new Hologram(holo.getLocation().add(0,0.50,0), "§6Coins: §b+"+(items[r].getPrice()/4));
						Line3.create();
						addHolos(player, Line3);
					}

				}
				Bukkit.broadcastMessage("§8§l[§b§l!§8§l] §6"+player.getName() 
				+ " §ahat §b" + items[r].getItemName() + 
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
	public double getLotto() {
		return lotto;
	}

	/**
	 * @param lotto the lotto to set
	 */
	public void setLotto(double lotto) {
		this.lotto = lotto;
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
}
