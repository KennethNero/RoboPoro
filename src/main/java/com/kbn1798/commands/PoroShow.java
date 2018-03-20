package com.kbn1798.commands;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.kbn1798.core.MainRunner;
import com.kbn1798.utils.BotUtils;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class PoroShow {
	
	/***
	 * A method designed to display a picture from the poro gathered from the config file, with the given string
	 * also gathered from the config.
	 * 
	 * @param event The MessageReceivedEvent referenced that activated this method.
	 * @throws MalformedURLException Deals with bad urls being parsed as embedded files.
	 * @throws IOException Classic I/O Exception.
	 */
	public static void providePoro(MessageReceivedEvent event) throws MalformedURLException, IOException{
		int i = (int) (Math.random()*MainRunner.config.getPoroNames().size());
		String name = "";
		int j=0;
		System.out.println(i);
		for(String s: MainRunner.config.getPoroNames()) {
			if(j==i) {
				name = s;
				break;
			}j++;
		}
		System.out.println(name);
		System.out.println(MainRunner.config.getPoroPics().get(name));
		int k = (int) (Math.random()*MainRunner.config.getPoroIntros().size());
    	BotUtils.embedFileFromURL(event.getChannel(), MainRunner.config.getPoroPics().get(name), MainRunner.config.getPoroIntros().get(k), "Found one!");
		
	}
	
	public static void provideSpecPoro(MessageReceivedEvent event) throws MalformedURLException, IOException{
		String name = event.getMessage().getContent().split(" ")[1];
		int k = (int) (Math.random()*MainRunner.config.getPoroIntros().size());
		BotUtils.embedFileFromURL(event.getChannel(), MainRunner.config.getPoroPics().get(name), MainRunner.config.getPoroIntros().get(k), "Heres "+name+"!");
		
	}

	/**
	 * A method designed to display information from the yaml configuration into chat as an embedded message.
	 * 
	 * @param event The trigger event that calls this method (/poro list)
	 */
	public static void listBois(MessageReceivedEvent event) {
		HashMap<String, String> pics = MainRunner.config.getPoroPics();
		ArrayList<String> fields = new ArrayList<String>();
		for(String s:pics.keySet()) {
			fields.add(s);
		}
		BotUtils.embededPoroList(event.getChannel(), fields, pics);
	}

}
