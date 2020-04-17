package de.deminosa.lobby.utils;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatAPI {

	public static TextComponent sendMessage(String msg){
		TextComponent message = new TextComponent(msg);
		return message;
	}
	
	public static TextComponent RunCommand(String msg, String CMD, String Hover){
		TextComponent message = new TextComponent(msg);
		message.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, CMD) );
		message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Hover).create() ) );
		return message;
	}
	
	public static TextComponent RunCommand(String msg, String CMD){
		TextComponent message = new TextComponent(msg);
		message.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, CMD) );
		return message;
	}
	
	
	public static TextComponent SuggestCommand(String msg, String CMD, String Hover){
		TextComponent message = new TextComponent(msg);
		message.setClickEvent( new ClickEvent( ClickEvent.Action.SUGGEST_COMMAND, CMD) );
		message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Hover).create() ) );
		return message;
	}
	
	public static TextComponent SuggestCommand(String msg, String CMD){
		TextComponent message = new TextComponent(msg);
		message.setClickEvent( new ClickEvent( ClickEvent.Action.SUGGEST_COMMAND, CMD) );
		return message;
	}
	
	public static TextComponent OpenURL(String msg, String URL, String Hover){
		TextComponent message = new TextComponent(msg);
		message.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, URL) );
		message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Hover).create() ) );
		return message;
	}
	
	public static TextComponent OpenURL(String msg, String URL){
		TextComponent message = new TextComponent(msg);
		message.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, URL) );
		return message;
	}
	
	public static void send(Player p, TextComponent textComponent){
		TextComponent message = new TextComponent(textComponent);
		p.spigot().sendMessage(message);
	}
	
	
	public static void send(Player p, TextComponent textComponent, TextComponent textComponent2){
		TextComponent message = new TextComponent(textComponent);
		message.addExtra(textComponent2);
		p.spigot().sendMessage(message);
	}
	
	public static void send(Player p, TextComponent textComponent, TextComponent textComponent2, TextComponent textComponent3){
		TextComponent message = new TextComponent(textComponent);
		message.addExtra(textComponent2);
		message.addExtra(textComponent3);
		p.spigot().sendMessage(message);
	}
	
	public static void send(Player p, TextComponent textComponent, TextComponent textComponent2, TextComponent textComponent3, TextComponent textComponent4){
		TextComponent message = new TextComponent(textComponent);
		message.addExtra(textComponent2);
		message.addExtra(textComponent3);
		message.addExtra(textComponent4);
		p.spigot().sendMessage(message);
	}
	
}
