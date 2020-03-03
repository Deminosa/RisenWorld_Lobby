package de.deminosa.lobby.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	17:07:06 # 08.08.2019
*
*/

public class DateManager {

	public static String getDay() {
		SimpleDateFormat date = new SimpleDateFormat("dd");
		String datum = date.format(new Date());
		return datum;
	}
	
	public static String getMonth() {
		SimpleDateFormat date = new SimpleDateFormat("MM");
		String datum = date.format(new Date());
		return datum;
	}
	
	public static String getTime() {
		SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
		String time = date.format(new Date());
		return time;
	}
	
	public static String getDate() {
		SimpleDateFormat date = new SimpleDateFormat("dd.MM");
		String datum = date.format(new Date());
		return datum;
	}
	
	public static String getFullDate() {
		SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
		String datum = date.format(new Date());
		return datum;
	}
	
}
