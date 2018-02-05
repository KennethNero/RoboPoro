package com.kbn1798;

import java.io.IOException;
import java.net.MalformedURLException;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class MyEvents {

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) throws MalformedURLException, IOException{
        if(event.getMessage().getContent().startsWith(BotCmdTest.BOT_PREFIX + "test"))
            BotCmdTest.sendMessage(event.getChannel(), "I am sending a message from an EventSubscriber listener");
        else if(event.getMessage().getContent().startsWith(BotCmdTest.BOT_PREFIX + "poro")) {
        	int i = (int) (Math.random()*MainRunner.listOLists.get("Poro").size());
        	BotCmdTest.embedFileFromURL(event.getChannel(), MainRunner.listOLists.get("Poro").get(i) );
        }
        	
    }

}