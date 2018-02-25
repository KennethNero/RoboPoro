package com.kbn1798.commands;

import java.io.IOException;
import java.net.MalformedURLException;

import com.kbn1798.core.MainRunner;
import com.kbn1798.utils.BotUtils;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class PoroShow {
	
	public static void providePoro(MessageReceivedEvent event) throws MalformedURLException, IOException {
		
		int i = (int) (Math.random()*MainRunner.config.getPoroPics().size());
		int k = (int) (Math.random()*MainRunner.config.getPoroIntros().size());
    	BotUtils.embedFileFromURL(event.getChannel(), MainRunner.config.getPoroPics().get(i), MainRunner.config.getPoroIntros().get(k), "Found one!");
		
	}

}
