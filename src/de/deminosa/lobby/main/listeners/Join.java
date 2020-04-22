package de.deminosa.lobby.main.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import de.deminosa.coinmanager.Coins;
import de.deminosa.coinmanager.command.CoinsCommand.CoinAction;
import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CoreCache;
import de.deminosa.core.utils.itembuilder.ItemBuilder;
import de.deminosa.core.utils.mysql.MySQL;
import de.deminosa.core.utils.warps.WarpManager;
import de.deminosa.lobby.RisenWorld_Lobby;
import de.deminosa.lobby.main.agb.AGB;
import de.deminosa.lobby.main.shop.Items.pets.PetUitls;
import de.deminosa.lobby.main.shop.Items.verwandlung.VerwandlungsManager;
import de.deminosa.lobby.main.timers.TestTimer;
import de.deminosa.lobby.utils.DateManager;
import de.deminosa.lobby.utils.GameChange;
import de.deminosa.lobby.utils.Utils;
import jump.JumpEndEvent;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	18:18:34 # 13.09.2019
 *
 */

public class Join implements Listener{

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage("");

		event.getPlayer().teleport(WarpManager.getWarpLocation("spawn"));

		new BukkitRunnable() {
			@Override
			public void run() {
				getItems(event.getPlayer());
				GameChange.sendGameState(event.getPlayer(), 3, -1);
				TestTimer.createHolo();
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 2);

		new BukkitRunnable() {

			@Override
			public void run() {
				CoreCache.getCorePlayer(event.getPlayer()).sendTitle(0, 20*5, 5, "§6Willkommen", "§7" + event.getPlayer().getName());
//				String[] s = {"§0","§6Coins","§b0 ", "§1", "§6Lottoscheine", "§b0  ", "§2", "§6Kisten", "§b0"};
				event.getPlayer().getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				event.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
				if(DateManager.getFullDate().equals("20.04.2020") | DateManager.getFullDate().equals("21.04.2020") |
						DateManager.getFullDate().equals("22.04.2020") |
						DateManager.getFullDate().equals("23.04.2020") |
						DateManager.getFullDate().equals("24.04.2020") |
						DateManager.getFullDate().equals("25.04.2020") |
						DateManager.getFullDate().equals("26.04.2020") |
						DateManager.getFullDate().equals("27.04.2020")) {
					CoreCache.getCorePlayer(event.getPlayer()).sendMessage("News", "");
					CoreCache.getCorePlayer(event.getPlayer()).sendMessage("News", "§6Update: §bBig Shop");
					CoreCache.getCorePlayer(event.getPlayer()).sendMessage("News", "");
					CoreCache.getCorePlayer(event.getPlayer()).sendMessage("News", "§7Es wurden für euch sehr viele neue");
					CoreCache.getCorePlayer(event.getPlayer()).sendMessage("News", "§7gegenstände hinzugefügt!");
					CoreCache.getCorePlayer(event.getPlayer()).sendMessage("News", "§7Es wurden §e50+ §7neue Items hinzugefügt!");
					CoreCache.getCorePlayer(event.getPlayer()).sendMessage("News", "");
					CoreCache.getCorePlayer(event.getPlayer()).sendMessage("News", "§6Neue Sectionen: §7Köpfe, Verwandlungen");
					CoreCache.getCorePlayer(event.getPlayer()).sendMessage("News", "");
					CoreCache.getCorePlayer(event.getPlayer()).playsound(Sound.LEVEL_UP);
				}
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 20*2);
	}

	@EventHandler
	public void ItemHolder(PlayerItemHeldEvent event) {
		CorePlayer player = CoreCache.getCorePlayer(event.getPlayer());
		player.playsound(Sound.CLICK);
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		AGB.open(event.getPlayer());
	}

	@EventHandler
	public void onMove(InventoryCloseEvent event) {
		new BukkitRunnable() {
			@Override
			public void run() {
				AGB.open((Player)event.getPlayer());
			}
		}.runTaskLater(RisenWorld_Lobby.getInstance(), 40);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		event.setQuitMessage("");
		PetUitls.stopFollow(event.getPlayer());
		VerwandlungsManager.stopFollow(event.getPlayer());
	}

	private void getItems(Player player) {
		player.getInventory().clear();
		player.getInventory().setItem(0, Utils.getGAMES());
		player.getInventory().setItem(8, Utils.getSHOP());

		player.getInventory().setItem(7, Utils.getTOY());
		player.getInventory().setItem(1, Utils.getJUMP());

		player.getInventory().setItem(6, null);
		player.getInventory().setItem(2, null);

		player.getInventory().setItem(5, null);
		player.getInventory().setItem(3, null);

		player.getInventory().setItem(4, null);

		for(int i = 9; i < 36; i++) {
			player.getInventory().setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE)
					.setDurability((short)15)
					.setName("§c").build());	
		}

		player.getInventory().setItem(20, new ItemBuilder(Material.REDSTONE).setName("§c§lDemnächst")
				.addLoreLine("§7Geplant: §6Eintellungen").build());
		player.getInventory().setItem(22, new ItemBuilder(Material.GLOWSTONE).setName("§6Event Server")
				.addLoreLine("§7Verbinde dich mit dem Event Server!").build());
		player.getInventory().setItem(24, new ItemBuilder(Material.CHEST).setName("§6Lotto").build());
	}

	@EventHandler
	public void JumpEnd(JumpEndEvent event) {
		int block = event.getJumpedBlocks();

		int coins = block/5;
		Coins.action(CoinAction.ADD, event.getPlayer(), coins);
		event.setMessage("§9Coins §7Du hast §6" + coins + " coin(s) §7bekommen für §6"+block+" Blöcke§7!");
		if(!MySQL.exsistValue("jump", "UUID", event.getPlayer().getUniqueId().toString(), "UUID")) {
			MySQL.set("jump", "UUID", event.getPlayer().getUniqueId().toString());
			MySQL.update("jump", "reach", ""+block, "UUID", event.getPlayer().getUniqueId().toString());
			event.getPlayer().sendMessage("§9Jump §7Neuer Rekord: §6" + block + " sprünge!");
		}else {
			int sqlBlock = MySQL.getInt("jump", "reach", "UUID", event.getPlayer().getUniqueId().toString());
			if(block > sqlBlock) {
				MySQL.update("jump", "reach", ""+block, "UUID", event.getPlayer().getUniqueId().toString());
				event.getPlayer().sendMessage("§9Jump §7Neuer Rekord: §6" + block + " sprünge!");
			}else {
				event.getPlayer().sendMessage("§9Jump §7Rekord: §6" + sqlBlock + " sprünge!");
			}
		}
	}

}
