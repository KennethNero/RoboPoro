package com.kbn1798.core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import com.kbn1798.utils.BotUtils;
import com.kbn1798.utils.Configuration;
import com.kbn1798.utils.YamlGen;

import sx.blah.discord.api.IDiscordClient;

public class MainRunner {
	public static HashMap<Long, Configuration> config = new HashMap<Long, Configuration>(); //Holds ServerLongID and Config attached
	public static IDiscordClient client;
	public static File folder;
	
    public static void main(String[] args) throws IOException{
    	//Main malformed input check block.
        if(args.length != 2){
        	if(args.length != 1) {
        		System.out.println("Please enter the bots token as the first argument e.g java -jar thisjar.jar tokenhere");
                return;
        	}else if(args.length !=2) {
        		System.out.println("Please enter the name of the configuration file as the second argument e.g java -jar thisjar.jar tokenhere confighere");
        		return;
        	}else {
        		System.out.println("Too many arguments, you've scared all the poros away.");
        	}
        }
        
        folder = new File("Configs");
        if(!folder.exists()) {
        	try {
        		folder.mkdir();
        	}catch(SecurityException e) {
        		e.printStackTrace();
        	}
        }
     

        IDiscordClient cli = BotUtils.getBuiltDiscordClient(args[0]);
        client = cli;

        // Register a listener via the EventSubscriber annotation which allows for organization and delegation of events
        cli.getDispatcher().registerListener(new MyEvents());

        // Only login after all events are registered otherwise some may be missed.
        cli.login();
        while(!client.isReady()) {}
        YamlGen.checkConfig();//Guilds are intiilized
        
    }
    
    
    

    

    
}