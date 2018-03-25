package com.kbn1798.commands;

import com.kbn1798.core.MainRunner;
import com.kbn1798.utils.BotUtils;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class PoroModify {
	
	/***
	 * A method which is able to add an entry to the configuration hashmap responsible for storing 
	 * pictures and URLs of poros
	 * 
	 * @param e The event which caused this method to be called
	 * @param name The requested name to be added to configuration
	 * @param url The url attached to the name attempting to be added to configuration
	 */
	public static void addPoro(MessageReceivedEvent e, String name, String url) {
		if(MainRunner.config.getPoroNames().contains(name)) {
			BotUtils.sendMessage(e.getChannel(), "Seems "+name+" already exists in the file!\nEither rename your poro or remove the one which shares this name");
		}else {
			MainRunner.config.getPoroPics().put(name, url);
			MainRunner.saveConfig();
			BotUtils.sendMessage(e.getChannel(), name+" has been saved with provided URL!\nType /poro "+name+" to bring it up.");
		}
	}
	
	/***
	 * A method which is able to remove an entry to the configuration hashmap responsible for storing 
	 * pictures and URLs of poros
	 * 
	 * @param e The event which caused this method to be called
	 * @param name The requested name to be removed from configuration
	 */
	public static void removePoro(MessageReceivedEvent e, String name) {
		if(MainRunner.config.getPoroNames().contains(name)) {
			if(MainRunner.config.getPoroNames().size()>1) {
				String url = MainRunner.config.getPoroPics().get(name);
				MainRunner.config.getPoroPics().remove(name);
				MainRunner.saveConfig();
				BotUtils.sendMessage(e.getChannel(), name+" has been removed from the list! Too add back, type:\n/poro add "+name+" "+url);
			}else {
				BotUtils.sendMessage(e.getChannel(), "Oops! If we were to remove "+name+" there would be no poros in the list!\nAdd more to remove "+name);
			}
		}else {
			BotUtils.sendMessage(e.getChannel(), "It seems "+name+" doesn't exist in the current list of poros.");
		}
	}
	
	/***
	 * A method to call if the number of args required for poroAdd are incorrectly met
	 * 
	 * @param e The event which caused this method to be called
	 */
	public static void argsAddError(MessageReceivedEvent e) {
		BotUtils.sendMessage(e.getChannel(), "Oops! Poro addition was wrongly formatted. Try:\n**/poro add [name] [URL]**");
	}
	
	/***
	 * A method to call if the number of args required for poroRemove are incorrectly met
	 * 
	 * @param e The event which caused this method to be called
	 */
	public static void argsRemoveError(MessageReceivedEvent e) {
		BotUtils.sendMessage(e.getChannel(), "Oops! Poro removal was wrongly formatted. Try:\n**/poro remove [name]**");
	}
	
}
