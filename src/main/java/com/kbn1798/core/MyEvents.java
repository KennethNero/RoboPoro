package com.kbn1798.core;

import java.io.IOException;
import java.net.MalformedURLException;

import com.kbn1798.commands.PoroModify;
import com.kbn1798.commands.PoroRoll;
import com.kbn1798.commands.PoroShow;
import com.kbn1798.utils.BotUtils;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class MyEvents {

	/***
	 * Catch all method for handling any message events supposed to be caught by the bot itself.
	 * 
	 * @param event The specific event in question.
	 * @throws MalformedURLException The possibility exists that a bad url comes through this method.
	 * @throws IOException Required IO exception catch.
	 */
    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) throws MalformedURLException, IOException{
    	String content = event.getMessage().getContent();
        if(content.startsWith(BotUtils.BOT_PREFIX + "test")) {
            BotUtils.sendMessage(event.getChannel(), "Result!");
        }
        
        else if(content.startsWith(BotUtils.BOT_PREFIX + "poro")) {
        	String[] split = content.split(" ");
        	if(split.length>=2) {
        		if(split[1].equalsIgnoreCase("list")) {
        			PoroShow.listBois(event);
        			
        		}else if(split[1].equalsIgnoreCase("add")) {
        			if(split.length>= 4) {
        				PoroModify.addPoro(event, split[2], split[3]);
        			}else {
        				PoroModify.argsAddError(event);
        			}
        			
        		}else if(split[1].equalsIgnoreCase("remove")) {
        			if(split.length>=3) {
        				PoroModify.removePoro(event, split[2]);
        			}
        			else {
        				PoroModify.argsRemoveError(event);
        			}
        			
        		}else if(MainRunner.config.getPoroNames().contains(split[1])) {
        			PoroShow.provideSpecPoro(event);
        		}else {
        			PoroShow.nameFindError(event);
        		}
        	}else{
        		PoroShow.providePoro(event);
        	}
        }
        else if(content.startsWith(BotUtils.BOT_PREFIX + "roll")) {
        	PoroRoll.processRoll(event);
        }
    }

}