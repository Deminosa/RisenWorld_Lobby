package de.deminosa.lobby.main.lotto;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import de.deminosa.coinmanager.Coins;
import de.deminosa.coinmanager.command.CoinsCommand.CoinAction;
import de.deminosa.coinmanager.command.LottoCommand.LottoAction;
import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.utils.gui.GUI;
import de.deminosa.core.utils.gui.GUIButton;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.lobby.RisenWorld_Lobby;
import io.netty.util.internal.ThreadLocalRandom;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:38:58 # 06.03.2020
*
*/

public class LottoInventory {

	private CorePlayer player;
	
	public LottoInventory(CorePlayer player) {
		this.player = player;
		start();
	}
	
	private void start() {
		GUI gui = new GUI(player, "§6Lotto", 36);
		
		gui.getInventory().setItem(2+9, new ItemBuilder(Material.EMERALD, ThreadLocalRandom.current().nextInt(9)+1).setName("§6Slots 1").build());
		gui.getInventory().setItem(4+9, new ItemBuilder(Material.EMERALD, ThreadLocalRandom.current().nextInt(9)+1).setName("§6Slots 2").build());
		gui.getInventory().setItem(6+9, new ItemBuilder(Material.EMERALD, ThreadLocalRandom.current().nextInt(9)+1).setName("§6Slots 3").build());
		
		int endrun = ThreadLocalRandom.current().nextInt(50)+10;
		gui.getInventory().setItem(4+27, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short)1).setName("§6Warte auf Ergebniss...").build());
		
//		Bukkit.broadcastMessage("endrun="+endrun);
		
		for(int i = 0; i < endrun; i++) {
			new BukkitRunnable() {
				@Override
				public void run() {
					gui.getInventory().setItem(2+9, new ItemBuilder(Material.EMERALD, ThreadLocalRandom.current().nextInt(9)+1).setName("§6Slots 1").build());
					gui.getInventory().setItem(4+9, new ItemBuilder(Material.EMERALD, ThreadLocalRandom.current().nextInt(9)+1).setName("§6Slots 2").build());
					gui.getInventory().setItem(6+9, new ItemBuilder(Material.EMERALD, ThreadLocalRandom.current().nextInt(9)+1).setName("§6Slots 3").build());
					player.playsound(Sound.CLICK);
				}
			}.runTaskLater(RisenWorld_Lobby.getInstance(), 2*i);
		}
		
		new BukkitRunnable() {
			@Override
			public void run() {
				gui.getInventory().setItem(2+9, new ItemBuilder(Material.EMERALD, ThreadLocalRandom.current().nextInt(9)+1).setName("§6Slots 1").build());
				gui.getInventory().setItem(4+9, new ItemBuilder(Material.EMERALD, ThreadLocalRandom.current().nextInt(9)+1).setName("§6Slots 2").build());
				gui.getInventory().setItem(6+9, new ItemBuilder(Material.EMERALD, ThreadLocalRandom.current().nextInt(9)+1).setName("§6Slots 3").build());
				
//				gui.getInventory().setItem(2+9, new ItemBuilder(Material.EMERALD, 1).setName("§6Slots 1").build());
//				gui.getInventory().setItem(4+9, new ItemBuilder(Material.EMERALD, 1).setName("§6Slots 2").build());
//				gui.getInventory().setItem(6+9, new ItemBuilder(Material.EMERALD, 1).setName("§6Slots 3").build());
				player.playsound(Sound.NOTE_PLING);
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), (2*endrun)+4);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				checkWinn(gui.getInventory());
				gui.setButton(gui.getInventory().getSize()-1, new GUIButton() {
					@Override
					public void onClick(InventoryClickEvent arg0) {
						if(Coins.hasEnoughLotto(player.getBukkitPlayer(), 1)) {
							Coins.lottoAction(LottoAction.REMOVE, player.getBukkitPlayer(), 1);
							new LottoInventory(player);
						}
					}
					
					@Override
					public ItemStack getIcon() {
						return new ItemBuilder(Material.NETHER_STAR)
								.setName("§6Nochmal?")
								.addLoreLine("§7Klicke hier um noch mal zu spielen!")
								.addLoreLine("")
								.addLoreLine("§7Du besitz §b" + Coins.getLotto(player.getBukkitPlayer()) + "/1 §7Lottoscheine!")
								.build();
					}
				});
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), (2*endrun)+20);
		
		gui.open();
	}
	
	private void checkWinn(Inventory inv) {
		int a = inv.getItem(2+9).getAmount();
		int b = inv.getItem(4+9).getAmount();
		int c = inv.getItem(6+9).getAmount();
		
		String s = ""+a+b+c;
		
		if(s.equals("999")) {
			inv.setItem(4+27, win("999 Coins"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 999);
		}else if(s.equals("888")) {
			inv.setItem(4+27, win("888 Coins"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 888);
		}else if(s.equals("777")) {
			inv.setItem(4+27, win("777 Coins"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 777);
		}else if(s.equals("666")) {
			inv.setItem(4+27, win("666 Coins"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 666);
		}else if(s.equals("555")) {
			inv.setItem(4+27, win("555 Coins"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 555);
		}else if(s.equals("444")) {
			inv.setItem(4+27, win("444 Coins"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 444);
		}else if(s.equals("333")) {
			inv.setItem(4+27, win("333 Coins"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 333);
		}else if(s.equals("222")) {
			inv.setItem(4+27, win("222 Coins"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 222);
		}else if(s.equals("111")) {
			inv.setItem(4+27, win("111 Coins"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 111);
		}else if(s.equals("193")) {
			inv.setItem(4+27, win("500"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 500);
		}else if(s.equals("142")) {
			inv.setItem(4+27, win("484"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 484);
		}else if(s.equals("911")) {
			inv.setItem(4+27, win("468"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 468);
		}else if(s.equals("121")) {
			inv.setItem(4+27, win("452"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 452);
		}else if(s.equals("721")) {
			inv.setItem(4+27, win("436"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 436);
		}else if(s.equals("247")) {
			inv.setItem(4+27, win("420"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 420);
		}else if(s.equals("459")) {
			inv.setItem(4+27, win("404"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 404);
		}else if(s.equals("969")) {
			inv.setItem(4+27, win("388"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 388);
		}else if(s.equals("991")) {
			inv.setItem(4+27, win("372"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 372);
		}else if(s.equals("945")) {
			inv.setItem(4+27, win("356"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 356);
		}else if(s.equals("881")) {
			inv.setItem(4+27, win("340"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 340);
		}else if(s.equals("858")) {
			inv.setItem(4+27, win("324"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 324);
		}else if(s.equals("841")) {
			inv.setItem(4+27, win("308"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 308);
		}else if(s.equals("679")) {
			inv.setItem(4+27, win("294"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 294);
		}else if(s.equals("563")) {
			inv.setItem(4+27, win("276"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 276);
		}else if(s.equals("561")) {
			inv.setItem(4+27, win("260"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 260);
		}else if(s.equals("496")) {
			inv.setItem(4+27, win("244"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 244);
		}else if(s.equals("495")) {
			inv.setItem(4+27, win("228"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 228);
		}else if(s.equals("466")) {
			inv.setItem(4+27, win("212"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 212);
		}else if(s.equals("454")) {
			inv.setItem(4+27, win("196"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 196);
		}else if(s.equals("429")) {
			inv.setItem(4+27, win("180"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 180);
		}else if(s.equals("420")) {
			inv.setItem(4+27, win("164"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 164);
		}else if(s.equals("373")) {
			inv.setItem(4+27, win("148"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 148);
		}else if(s.equals("353")) {
			inv.setItem(4+27, win("132"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 132);
		}else if(s.equals("292")) {
			inv.setItem(4+27, win("116"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 116);
		}else if(s.equals("284")) {
			inv.setItem(4+27, win("100"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 100);
		}else if(s.equals("257")) {
			inv.setItem(4+27, win("84"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 84);
		}else if(s.equals("155")) {
			inv.setItem(4+27, win("68"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 68);
		}else if(s.equals("196")) {
			inv.setItem(4+27, win("52"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 52);
		}else if(s.equals("168")) {
			inv.setItem(4+27, win("36"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 36);
		}else if(s.equals("153")) {
			inv.setItem(4+27, win("20"));
			Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 20);
		}else {
//			Bukkit.broadcastMessage(s);
			inv.setItem(4+27, lose());
		}
	}
	
	private ItemStack win(String s) {
		player.playsound(Sound.LEVEL_UP);
		return new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short)5).setName("§aGewonnen!").addLoreLine("§bGewonnen: §7"+s).build();
	}
	
	private ItemStack lose() {
		player.playsound(Sound.NOTE_BASS_DRUM);
		return new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short)14).setName("§cVerloren!").build();
	}
}
