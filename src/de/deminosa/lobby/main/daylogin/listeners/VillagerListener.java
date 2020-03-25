package de.deminosa.lobby.main.daylogin.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import de.deminosa.coinmanager.Coins;
import de.deminosa.coinmanager.command.CoinsCommand.CoinAction;
import de.deminosa.coinmanager.command.LottoCommand.LottoAction;
import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.gui.GUI;
import de.deminosa.core.utils.gui.GUIButton;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.daylogin.DayLoginHandler;
import de.deminosa.lobby.utils.DateManager;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	15:05:18 # 17.03.2020
*
*/

public class VillagerListener implements Listener{

	@EventHandler
	public void openInvVillager(InventoryOpenEvent event) {
		if(event.getInventory().getType() == InventoryType.MERCHANT) {
			new BukkitRunnable() {
				@Override
				public void run() {
					Player bplayer = (Player)event.getPlayer();
					CorePlayer player = CoreCache.getCorePlayer(bplayer);
					DayLoginHandler.regist(player);
					
					int getStreak = DayLoginHandler.getStreak(player);
					
					GUI gui = new GUI(CoreCache.getCorePlayer(bplayer), "�6Der Gl�ckliche Karl", 9);
					
					if(getStreak == 0 || getStreak == -1 || getStreak == -2) {
						gui.getInventory().setItem(2, soon(player));
						gui.getInventory().setItem(3, soon());
						gui.getInventory().setItem(4, soon());
						gui.getInventory().setItem(5, soon());
						gui.getInventory().setItem(6, soon());
						gui.getInventory().setItem(7, soon());
						
						gui.setButton(1, new GUIButton() {
							@Override
							public void onClick(InventoryClickEvent arg0) {
								if(DayLoginHandler.isRewardAvabile(player)) {
									DayLoginHandler.updateReward(player);
									Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 5);
									player.sendMessage("Tagesbonus", "Du hast �a+5 Coins�7 erhalten!");
									player.getBukkitPlayer().closeInventory();
								}
							}
							
							@Override
							public ItemStack getIcon() {
								return new ItemBuilder(Material.SPECKLED_MELON).setName("�6Belohnung")
										.addLoreLine("�7Verf�gbar: " + 
												(DayLoginHandler.isRewardAvabile(player) ? "�aJa" : "�b" 
												+ (Integer.valueOf(DayLoginHandler.getLastDay(player))+1) + "." + DateManager.getMonth()+"."))
										.addLoreLine("�7Du erh�lst: �b5 Coins")
										.build();
							}
						});
					}else if(getStreak == 1){
						gui.getInventory().setItem(1, used());
						
						gui.getInventory().setItem(3, soon(player));
						gui.getInventory().setItem(4, soon());
						gui.getInventory().setItem(5, soon());
						gui.getInventory().setItem(6, soon());
						gui.getInventory().setItem(7, soon());
						gui.setButton(2, new GUIButton() {
							@Override
							public void onClick(InventoryClickEvent arg0) {
								if(DayLoginHandler.isRewardAvabile(player)) {
									DayLoginHandler.updateReward(player);
									Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 10);
									player.sendMessage("Tagesbonus", "Du hast �a+10 Coins�7 erhalten!");
									player.getBukkitPlayer().closeInventory();
								}
							}
							
							@Override
							public ItemStack getIcon() {
								return new ItemBuilder(Material.SPECKLED_MELON).setName("�6Belohnung")
										.addLoreLine("�7Verf�gbar: " + 
												(DayLoginHandler.isRewardAvabile(player) ? "�aJa" : "�b" 
												+ (Integer.valueOf(DayLoginHandler.getLastDay(player))+1) + "." + DateManager.getMonth()+"."))
										.addLoreLine("�7Du erh�lst: �b10 Coins")
										.build();
							}
						});
					}else if(getStreak == 2){
						gui.getInventory().setItem(1, used());
						gui.getInventory().setItem(2, used());
						
						gui.getInventory().setItem(4, soon(player));
						gui.getInventory().setItem(5, soon());
						gui.getInventory().setItem(6, soon());
						gui.getInventory().setItem(7, soon());
						gui.setButton(3, new GUIButton() {
							@Override
							public void onClick(InventoryClickEvent arg0) {
								if(DayLoginHandler.isRewardAvabile(player)) {
									DayLoginHandler.updateReward(player);
									Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 15);
									player.sendMessage("Tagesbonus", "Du hast �a+15 Coins�7 erhalten!");
									player.getBukkitPlayer().closeInventory();
								}
							}
							
							@Override
							public ItemStack getIcon() {
								return new ItemBuilder(Material.SPECKLED_MELON).setName("�6Belohnung")
										.addLoreLine("�7Verf�gbar: " + 
												(DayLoginHandler.isRewardAvabile(player) ? "�aJa" : "�b" 
												+ (Integer.valueOf(DayLoginHandler.getLastDay(player))+1) + "." + DateManager.getMonth()+"."))
										.addLoreLine("�7Du erh�lst: �b15 Coins")
										.build();
							}
						});
					}else if(getStreak == 3){
						gui.getInventory().setItem(1, used());
						gui.getInventory().setItem(2, used());
						gui.getInventory().setItem(3, used());
						
						gui.getInventory().setItem(5, soon(player));
						gui.getInventory().setItem(6, soon());
						gui.getInventory().setItem(7, soon());
						gui.setButton(4, new GUIButton() {
							@Override
							public void onClick(InventoryClickEvent arg0) {
								if(DayLoginHandler.isRewardAvabile(player)) {
									DayLoginHandler.updateReward(player);
									Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 25);
									player.sendMessage("Tagesbonus", "Du hast �a+25 Coins�7 erhalten!");
									player.getBukkitPlayer().closeInventory();
								}
							}
							
							@Override
							public ItemStack getIcon() {
								return new ItemBuilder(Material.SPECKLED_MELON).setName("�6Belohnung")
										.addLoreLine("�7Verf�gbar: " + 
												(DayLoginHandler.isRewardAvabile(player) ? "�aJa" : "�b" 
												+ (Integer.valueOf(DayLoginHandler.getLastDay(player))+1) + "." + DateManager.getMonth()+"."))
										.addLoreLine("�7Du erh�lst: �b25 Coins")
										.build();
							}
						});
					}else if(getStreak == 4){
						gui.getInventory().setItem(1, used());
						gui.getInventory().setItem(2, used());
						gui.getInventory().setItem(3, used());
						gui.getInventory().setItem(4, used());
						
						gui.getInventory().setItem(6, soon(player));
						gui.getInventory().setItem(7, soon());
						gui.setButton(5, new GUIButton() {
							@Override
							public void onClick(InventoryClickEvent arg0) {
								if(DayLoginHandler.isRewardAvabile(player)) {
									DayLoginHandler.updateReward(player);
									Coins.action(CoinAction.ADD, player.getBukkitPlayer(), 50);
									player.sendMessage("Tagesbonus", "Du hast �a+50 Coins�7 erhalten!");
									player.getBukkitPlayer().closeInventory();
								}
							}
							
							@Override
							public ItemStack getIcon() {
								return new ItemBuilder(Material.SPECKLED_MELON).setName("�6Belohnung")
										.addLoreLine("�7Verf�gbar: " + 
												(DayLoginHandler.isRewardAvabile(player) ? "�aJa" : "�b" 
												+ (Integer.valueOf(DayLoginHandler.getLastDay(player))+1) + "." + DateManager.getMonth()+"."))
										.addLoreLine("�7Du erh�lst: �b50 Coins")
										.build();
							}
						});
					}else if(getStreak == 5){
						gui.getInventory().setItem(1, used());
						gui.getInventory().setItem(2, used());
						gui.getInventory().setItem(3, used());
						gui.getInventory().setItem(4, used());
						gui.getInventory().setItem(5, used());
						
						gui.getInventory().setItem(7, soon(player));
						gui.setButton(6, new GUIButton() {
							@Override
							public void onClick(InventoryClickEvent arg0) {
								if(DayLoginHandler.isRewardAvabile(player)) {
									DayLoginHandler.updateReward(player);
									Coins.lottoAction(LottoAction.ADD, player.getBukkitPlayer(), 1);
									player.sendMessage("Tagesbonus", "Du hast �a+1 Lottoschein�7 erhalten!");
									player.getBukkitPlayer().closeInventory();
								}
							}
							
							@Override
							public ItemStack getIcon() {
								return new ItemBuilder(Material.SPECKLED_MELON).setName("�6Belohnung")
										.addLoreLine("�7Verf�gbar: " + 
												(DayLoginHandler.isRewardAvabile(player) ? "�aJa" : "�b" 
												+ (Integer.valueOf(DayLoginHandler.getLastDay(player))+1) + "." + DateManager.getMonth()+"."))
										.addLoreLine("�7Du erh�lst: �b1 Lottoschein")
										.build();
							}
						});
					}else if(getStreak == 6){
						gui.getInventory().setItem(1, used());
						gui.getInventory().setItem(2, used());
						gui.getInventory().setItem(3, used());
						gui.getInventory().setItem(4, used());
						gui.getInventory().setItem(5, used());
						gui.getInventory().setItem(6, used());
						gui.setButton(7, new GUIButton() {
							@Override
							public void onClick(InventoryClickEvent arg0) {
								if(DayLoginHandler.isRewardAvabile(player)) {
									DayLoginHandler.updateReward(player);
									Coins.lottoAction(LottoAction.ADD, player.getBukkitPlayer(), 2);
									player.sendMessage("Tagesbonus", "Du hast �a+2 Lottoscheine�7 erhalten!");
									player.getBukkitPlayer().closeInventory();
								}
							}
							
							@Override
							public ItemStack getIcon() {
								return new ItemBuilder(Material.SPECKLED_MELON).setName("�6Belohnung")
										.addLoreLine("�7Verf�gbar: " + 
												(DayLoginHandler.isRewardAvabile(player) ? "�aJa" : "�b" 
												+ (Integer.valueOf(DayLoginHandler.getLastDay(player))+1) + "." + DateManager.getMonth()+"."))
										.addLoreLine("�7Du erh�lst: �b2 Lottoscheine")
										.build();
							}
						});
					}
					
					gui.open();
				}
			}.runTaskLater(RisenWorld_Lobby.getInstance(), 1);
		}
	}
	
	private ItemStack used() {
		return new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short)14).setName("�cSchon Abgeholt!").build();
	}
	
	private ItemStack soon(CorePlayer player) {
		int lastDay = Integer.valueOf(DayLoginHandler.getLastDay(player));
		if(DayLoginHandler.isRewardAvabile(player)) {
			lastDay += 1;
		}
		return new ItemBuilder(Material.STAINED_GLASS_PANE).setName("�7Abholung am �6" + (lastDay+2) + "."+DateManager.getMonth() + ".").build();
	}
	
	private ItemStack soon() {
		return new ItemBuilder(Material.STAINED_GLASS_PANE).setName("�7Abholung Demn�chst").build();
	}
}
