package com.kbn1798.commands;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.kbn1798.utils.BotUtils;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class PoroShow {
	public static ArrayList<String> poro = new ArrayList<String>();
	public static ArrayList<String> strs = new ArrayList<String>();
	
	public static void providePoro(MessageReceivedEvent event) throws MalformedURLException, IOException {
		int i = (int) (Math.random()*poro.size());
		int k = (int) (Math.random()*strs.size());
    	BotUtils.embedFileFromURL(event.getChannel(), poro.get(i), strs.get(k), "Found one!");
		
	}
	
	
    public static void populatePoroList() {
    	poro.add("https://i.imgur.com/qGWNJI6.jpg"); poro.add("https://i.imgur.com/eY7722M.jpg");
    	poro.add("https://i.imgur.com/YHEneSA.jpg"); poro.add("https://i.imgur.com/IRz71Sq.png");
    	poro.add("https://i.imgur.com/Ir8OP15.png"); poro.add("https://i.imgur.com/VrENQFY.png");
    	poro.add("https://i.imgur.com/Xz1Hm06.jpg"); poro.add("https://i.imgur.com/GFs2KN3.jpg");
    	poro.add("https://i.imgur.com/e2LeJ6Z.jpg"); poro.add("https://i.imgur.com/mdM9Q08.jpg");
    	
    	strs.add("Soft lil thing for you!\n"); strs.add("Made of magic, love, and a lil bit of sugar.\n");
    	strs.add("Fresh one right out the oven!\n"); strs.add("This one seems to like you, aww.\n");
    	strs.add("Poros make the best pets!\n"); strs.add("Snaks! Does anyone have any snaks for the lil guy!?\n");
    	strs.add("Ever seen one this fluffy?\n"); strs.add("You can never lose a poro, they're just real good at hide&seek.\n");
    	strs.add("Even in the coldest climates, poros never stop smiling.\n"); strs.add("I think this one will do just fine.\n");
    }
}
