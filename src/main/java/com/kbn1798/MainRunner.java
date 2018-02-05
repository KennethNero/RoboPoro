package com.kbn1798;

import java.util.ArrayList;
import java.util.HashMap;

import sx.blah.discord.api.IDiscordClient;

public class MainRunner {
	
	public static HashMap<String, ArrayList<String>> listOLists = new HashMap<>();

    public static void main(String[] args){
    	populateHashmap();
    	
        if(args.length != 1){
            System.out.println("Please enter the bots token as the first argument e.g java -jar thisjar.jar tokenhere");
            return;
        }

        IDiscordClient cli = BotCmdTest.getBuiltDiscordClient(args[0]);

        /*
        // Commented out as you don't really want duplicate listeners unless you're intentionally writing your code 
        // like that.
        // Register a listener via the IListener interface
        cli.getDispatcher().registerListener(new IListener<MessageReceivedEvent>() {
            public void handle(MessageReceivedEvent event) {
                if(event.getMessage().getContent().startsWith(BotUtils.BOT_PREFIX + "test"))
                    BotUtils.sendMessage(event.getChannel(), "I am sending a message from an IListener listener");
            }
        });
        */

        // Register a listener via the EventSubscriber annotation which allows for organisation and delegation of events
        cli.getDispatcher().registerListener(new MyEvents());

        // Only login after all events are registered otherwise some may be missed.
        cli.login();

    }
    
    private static void populateHashmap() {
    	ArrayList<String> l = new ArrayList<String>();
    	l.add("https://i.imgur.com/qGWNJI6.jpg");
    	l.add("https://i.imgur.com/eY7722M.jpg");
    	l.add("https://i.imgur.com/YHEneSA.jpg");
    	l.add("https://i.imgur.com/IRz71Sq.png");
    	l.add("https://i.imgur.com/Ir8OP15.png");
    	listOLists.put("Poro", l);
    }

}