package de.deminosa.lobby.main.daylogin;

import de.deminosa.core.builders.CorePlayer;
import de.deminosa.core.cache.CorePlayerData;
import de.deminosa.core.utils.mysql.MySQL;
import de.deminosa.lobby.utils.DateManager;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	12:15:49 # 26.02.2020
*
*/

public class DayLoginHandler {

	public static boolean isRewardAvabile(CorePlayer player) {
		if(MySQL.exsistValue("DayLogin", "UUID", player.getUUIDAsString(), "UUID")) {
			String getLastDay = MySQL.getString("DayLogin", "UUID", player.getUUIDAsString(), "LastDay");
			//String getLastMonth = MySQL.getString("DayLogin", "UUID", player.getUUIDAsString(), "LastMonth");
			
			if(DateManager.getDay().equals(getLastDay)) {
				return false;
			}
			return true;
		}
		return true;
	}
	
	public static void updateReward(CorePlayer player) {
		if(!MySQL.exsistValue("DayLogin", "UUID", player.getUUIDAsString(), "UUID")) {
			MySQL.set("DayLogin", "UUID", player.getUUIDAsString());
			MySQL.update("DayLogin", "LastDay", DateManager.getDay(), "UUID", player.getUUIDAsString());
			MySQL.update("DayLogin", "LastMonth", DateManager.getMonth(), "UUID", player.getUUIDAsString());
			checkStreak(player);
		}else {
			checkStreak(player);
			MySQL.update("DayLogin", "LastDay", DateManager.getDay(), "UUID", player.getUUIDAsString());
			MySQL.update("DayLogin", "LastMonth", DateManager.getMonth(), "UUID", player.getUUIDAsString());
		}
	}
	
	
	private static void checkStreak(CorePlayer player) {
		String getLastDay = MySQL.getString("DayLogin", "UUID", player.getUUIDAsString(), "LastDay");
		String getLastMonth = MySQL.getString("DayLogin", "UUID", player.getUUIDAsString(), "LastMonth");
		
		int lastDay = Integer.valueOf(getLastDay);
		int lastMonth = Integer.valueOf(getLastMonth);
		
		int currentDay = Integer.valueOf(DateManager.getDay());
		int currentMonth = Integer.valueOf(DateManager.getMonth());
		
		// 1 ist streak 2+ ist brake
		// 30 & 29 ist Streak
		int dayResult = lastDay - currentDay;
		int monthResult = lastMonth - currentMonth;
		
		if(dayResult == 1 || dayResult == 30 || dayResult == 29) {
			if(monthResult == 0 || monthResult == 1) {
				int getStreak = MySQL.getInt("DayLogin", "Streak", "UUID", player.getUUIDAsString());
				int streakResult = getStreak + 1;
				if(streakResult == 7) {
					MySQL.update("DayLogin", "Streak", String.valueOf(0), "UUID", player.getUUIDAsString());
					CorePlayerData.setData(player, "DayLogin", "7Streak", true);
				}else {
					MySQL.update("DayLogin", "Streak", String.valueOf(streakResult), "UUID", player.getUUIDAsString());
					CorePlayerData.setData(player, "DayLogin", "7Streak", false);
				}
			}else {
				MySQL.update("DayLogin", "Streak", String.valueOf(0), "UUID", player.getUUIDAsString());
				CorePlayerData.setData(player, "DayLogin", "7Streak", false);
			}
		}else {
			MySQL.update("DayLogin", "Streak", String.valueOf(0), "UUID", player.getUUIDAsString());
			CorePlayerData.setData(player, "DayLogin", "7Streak", false);
		}
	}
}
